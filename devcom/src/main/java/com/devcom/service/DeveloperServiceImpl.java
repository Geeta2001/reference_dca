 package com.devcom.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.devcom.dto.DeveloperDTO;
import com.devcom.entity.Developer;
import com.devcom.entity.User;
import com.devcom.exception.UserNotFoundException;
import com.devcom.repository.DeveloperRepository;
import com.devcom.repository.UserRepository;



@Service 
public class DeveloperServiceImpl implements DeveloperService {
	
	@Autowired
	DeveloperRepository developerRepository; 
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public ResponseEntity<String> addDeveloper(DeveloperDTO devDTO) {
		Optional<User> user = userRepository.findById(devDTO.getUserId());
		Developer developer = new Developer();
		developer.setName(devDTO.getName());
		developer.setEmail(devDTO.getEmail());
		developer.setSkillLevel(devDTO.getSkillLevel());
		if(user.isEmpty()) {
			throw new UserNotFoundException();
		}
		developer.setUser(user.get());
		developerRepository.save(developer);
		return new ResponseEntity<>("Developer details added",HttpStatus.OK);
	}

	@Override
	public Optional<Developer> getDeveloper(int devId) {
		return developerRepository.findById(devId);
	}

	@Override
	public List<Developer> getAllDevelopers() {
		return developerRepository.findAll();
	}

	@SuppressWarnings("deprecation")
	@Override
	public ResponseEntity<String> editDeveloper(DeveloperDTO developerdto, int devId) {
		Developer getDev = developerRepository.getById(devId);
		getDev.setSkillLevel(developerdto.getSkillLevel());
		getDev.setName(developerdto.getName());
		Developer updateDev = developerRepository.save(getDev);
		
		ResponseEntity.ok().body(updateDev);
		return new ResponseEntity<>("Developer details updated", HttpStatus.OK);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public ResponseEntity<String> blockDeveloper(DeveloperDTO developerdto, int devId) {
		Developer getDev = developerRepository.getById(devId);
		getDev.setBlocked(true);
		Developer updateDev = developerRepository.save(getDev);
		
		ResponseEntity.ok().body(updateDev);
		return new ResponseEntity<>("Developer blocked", HttpStatus.OK);
	}

	@SuppressWarnings("deprecation")
	@Override
	public ResponseEntity<String> unblockDeveloper(DeveloperDTO developerdto, int devId) {
		Developer getDev = developerRepository.getById(devId);
		getDev.setBlocked(false);
		Developer updateDev = developerRepository.save(getDev);
		
		ResponseEntity.ok().body(updateDev);
		return new ResponseEntity<>("Developer unblocked", HttpStatus.OK);
	}
}
