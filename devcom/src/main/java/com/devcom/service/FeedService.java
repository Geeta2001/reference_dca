package com.devcom.service;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.devcom.dto.FeedDTO;
import com.devcom.entity.Feed;

public interface FeedService {
	public ResponseEntity<String> addFeed(FeedDTO feeddto);
	public String removeFeed(int feedid);
	public Optional<Feed> getFeed(int feedid);
}
