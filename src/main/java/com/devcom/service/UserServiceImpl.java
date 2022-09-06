package com.devcom.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.devcom.dto.UserDTO;
import com.devcom.entity.User;
import com.devcom.exception.InvalidCredentialsException;
import com.devcom.exception.UserExistsException;
import com.devcom.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userRepository;

	@Override
	public ResponseEntity<String> registerUser(UserDTO userdto) {
		// TODO Auto-generated method stub
		User user = new User();
		
		user.setUserName(userdto.getUserName());
		user.setPassword(userdto.getPassword());
		user.setRole(userdto.getRole());
		Optional<User> opt1 = userRepository.findByUserName(userdto.getUserName());
		if(opt1.isPresent()) {
			throw new UserExistsException();
		}else {
			userRepository.save(user);
			return new ResponseEntity<String>("Success", HttpStatus.OK);
		}
	}
	
	@Override
	public ResponseEntity<String> loginUser(UserDTO userdto) {
		String username = userdto.getUserName();
		String password = userdto.getPassword();
		Optional<User> opt = userRepository.findByUserName(username);
		
		if(opt.isPresent() && opt.get().getPassword().equals(password)) {
			return new ResponseEntity<String>("Login successfully", HttpStatus.OK);
		}else {
			throw new InvalidCredentialsException();
		}
	}
}