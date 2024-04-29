package com.eviden.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.eviden.exception.GlobalException;
import com.eviden.model.ApiError;

@RestControllerAdvice
public class GlobalExceptionController {
	
	@ExceptionHandler(GlobalException.class)
	public ResponseEntity<ApiError> handleElementNotFoundException(GlobalException e){
		ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, e.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
		
	}

}
