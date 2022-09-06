package com.devcom.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.devcom.entity.Developer;
import com.devcom.repository.DeveloperRepository;



@RunWith(SpringRunner.class)
@SpringBootTest
class DeveloperServiceImplTest {
	
	@Autowired
	private DeveloperServiceImpl developerServiceImpl;
	
	@Mock
	private DeveloperRepository developerRepository;

	@Test
	void testAddDeveloper() {
		Developer developer = new Developer("name","name","name");
		
		when(developerRepository.save(developer)).thenReturn(developer);
	assertEquals(developer,developerRepository.save(developer));
		
	}
	

	@Test
	void testGetDeveloper() {
		Developer developer= new Developer("name","name","name");
		Optional<Developer> devOptional = Optional.of(developer);
		when(developerRepository.findById(1)).thenReturn(devOptional);
    assertEquals(1,developerServiceImpl.getAllDevelopers().size());
	}
//
	@Test
	void testGetAllDevelopers() {
		
		when(developerRepository.findAll()).thenReturn(Stream.of(new Developer("name","name","name"),
				new Developer("name1","name1","name1")).collect(Collectors.toList()));
		
		
//		assertEquals(1+i,developerServiceImpl.getAllDevelopers().size());
		assertThat(developerServiceImpl.getAllDevelopers().size()).isGreaterThan(1);
	}
//
//	@Test
//	void testEditDeveloper() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testBlockuser() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testUnblockuser() {
//		fail("Not yet implemented");
//	}
}

