package com.devcom.service;

import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.devcom.dto.ResponseDto;
import com.devcom.entity.Response;

public interface ResponseIService {

	public Response addResponse(ResponseDto responseDto);

	public Response editResponse(int respId, ResponseDto responseDto);

	public String removeResponse(int respId);

	public Optional<Response> getResponse(int responseid);

}
