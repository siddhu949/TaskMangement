package com.springboot.taskproject.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.taskproject.entity.Task;
import com.springboot.taskproject.entity.Users;
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


	public List<TaskDto> getAllTasks(long userid) {
		return null;
	}



}
