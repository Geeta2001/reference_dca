package com.devcom.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.time.ZonedDateTime;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.devcom.entity.Feed;
import com.devcom.repository.FeedRepository;
import com.devcom.repository.UserRepo;



@RunWith(SpringRunner.class)
@SpringBootTest
class FeedServiceImpTest {
	
	@Autowired
	private FeedServiceImp feedService;
	
	@Mock
	private FeedRepository feeedRepository;

	@Test
	void testAddFeed() {
		Feed feed = new Feed("asking question","Java",new Date());
		
		when(feeedRepository.save(feed)).thenReturn(feed);
		assertEquals(feed,feeedRepository.save(feed));
		
	}
//
//	@Test
//	void testRemoveFeed() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testGetFeed() {
//		fail("Not yet implemented");
//	}

}
