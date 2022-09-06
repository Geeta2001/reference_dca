package com.devcom.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.devcom.dto.ResponseDto;
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
public class ResponseServiceImpl implements ResponseIService {

	@Autowired
	ResponseRepository respRepo;

	@Autowired
	DeveloperRepository developerrepo;

	@Autowired
	FeedRepository feedRepo;

	@Override
	public Response addResponse(ResponseDto responseDto) {
		Optional<Developer> dev = developerrepo.findById(responseDto.getDevId());
		Optional<Feed> feed = feedRepo.findById(responseDto.getFeedId());
		Response response = new Response();
		response.setAnswer(responseDto.getAnswer());
		response.setRespDate(responseDto.getRespDate());
		if (dev.isEmpty()) {
			throw new DeveloperNotFoundException();
		}
		response.setDeveloper(dev.get());
		if (feed.isEmpty()) {
			throw new FeedNotFoundException();
		}
		response.setFeed(feed.get());
		return respRepo.save(response);
	}

	@SuppressWarnings("deprecation")
	@Override
	public Response editResponse(int respId, ResponseDto responseDto) {

		Response getResp = respRepo.getById(respId);
		getResp.setAnswer(responseDto.getAnswer());

		return respRepo.save(getResp);

	}

	@Override
	public String removeResponse(int respId) {

		Optional<Response> resp = respRepo.findById(respId);
		if (!resp.isPresent()) {
			throw new ResponseNotFoundException();
		}
		respRepo.deleteById(respId);
		return "Response Deleted";

	}

	@Override
	public Optional<Response> getResponse(int responseid) {
		return respRepo.findById(responseid);
	}
}
