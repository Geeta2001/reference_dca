package com.devcom.service;

import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.devcom.dto.ResponseDTO;
import com.devcom.entity.Response;

public interface ResponseService {
	public ResponseEntity<String> addResponse(ResponseDTO responsedto);

	public ResponseEntity<String> editResponse(int respId, ResponseDTO responsedto);
	
	public String removeResponse(int respId);

	public Optional<Response> getResponse(int respid);
	
}
