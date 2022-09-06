package com.devcom.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devcom.dto.DeveloperDTO;
import com.devcom.dto.FeedDTO;
import com.devcom.dto.ResponseDTO;
import com.devcom.entity.Developer;
import com.devcom.entity.Feed;
import com.devcom.entity.Response;
import com.devcom.service.DeveloperService;
import com.devcom.service.FeedService;
import com.devcom.service.ResponseService;

@RestController
@RequestMapping("/developers")
public class DeveloperController {
	 
	@Autowired
	DeveloperService developerService;
	
	@PostMapping("/adddetails")
	public Developer addDeveloper(@RequestBody DeveloperDTO developerdto) {
		return developerService.addDeveloper(developerdto);
	}
	
	@GetMapping("/getdetails/{devId}")
	public Optional<Developer> getDeveloper(@PathVariable int devId ) {
		return developerService.getDeveloper(devId);
		
	}
	
	@GetMapping("/alldetails")
	public List<Developer> getAllDevelopers() {
		return developerService.getAllDevelopers();
	}
	
	@PutMapping("/editdetails/{devId}")
	public ResponseEntity<Developer> editDeveloper(@PathVariable("devId") int devId, @RequestBody DeveloperDTO developerdto) {
		return this.developerService.editDeveloper(developerdto,devId);
	}
	
	@Autowired
	FeedService feedService;
	
	@PostMapping("/addfeed")
	public Feed addFeed(@RequestBody FeedDTO feeddto)
	{
		return feedService.addFeed(feeddto);
	}
	
	@GetMapping("/getfeed/{feedid}")
	public Optional<Feed> getFeed(@PathVariable int feedid ) {
		return feedService.getFeed(feedid);
	}
	
	@Autowired
	ResponseService responseService;
	
	@PostMapping("/add")
	public Response addResponse(@RequestBody ResponseDTO responsedto) {
		
		return responseService.addResponse(responsedto);
	}
	
	@PutMapping("/edit/{respId}")
	public ResponseEntity<Response> editResponse(@PathVariable("respId") int respId,@RequestBody ResponseDTO responsedto) {
		return this.responseService.editResponse(respId, responsedto);
	}
	

	@GetMapping("/getresponse/{respid}")
	public Optional<Response> getResponse(@PathVariable int respid ) {
		return responseService.getResponse(respid);
		
	}
}