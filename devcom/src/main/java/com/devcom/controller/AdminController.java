package com.devcom.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devcom.entity.Developer;
import com.devcom.entity.User;
import com.devcom.exception.DeveloperNotFoundException;
import com.devcom.exception.FeedNotFoundException;
import com.devcom.exception.ResponseNotFoundException;
import com.devcom.service.DeveloperService;
import com.devcom.service.FeedService;
import com.devcom.service.ResponseService;
import com.devcom.service.UserService;

@RestController
@CrossOrigin(origins="http://localhost:3000")
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	FeedService feedService;
	
	@Autowired
	ResponseService responseService;
	
	@Autowired
	DeveloperService developerService;
	
	@Autowired
	UserService userService;
	
	
	@GetMapping("/alldetails")
	public List<Developer> getAllDevelopers() {
		return developerService.getAllDevelopers();
	}
	@GetMapping("/alluserdetails")
	public List<User> getAllUser() {
		return userService.getAllUser();
	}
	
	@PutMapping("/blockdeveloper/{devId}")
	public ResponseEntity<Developer> blockUser(@PathVariable("devId") int devId) throws DeveloperNotFoundException {
		Developer savestatus = developerService.blockUser(devId);
		return ResponseEntity.ok().body(savestatus);
		}
	
	@PutMapping("/unblockdeveloper/{devId}")
	public ResponseEntity<Developer> unblockUser(@PathVariable("devId") int devId) throws DeveloperNotFoundException {
		Developer savestatus = developerService.unblockUser(devId);
		return ResponseEntity.ok().body(savestatus);
		}
	@PutMapping("/blockuser/{userId}")
	public ResponseEntity<User> blockUserId(@PathVariable("userId") int userId){
		User savestatus = userService.blockUserId(userId);
		return ResponseEntity.ok().body(savestatus);
	}
	
	@PutMapping("/unblockuser/{userId}")
	public ResponseEntity<User> unblockUserId(@PathVariable("userId") int userId){
		User savestatus = userService.unblockUserId(userId);
		return ResponseEntity.ok().body(savestatus);
	}
	
	@DeleteMapping("/deletefeed/{feedId}")
	public ResponseEntity<String> removeFeed(@PathVariable("feedId") int feedId) throws FeedNotFoundException {
			feedService.removeFeed(feedId);
			return new ResponseEntity<>("Feed Removed", HttpStatus.OK);
		}
	
	@GetMapping("/getdetails/{devId}")
	public Optional<Developer> getDeveloper(@PathVariable int devId ) throws DeveloperNotFoundException {
			return developerService.getDeveloper(devId);	
	}
	
/*	@DeleteMapping("/deletefeed/{feedId}")
	public ResponseEntity<String> removeDeveloper(@PathVariable("feedId") int devId) throws DeveloperNotFoundException {
			feedService.removeFeed(devId);
			return new ResponseEntity<>("Developer Removed", HttpStatus.OK);
		}
	*/
	
	@DeleteMapping("/deleteresponse/{respId}")
	public ResponseEntity<String> removeResponse(@PathVariable( "respId") int respId) throws ResponseNotFoundException {
	     	responseService.removeResponse(respId);
			return new ResponseEntity<>("Response Removed", HttpStatus.OK);	
	}
	
}
