package com.springboot.taskproject.service;

import java.util.List;

import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

import com.springboot.taskproject.payload.TaskDto;


public interface TaskService {
	public TaskDto saveTask(long userid, TaskDto taskDto);
	
	public List<TaskDto> getAllTasks(long userid);

	
}
