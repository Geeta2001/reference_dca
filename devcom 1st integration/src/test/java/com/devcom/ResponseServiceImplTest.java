package com.devcom.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.devcom.dto.FeedDto;
import com.devcom.entity.Feed;
import com.devcom.entity.Response;
import com.devcom.repository.ResponseRepository;



@RunWith(SpringRunner.class)
@SpringBootTest
class ResponseServiceImplTest {
	
	@Autowired 
	private ResponseServiceImpl responseService;
	
	@Mock
	private ResponseRepository responseRepository;
	

	@Test
	void testAddResponse() {
		Response response = new Response("answering to question",new Date(),1);
		
	when(responseRepository.save(response)).thenReturn(response);
	assertEquals(response,responseRepository.save(response));	
	}
	

//	Feed feed = new Feed(1,"asking question",new Date(),"Java",4);
//	ResponseEntity<Feed> feedEntity= ResponseEntity.ok(feed);
//	FeedDto feedDto = new FeedDto();
//	BeanUtils.copyProperties(feed, feedDto); 
//	when(feedRepository.getById(1)).thenReturn(feed);
//	
//	assertEquals(feedEntity,feedService.editFeed(1, feedDto));
	@BeforeAll
     public static void setUP() {
		Response res= new Response("ans",new Date(),1);
	}

	@Test
	void testEditResponse() {
		
//		Response res=responseRepository.getById(1).;
//		res.setAnswer("changed");
//		responseRepository.save(res);
//		assertNotEquals("5000",responseRepository.findById(1).get().getAnswer());
//		
//		
	}
//	@Test
//	public void deleteUserTest() {
//		User user = new User(999, "Pranya", 33, "Pune");
//		service.deleteUser(user);
//		verify(repository, times(1)).delete(user);
//	}

	@Test
	void testRemoveResponse() {
		responseRepository.deleteById(1);
		assertThat(responseRepository.existsById(1)).isFalse();
	}
//	when(repository.findAll()).thenReturn(Stream
//			.of(new User(376, "Danile", 31, "USA"), new User(958, "Huy", 35, "UK")).collect(Collectors.toList()));
//	assertEquals(2, service.getUsers().size());
	@Test
	void testGetResponse() {
		Response res= new Response("answering to question",new Date(),1);
		Optional<Response> respOptional = Optional.of(res);
		when(responseRepository.findById(1)).thenReturn(respOptional);
		assertThat(responseService.getResponse(1).isPresent());
		
	}

}
