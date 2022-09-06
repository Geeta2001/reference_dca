package com.devcom.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devcom.dto.UserDto;
import com.devcom.entity.User;
import com.devcom.repository.UserRepo;

@Service
public class UserServiceImpl implements UserIService {
	@Autowired
	UserRepo userRepo;

	@Override
	public User registerUser(UserDto userdto) {
		User user = new User();

		user.setUserName(userdto.getUserName());
		user.setPassword(userdto.getPassword());
		user.setRole(userdto.getRole());
		return userRepo.save(user);

	}

}
