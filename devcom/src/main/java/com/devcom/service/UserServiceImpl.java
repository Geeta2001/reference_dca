package com.devcom.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devcom.dto.UserDTO;
import com.devcom.entity.User;
import com.devcom.exception.InvalidCredentialsException;
import com.devcom.exception.UserExistsException;
import com.devcom.exception.UserIsBlockedException;
import com.devcom.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	UserRepository userRepository;

	@Override
	public User registerUser(UserDTO userdto) throws UserExistsException {
		Optional<User> opt1 = userRepository.findByUserName(userdto.getUserName());
		if(opt1.isPresent()) {
			LOG.error("user already exists");
			throw new UserExistsException();
		}
		User user = new User();
				user.setUserName(userdto.getUserName());
		user.setPassword(userdto.getPassword());
		return userRepository.save(user);
	}

	@Override
	public User loginUser(UserDTO userdto) throws InvalidCredentialsException, UserIsBlockedException {
		String username = userdto.getUserName();
		String password = userdto.getPassword();
		Optional<User> opt = userRepository.findByUserName(username);
		
		if ( opt.isPresent() && opt.get().isBlocked() == true ) {
			LOG.info("User is blocked");
			throw new UserIsBlockedException();
		}
		else if(opt.isPresent() && opt.get().getPassword().equals(password)){
			LOG.info("User Login successful");
			User user = opt.get();
			return user;
		}
		else {
			LOG.error("Invalid Credentials");
			throw new InvalidCredentialsException();
		}
	}
	
	@Override
	public User blockUserId(int userId) {
		@SuppressWarnings("deprecation")
		User getUser = userRepository.getById(userId);
		getUser.setBlocked(true);
		LOG.info("User is blocked");
		return userRepository.save(getUser);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public User unblockUserId(int userId) {
		User getUser = userRepository.getById(userId);
		getUser.setBlocked(false);
		LOG.info("User is unblocked");
		return userRepository.save(getUser);
		
	}

	@Override
	public List<User> getAllUser() {
		return userRepository.findAll();
	}
	}
