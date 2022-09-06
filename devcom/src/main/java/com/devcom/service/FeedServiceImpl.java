package com.devcom.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.devcom.dto.FeedDTO;
import com.devcom.entity.Developer;
import com.devcom.entity.Feed;
import com.devcom.exception.DeveloperNotFoundException;
import com.devcom.exception.ResponseNotFoundException;
import com.devcom.repository.DeveloperRepository;
import com.devcom.repository.FeedRepository;

@Service
public class FeedServiceImpl implements FeedService {
	@Autowired
	FeedRepository feedRepository;
	
	@Autowired
	DeveloperRepository developerRepository;	
	
	@Override
	public ResponseEntity<String> addFeed(FeedDTO feeddto) {
		Feed feed1=new Feed();
		Optional<Developer> developer = developerRepository.findById(feeddto.getDevId());
		feed1.setQuery(feeddto.getQuery());
		feed1.setTopic(feeddto.getTopic());
		feed1.setFeedDate(feeddto.getFeedDate());
		if(developer.isEmpty()) {
			throw new DeveloperNotFoundException();
		}
		feed1.setDeveloper(developer.get());
		feedRepository.save(feed1);
		return new ResponseEntity<>("Feed added", HttpStatus.OK);
	}
	
	@Override
	public String removeFeed(int feedid) {
		
		Optional<Feed> feed =feedRepository.findById(feedid);
		if(!feed.isPresent()) {
			throw new ResponseNotFoundException();
		}
		feedRepository.deleteById(feedid);
        return "Feed Deleted";
	
	}
	@Override
	public Optional<Feed> getFeed(int feedid) {
		return feedRepository.findById(feedid);
	}

}
