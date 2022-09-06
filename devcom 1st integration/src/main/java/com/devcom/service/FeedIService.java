package com.devcom.service;

import java.util.Optional;
import org.springframework.http.ResponseEntity;
import com.devcom.dto.FeedDto;
import com.devcom.entity.Feed;

public interface FeedIService {
	public Feed addFeed(FeedDto feed);

	public String removeFeed(int feedid);

	public Optional<Feed> getFeed(int feedid);
}
