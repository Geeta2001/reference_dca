package com.devcom.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devcom.dto.DeveloperDTO;
import com.devcom.dto.FeedDto;
import com.devcom.dto.ResponseDto;
import com.devcom.entity.Developer;
import com.devcom.entity.Feed;
import com.devcom.entity.Response;
import com.devcom.exception.DeveloperAlreadyExistsException;
import com.devcom.exception.DeveloperNotFoundException;
import com.devcom.exception.FeedNotFoundException;
import com.devcom.exception.ResponseNotFoundException;
import com.devcom.repository.DeveloperRepository;
import com.devcom.repository.FeedRepository;
import com.devcom.repository.ResponseRepository;
import com.devcom.service.DeveloperIService;
import com.devcom.service.FeedIService;
import com.devcom.service.ResponseIService;

@RestController
@RequestMapping("/developers")
public class DeveloperController {

	@Autowired
	DeveloperIService developerService;

	@Autowired
	FeedIService feedService;

	@Autowired
	ResponseIService respService;

	@Autowired
	DeveloperRepository devRepo;

	@Autowired
	FeedRepository feedRepo;

	@Autowired
	ResponseRepository respRepo;

	@PostMapping("/adddetails")
	public ResponseEntity<String> addDeveloper(@RequestBody DeveloperDTO developerdto) {
		Optional<Developer> opt = devRepo.findByEmail(developerdto.getEmail());
		if (opt.isPresent()) {
			throw new DeveloperAlreadyExistsException();
		} else {
			developerService.addDeveloper(developerdto);
			return new ResponseEntity<>("Success", HttpStatus.OK);
		}
	}

	@GetMapping("/getdetails/{devId}")
	public Optional<Developer> getDeveloper(@PathVariable int devId) {
		Optional<Developer> opt = devRepo.findById(devId);
		if (opt.isEmpty()) {
			throw new DeveloperNotFoundException();
		} else {
			return developerService.getDeveloper(devId);
		}
	}

	@GetMapping("/alldetails")
	public List<Developer> getAllDevelopers() {
		return developerService.getAllDevelopers();
	}

	@PutMapping("/editdetails/{devId}")
	public ResponseEntity<Developer> editDeveloper(@PathVariable("devId") int devId,
			@RequestBody DeveloperDTO developerdto) {
		Developer updateDev = developerService.editDeveloper(developerdto, devId);
		return ResponseEntity.ok().body(updateDev);
	}

	@PostMapping("/addfeed")
	public ResponseEntity<String> addFeed(@RequestBody FeedDto feeddto) {
		feedService.addFeed(feeddto);
		return new ResponseEntity<>("Success", HttpStatus.OK);
	}

	@GetMapping("/getfeeddetails/{feedid}")
	public Optional<Feed> getFeed(@PathVariable int feedid) {
		Optional<Feed> opt = feedRepo.findById(feedid);
		if (opt.isEmpty()) {
			throw new FeedNotFoundException();
		} else {
			return feedService.getFeed(feedid);
		}

	}

	@PostMapping("/addresponse")
	public Response addResponse(@RequestBody ResponseDto responseDto) {
		return respService.addResponse(responseDto);
	}

	@PutMapping("/edit/{respId}")
	public ResponseEntity<Response> editResponse(@PathVariable("respId") int respId,
			@RequestBody ResponseDto responseDto) {
		Response updateResp = respService.editResponse(respId, responseDto);
		return ResponseEntity.ok().body(updateResp);

	}

	@GetMapping("/getresponsedetails/{responseid}")
	public Optional<Response> getResponse(@PathVariable int responseid) {
		Optional<Response> opt = respRepo.findById(responseid);
		if (opt.isEmpty()) {
			throw new ResponseNotFoundException();
		} else {
			return respService.getResponse(responseid);
		}

	}
}
