package com.devcom.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devcom.entity.Developer;
import com.devcom.entity.Feed;
import com.devcom.entity.Response;
import com.devcom.exception.FeedNotFoundException;
import com.devcom.exception.ResponseNotFoundException;
import com.devcom.repository.FeedRepository;
import com.devcom.repository.ResponseRepository;
import com.devcom.service.DeveloperServiceImpl;
import com.devcom.service.FeedIService;
import com.devcom.service.ResponseIService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	FeedIService feedService;

	@Autowired
	ResponseIService respService;

	@Autowired
	DeveloperServiceImpl devService;
	
	@Autowired
	FeedRepository feedRepo;
	
	@Autowired
	ResponseRepository responseRepo;

	@PutMapping("/blockuser/{devId}")
	public ResponseEntity<Developer> blockUser(@PathVariable("devId") int devId) {
		
		Developer savestatus = devService.blockuser(devId);
		return ResponseEntity.ok().body(savestatus);

	}

	@PutMapping("/unblockuser/{devId}")
	public ResponseEntity<Developer> unblock(@PathVariable("devId") int devId) {
		Developer savestatus = devService.unblockuser(devId);
		return ResponseEntity.ok().body(savestatus);
	}

	@DeleteMapping("/deletefeed/{feedId}")
	public ResponseEntity<String> removeFeed(@PathVariable("feedId") int feedId) {
		Optional<Feed> opt = feedRepo.findById(feedId);
		if(opt.isEmpty()) {
			throw new FeedNotFoundException();
		}else {
			feedService.removeFeed(feedId);
			return new ResponseEntity<>("Feed Removed", HttpStatus.OK);
		}
	}

	@DeleteMapping("/deleteresponse/{respId}")
	public ResponseEntity<String> removeResponse(@PathVariable("respId") int respId) {
		Optional<Response> opt = responseRepo.findById(respId);
		if(opt.isEmpty()) {
			throw new ResponseNotFoundException();
		}else {
			respService.removeResponse(respId);
			return new ResponseEntity<>("Response Removed", HttpStatus.OK);
		}
	}

}
