package com.springboot.taskproject.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.taskproject.entity.Task;
import com.springboot.taskproject.entity.Users;
import com.springboot.taskproject.exception.APIException;
import com.springboot.taskproject.exception.TaskNotFound;
import com.springboot.taskproject.exception.UserNotFound;
import com.springboot.taskproject.payload.TaskDto;
import com.springboot.taskproject.repository.TaskRepository;
import com.springboot.taskproject.repository.UserRepository;
import com.springboot.taskproject.service.TaskService;
@Service
public class TaskServiceImpl implements TaskService{
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private TaskRepository taskRepository;
	public TaskDto saveTask(long userid, TaskDto taskDto) {
		//first we validate the user id
	Users users=userRepository.findById(userid).orElseThrow(()-> new UserNotFound(String.format("User id %d not found", userid)));
	Task task=modelMapper.map(taskDto, Task.class);
	Task savedTask=taskRepository.save(task);
	
	
		return modelMapper.map(savedTask,TaskDto.class);
	}

 //get the all tasks for the user id
	public List<TaskDto> getAllTasks(long userid) {
		// search the user id validated
		Users users=userRepository.findById(userid).orElseThrow(
				()-> new UserNotFound(String.format("User id %d not found", userid))
				);
		List<Task> tasks = taskRepository.findAllByUsersId(userid);
		
		return tasks.stream().map(
				task -> modelMapper.map(task, TaskDto.class)
				).collect(Collectors.toList());
	}
	//getting indv tasks

	@Override
	public TaskDto getTask(long userid, long taskid) {
		Users users=userRepository.findById(userid).orElseThrow(
				()-> new UserNotFound(String.format("User id %d not found", userid))
				);
		Task task =taskRepository.findById(taskid).orElseThrow(
				() -> new TaskNotFound(String.format("TASK NOT %d IS NOT FOUND", taskid))
				);
		if(users.getId()!=task.getUsers().getId()) {
			throw new APIException(String.format("TASK IS %d NOT BELONGS TO %d USER", taskid,userid));
		}
		return modelMapper.map(task, TaskDto.class);
	}

	@Override
	public void deleteTask(long userid, long taskid) {
		Users users=userRepository.findById(userid).orElseThrow(
				()-> new UserNotFound(String.format("User id %d not found", userid))
				);
		Task task =taskRepository.findById(taskid).orElseThrow(
				() -> new TaskNotFound(String.format("TASK NOT %d IS NOT FOUND", taskid))
				);
		if(users.getId()!=task.getUsers().getId()) {
			throw new APIException(String.format("TASK IS %d NOT BELONGS TO %d USER", taskid,userid));
		}
		taskRepository.deleteById(taskid);
	}
	


}
