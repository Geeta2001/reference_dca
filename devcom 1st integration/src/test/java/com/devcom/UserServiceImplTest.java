package com.devcom.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.devcom.entity.User;
import com.devcom.repository.UserRepo;




@RunWith(SpringRunner.class)
@SpringBootTest
class UserServiceImplTest {

	@Mock
	private UserRepo userRepository;
	
	@Test
	void testRegisterUser() {
		User user = new User("abc","abc","asd");
		
		when(userRepository.save(user)).thenReturn(user);
		assertEquals(user,userRepository.save(user));
		
	}
	//negativeTest
	@Test
	void testRegisterUserN() {
		
	}
	

}
