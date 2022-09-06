package com.devcom.service;

import org.springframework.http.ResponseEntity;

import com.devcom.dto.UserDTO;
import com.devcom.entity.User;

public interface UserService {
	public User registerUser(UserDTO userdto);

//	public ResponseEntity<String> loginUser(UserDTO userdto);
}
