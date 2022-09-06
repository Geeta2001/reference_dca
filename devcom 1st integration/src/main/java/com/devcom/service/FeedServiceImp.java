package com.devcom.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.devcom.dto.FeedDto;
import com.devcom.entity.Developer;
import com.devcom.entity.Feed;
import com.devcom.exception.DeveloperNotFoundException;
import com.devcom.exception.ResponseNotFoundException;
import com.devcom.repository.DeveloperRepository;
import com.devcom.repository.FeedRepository;

@Component
@Service
public class FeedServiceImp implements FeedIService {
	@Autowired
	FeedRepository feedRepository;

	@Autowired
	DeveloperRepository developerrepo;

	@Override
	public Feed addFeed(FeedDto feeddto) {
		Optional<Developer> dev = developerrepo.findById(feeddto.getDevId());
		Feed feed = new Feed();
		feed.setQuery(feeddto.getQuery());
		feed.setTopic(feeddto.getTopic());
		feed.setFeedDate(feeddto.getFeedDate());
		if (dev.isEmpty()) {
			throw new DeveloperNotFoundException();
		}
		feed.setDeveloper(dev.get());
		return feedRepository.save(feed);

	}

	@Override
	public String removeFeed(int feedid) {

		Optional<Feed> feed = feedRepository.findById(feedid);
		if (!feed.isPresent()) {
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
