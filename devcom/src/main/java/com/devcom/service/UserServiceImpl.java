package com.devcom.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devcom.dto.UserDTO;
import com.devcom.entity.User;
import com.devcom.exception.InvalidCredentialsException;
import com.devcom.exception.UserExistsException;
import com.devcom.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	UserRepository userRepository;

	@Override
	public User registerUser(UserDTO userdto) throws UserExistsException {
		Optional<User> opt1 = userRepository.findByUserName(userdto.getUserName());
		if(opt1.isPresent()) {
			log.error("user already exists");
			throw new UserExistsException();
		}
		User user = new User();
				user.setUserName(userdto.getUserName());
		user.setPassword(userdto.getPassword());
		return userRepository.save(user);
	}

	@Override
	public User loginUser(UserDTO userdto) throws InvalidCredentialsException {
		String username = userdto.getUserName();
		String password = userdto.getPassword();
		Optional<User> opt = userRepository.findByUserName(username);

		if (opt.isPresent() && opt.get().getPassword().equals(password)) {
			return opt.get();
		} 
		throw new InvalidCredentialsException(); 
		}	
	}
