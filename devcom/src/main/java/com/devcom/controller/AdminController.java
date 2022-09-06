package com.devcom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devcom.dto.DeveloperDTO;
import com.devcom.service.DeveloperService;
import com.devcom.service.FeedService;
import com.devcom.service.ResponseService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	FeedService feedService;
	
	@Autowired
	ResponseService responseService;
	
	@Autowired
	DeveloperService developerService;
	
	@PutMapping("/blockdeveloper/{devId}")
	public ResponseEntity<String> blockDeveloper(@PathVariable("devId") int devId, @RequestBody DeveloperDTO developerdto) {
		return this.developerService.blockDeveloper(developerdto,devId);
	}
	
	@PutMapping("/unblockdeveloper/{devId}")
	public ResponseEntity<String> unblockDeveloper(@PathVariable("devId") int devId, @RequestBody DeveloperDTO developerdto) {
		return this.developerService.unblockDeveloper(developerdto,devId);
	}
	
	@DeleteMapping("/deletefeed/{feedId}")
	public String removeFeed(@PathVariable("feedId") int feedId) {
	     
	     return feedService.removeFeed(feedId);
	}
	
	@DeleteMapping("/deleteresponse/{respId}")
	public String removeResponse(@PathVariable( "respId") int respId) {
	     
	     return responseService.removeResponse(respId);
	}
}
