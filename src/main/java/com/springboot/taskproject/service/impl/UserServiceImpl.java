package com.springboot.taskproject.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.taskproject.entity.Users;
import com.springboot.taskproject.payload.UserDto;
import com.springboot.taskproject.repository.UserRepository;
import com.springboot.taskproject.service.UserService;
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDto createUser(UserDto userDto) {
		// userDto is not an entity of users
		Users user = userDtoToEntity(userDto);//converted userdto to users
		Users savedUser = userRepository.save(user);
		return entityToUserDto(savedUser);
	}
	//this is used to convert the user dto to user details 
	private Users userDtoToEntity(UserDto userDto) {
		Users users = new Users();
		users.setEmail(userDto.getEmail());
		users.setName(userDto.getPassword());
		users.setPassword(userDto.getPassword());
		return users;
	}
	// thisis used to convert the users info to user dto
	private UserDto entityToUserDto(Users savedUser) {
		UserDto userDto = new UserDto();
		userDto.setId(savedUser.getId());
		userDto.setEmail(savedUser.getEmail());
		userDto.setPassword(savedUser.getPassword());
		userDto.setName(savedUser.getName());
		return userDto;
	}
}
