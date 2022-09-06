package com.devcom.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.devcom.dto.UserDto;
import com.devcom.entity.User;
import com.devcom.exception.UserExistsException;
import com.devcom.repository.UserRepo;
import com.devcom.service.UserIService;

@RestController
public class UserController {

	@Autowired
	UserIService userService;
	
	@Autowired
	UserRepo userRepo;

	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@RequestBody @Valid UserDto userdto) {
		Optional<User> opt1 = userRepo.findByUserName(userdto.getUserName());
		if (opt1.isPresent()) {
			throw new UserExistsException();
		} else {
			userService.registerUser(userdto);
			return new ResponseEntity<>("Success", HttpStatus.OK);
		}

	}

	@PostMapping("/login")
	public ResponseEntity<String> loginUser(@ModelAttribute @Valid UserDto userdto) {
		String username = userdto.getUserName();
		String password = userdto.getPassword();
		Optional<User> opt = userRepo.findByUserName(username);

		if (opt.isPresent() && opt.get().getPassword().equals(password)) {
			return new ResponseEntity<>("Login successfully", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Incorrect credentials", HttpStatus.OK);
		}
	}

}
