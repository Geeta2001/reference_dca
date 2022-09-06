package com.devcom.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.devcom.dto.ResponseDTO;
import com.devcom.entity.Developer;
import com.devcom.entity.Feed;
import com.devcom.entity.Response;
import com.devcom.exception.DeveloperNotFoundException;
import com.devcom.exception.FeedNotFoundException;
import com.devcom.exception.ResponseNotFoundException;
import com.devcom.repository.DeveloperRepository;
import com.devcom.repository.FeedRepository;
import com.devcom.repository.ResponseRepository;

@Service
public class ResponseServiceImpl implements ResponseService {
	
	@Autowired
	ResponseRepository responseRepository;
	
	@Autowired
	DeveloperRepository developerRepository;
	
	@Autowired
	FeedRepository feedRepository;
	
	@Override
	public ResponseEntity<String> addResponse(ResponseDTO responsedto) {
		Optional<Developer> developer = developerRepository.findById(responsedto.getDevId());
		Optional<Feed> feed = feedRepository.findById(responsedto.getFeedId());
		Response response= new Response();		
		response.setAnswer(responsedto.getAnswer());
		response.setRespDate(responsedto.getRespDate());
		if(developer.isEmpty()) {
			throw new DeveloperNotFoundException();
		}
		response.setDeveloper(developer.get());
		if(feed.isEmpty()) {
			throw new FeedNotFoundException();
		}
		response.setFeed(feed.get());
		responseRepository.save(response);
		return new ResponseEntity<>("Response added", HttpStatus.OK);
	}

	@SuppressWarnings("deprecation")
	@Override
	public ResponseEntity<String> editResponse(int respId, ResponseDTO responsedto) {
		
		Response getResp=responseRepository.getById(respId);
		getResp.setAnswer(responsedto.getAnswer());
		
		Response updateResp= responseRepository.save(getResp);
		
		ResponseEntity.ok().body(updateResp);
		return new ResponseEntity<>("Response updated", HttpStatus.OK);
	}

	@Override
	public String removeResponse(int respId) {
		
		Optional<Response> resp =responseRepository.findById(respId);
		if(!resp.isPresent()) {
			throw new ResponseNotFoundException();
		}
		responseRepository.deleteById(respId);
		return "Response Deleted";
	
	}
	
	@Override
	public Optional<Response> getResponse(int respid) {
		return responseRepository.findById(respid);
	}
}