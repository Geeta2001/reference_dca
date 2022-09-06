package com.devcom.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.devcom.dto.UserDTO;
import com.devcom.service.UserService;

@RestController
//@RequestMapping("/register")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@RequestBody UserDTO userdto) {
		return userService.registerUser(userdto);
			
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> loginUser(@ModelAttribute UserDTO userdto) {
		return userService.loginUser(userdto);
	}
	

}