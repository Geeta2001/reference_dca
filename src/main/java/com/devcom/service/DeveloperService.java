package com.devcom.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.devcom.dto.DeveloperDTO;
import com.devcom.entity.Developer;

public interface DeveloperService {

	public Developer addDeveloper(DeveloperDTO developerdto);
		
	public Optional<Developer> getDeveloper(int devId);
	
	public List<Developer> getAllDevelopers();

	public ResponseEntity<Developer> editDeveloper(DeveloperDTO developerdto, int devId);

	ResponseEntity<Developer> blockDeveloper(DeveloperDTO developerdto, int devId);

	ResponseEntity<Developer> unblockDeveloper(DeveloperDTO developerdto, int devId);
}
