package com.pgmanagement.handler;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.pgmanagement.exception.EmailAlreadyExistException;
import com.pgmanagement.response.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(EmailAlreadyExistException.class)
	public ResponseEntity<ErrorResponse> handleEmailAlreadyExistException(EmailAlreadyExistException ex){
		
		ErrorResponse response = new ErrorResponse
							(HttpStatus.CONFLICT.value(), ex.getMessage(), LocalDateTime.now());

		return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException ex){
		
		String message = ex.getBindingResult().getFieldError().getDefaultMessage();
		
		ErrorResponse response = new ErrorResponse(400, message, LocalDateTime.now());
		
		return ResponseEntity.badRequest().body(response);
		
	}

}
