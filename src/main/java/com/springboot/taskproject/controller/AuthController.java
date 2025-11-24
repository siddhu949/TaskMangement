package com.springboot.taskproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.taskproject.entity.Users;
import com.springboot.taskproject.payload.UserDto;
import com.springboot.taskproject.repository.UserRepository;
import com.springboot.taskproject.service.UserService;

@RestController
@RequestMapping("/ap/auth")
public class AuthController {
	@Autowired
	private UserService userService;
	//post the user to the db
	@PostMapping("/register")
	// this is not a coorected method we dont call the users entity direct to here because if we want to get the task and user then we want to write the extra code
	//to expilicity to get the user details so we need the dto to implicity call the user details and getting the details
//	public void createUser(@RequestBody Users users) {
//		@Autowired
//		UserRepository.save(users);
//	}
	
	
	public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
		return new ResponseEntity<>(userService.createUser(userDto),HttpStatus.CREATED);
	}
}
