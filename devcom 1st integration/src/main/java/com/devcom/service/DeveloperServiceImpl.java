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
import com.devcom.repository.UserRepo;

@Service
public class DeveloperServiceImpl implements DeveloperIService {

	@Autowired
	DeveloperRepository developerRepository;

	@Autowired
	UserRepo userrepo;

	@Override
	public Developer addDeveloper(DeveloperDTO devDTO) {
		Optional<User> user = userrepo.findById(devDTO.getUserId());
		Developer developer = new Developer();

		developer.setName(devDTO.getName());
		developer.setEmail(devDTO.getEmail());
		developer.setSkillLevel(devDTO.getSkillLevel());
		if (user.isEmpty()) {
			throw new UserNotFoundException();
		}
		developer.setUser(user.get());
		return developerRepository.save(developer);

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
	public Developer editDeveloper(DeveloperDTO developerdto, int devId) {
		Developer getDev = developerRepository.getById(devId);
		getDev.setSkillLevel(developerdto.getSkillLevel());
		getDev.setName(developerdto.getName());

		return developerRepository.save(getDev);
	}

	@SuppressWarnings("deprecation")
	@Override
	public Developer blockuser(int devId) {
		Developer dev = developerRepository.getById(devId);
		dev.setBlocked(true);
		return developerRepository.save(dev);
	}

	@SuppressWarnings("deprecation")
	@Override
	public Developer unblockuser(int devId) {
		Developer dev = developerRepository.getById(devId);
		dev.setBlocked(false);
		return developerRepository.save(dev);
	}

}
