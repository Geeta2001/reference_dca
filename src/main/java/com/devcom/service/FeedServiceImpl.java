package com.devcom.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devcom.dto.FeedDTO;
import com.devcom.entity.Developer;
import com.devcom.entity.Feed;
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
	public Feed addFeed(FeedDTO feeddto) {
		Feed feed1=new Feed();
		Optional<Developer> developer = developerRepository.findById(feeddto.getDevId());
		feed1.setQuery(feeddto.getQuery());
		feed1.setTopic(feeddto.getTopic());
		feed1.setFeedDate(feeddto.getFeedDate());
		feed1.setDeveloper(developer.get());
		return  feedRepository.save(feed1);
		
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
		// TODO Auto-generated method stub
		return feedRepository.findById(feedid);
	}

}
