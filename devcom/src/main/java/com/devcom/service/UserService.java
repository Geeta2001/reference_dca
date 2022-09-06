package com.devcom.service;

import org.springframework.http.ResponseEntity;

import com.devcom.dto.UserDTO;

public interface UserService {
	public ResponseEntity<String> registerUser(UserDTO userdto);

	ResponseEntity<String> loginUser(UserDTO userdto);
}
