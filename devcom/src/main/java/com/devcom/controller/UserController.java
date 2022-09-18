package com.devcom.controller;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devcom.dto.UserDTO;
import com.devcom.entity.User;
import com.devcom.exception.InvalidCredentialsException;
import com.devcom.exception.UserExistsException;
import com.devcom.exception.UserIsBlockedException;
import com.devcom.service.UserService;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@Validated
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@RequestBody @Valid UserDTO userdto) throws UserExistsException {
			userService.registerUser(userdto);
			return new ResponseEntity<>("Success", HttpStatus.OK);
	}
	
	@PostMapping("/login")
	public ResponseEntity<Integer> loginUser(@RequestBody UserDTO userdto) throws InvalidCredentialsException, UserIsBlockedException {
			User user = userService.loginUser(userdto);
			return new ResponseEntity<>(user.getUserId(), HttpStatus.OK);
			
	}
	
	@PutMapping("/blockuser/{userId}")
	public ResponseEntity<User> blockUserId(@PathVariable("userId") int userId){
		User savestatus = userService.blockUserId(userId);
		return ResponseEntity.ok().body(savestatus);
	}
	
	@PutMapping("/unblockuser/{userId}")
	public ResponseEntity<User> unblockUserId(@PathVariable("userId") int userId){
		User savestatus = userService.unblockUserId(userId);
		return ResponseEntity.ok().body(savestatus);
	}
	
	@GetMapping("/alluserdetails")
	public List<User> getAllUser() {
		return userService.getAllUser();
	}
	

}