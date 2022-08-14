package com.example.demo.contact.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(value= UserNotFound.class)
	public ResponseEntity<?> exception(UserNotFound exception){
		return new ResponseEntity<>("user not found" , HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value= NoTagFound.class)
	public ResponseEntity<?> exception(NoTagFound exception){
		return new ResponseEntity<>("no tag found assosiated with this id" , HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value= NoCustomFieldFound.class)
	public ResponseEntity<?> exception(NoCustomFieldFound exception){
		return new ResponseEntity<>("no custom field found assosiated with this id"  , HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value= InvalidEmail.class)
	public ResponseEntity<?> exception(InvalidEmail exception){
		return new ResponseEntity<>("Enter valid Email Id" , HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value= InvalidMobileNo.class)
	public ResponseEntity<?> exception(InvalidMobileNo exception){
		return new ResponseEntity<>("Enter valid Mobile No" , HttpStatus.BAD_REQUEST);
	}
	
}
