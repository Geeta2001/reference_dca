package com.devcom.service;

import java.util.List;

import com.devcom.dto.UserDTO;
import com.devcom.entity.User;
import com.devcom.exception.InvalidCredentialsException;
import com.devcom.exception.UserIsBlockedException;

public interface UserService {
	public User registerUser(UserDTO userdto);
	
	public User loginUser(UserDTO userdto) throws InvalidCredentialsException, UserIsBlockedException;

	List<User> getAllUser();

	User blockUserId(int userId);

	User unblockUserId(int userId);
}
