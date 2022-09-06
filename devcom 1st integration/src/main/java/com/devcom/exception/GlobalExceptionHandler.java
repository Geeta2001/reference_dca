package com.devcom.exception;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<>(ex.getBindingResult().getAllErrors().get(0).getDefaultMessage(), HttpStatus.BAD_REQUEST);
    }

	@ExceptionHandler(value = DeveloperNotFoundException.class)
	public ResponseEntity<String> developerNotFoundExceptionHandler(DeveloperNotFoundException ex) {
		return new ResponseEntity<>("Developer Not Found", HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = UserNotFoundException.class)
	public ResponseEntity<String> userNotFoundExceptionHandler(UserNotFoundException ex) {
		return new ResponseEntity<>("User Not Found", HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = InvalidCredentialsException.class)
	public ResponseEntity<String> invalidCredentialsHandler(InvalidCredentialsException ex) {
		return new ResponseEntity<>("Invalid Credentials", HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = ResponseNotFoundException.class)
	public ResponseEntity<String> responseNotFoundExceptionHandler(ResponseNotFoundException ex) {

		return new ResponseEntity<>("Response Not Found", HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = UserExistsException.class)
	public ResponseEntity<String> userExistsExceptionHandler(UserExistsException ex) {

		return new ResponseEntity<>("User already exists", HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = FeedNotFoundException.class)
	public ResponseEntity<String> feedNotFoundExceptionHandler(FeedNotFoundException ex) {

		return new ResponseEntity<>("Feed Not Found", HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = DeveloperAlreadyExistsException.class)
	public ResponseEntity<String> developerAlreadyExistsException(DeveloperAlreadyExistsException ex){
		return new ResponseEntity<>("Developer already exists", HttpStatus.NOT_FOUND);
	}

}
