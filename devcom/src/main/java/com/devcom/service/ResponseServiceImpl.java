package com.devcom.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
	
	private static final Logger log = LoggerFactory.getLogger(ResponseServiceImpl.class);

	@Autowired
	ResponseRepository responseRepository;
	
	@Autowired
	DeveloperRepository developerRepository;
	
	@Autowired
	FeedRepository feedRepository;
	
	@Override
	public Response addResponse(ResponseDTO responsedto) throws DeveloperNotFoundException, FeedNotFoundException {
		Optional<Developer> developer = developerRepository.findById(responsedto.getDevId());
		Optional<Feed> feed = feedRepository.findById(responsedto.getFeedId());
		Response response= new Response();		
		response.setAnswer(responsedto.getAnswer());
		response.setRespDate(responsedto.getRespDate());
		if(developer.isEmpty()) {
			log.error("developer not found");
			throw new DeveloperNotFoundException();
		}
		response.setDeveloper(developer.get());
		if(feed.isEmpty()) {
			log.error("feed not found");
			throw new FeedNotFoundException();
		}
		response.setFeed(feed.get());
		log.info("response saved");
		return responseRepository.save(response);
	}

	@SuppressWarnings("deprecation")
	@Override
	public Response editResponse(int respId, ResponseDTO responsedto) throws ResponseNotFoundException {
		Optional<Response> resp = responseRepository.findById(responsedto.getRespId());
		if(resp.isEmpty()) {
			log.error("response not found");
			throw new ResponseNotFoundException();
		}
		Response getResp=responseRepository.getById(respId);
		getResp.setAnswer(responsedto.getAnswer());
		return responseRepository.save(getResp);
	}

	@Override
	public String removeResponse(int respId) throws ResponseNotFoundException {
		Optional<Response> resp =responseRepository.findById(respId);
		if(!resp.isPresent()) {
			log.error("response not found");
			throw new ResponseNotFoundException();
		}
		responseRepository.deleteById(respId);
		return "Response Deleted";
	
	}
	
	@Override
	public Optional<Response> getResponse(int respid) throws ResponseNotFoundException {
		Optional<Response> opt = responseRepository.findById(respid);
		if (opt.isEmpty()) {
			throw new ResponseNotFoundException();
		}
		
		return responseRepository.findById(respid);
	}
	@Override
	public List<Response> getAllResponses() {
		
		return  responseRepository.findAll();
	}

	@Override
	public Optional<Response> getResponseByFeed(int feedid) {
		
		return responseRepository.findById(feedid);
	}

//	@Override
//	public List<Response> getResponseByFeed(int rf_fk) {
//		
//		return feedRepository.findAllByFeedId(rf_fk);
//	}

//	@Override
//	public List<Response> getResponseByDeveloper(int devId) throws UnknownDeveloperException {
//		
//		return responseRepository.findById(devId);
//	}

}