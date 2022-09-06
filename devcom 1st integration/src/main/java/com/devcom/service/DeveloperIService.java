package com.devcom.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.devcom.dto.DeveloperDTO;
import com.devcom.entity.Developer;

public interface DeveloperIService {

	public Developer addDeveloper(DeveloperDTO developerdto);
		
	public Optional<Developer> getDeveloper(int devId);
	
	public List<Developer> getAllDevelopers();

	public Developer editDeveloper(DeveloperDTO developerdto, int devId);

	public Developer blockuser( int devId);
	
	public Developer unblockuser( int devId);
}
