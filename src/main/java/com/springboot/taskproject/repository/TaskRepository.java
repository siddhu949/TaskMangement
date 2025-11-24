package com.springboot.taskproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.taskproject.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long>{

}
