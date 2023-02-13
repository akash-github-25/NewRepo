package com.Neuron.Exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

public class GlobalExceptionHandler {
	
	@ExceptionHandler(BookException.class)
	public ResponseEntity<MyErrorDetails> handleStudentException(BookException exp,WebRequest req){
	MyErrorDetails err=new MyErrorDetails(LocalDateTime.now(), exp.getMessage(), req.getDescription(false));
	return new ResponseEntity<>(err,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(UserException.class)
	public ResponseEntity<MyErrorDetails> handleStudentException(UserException exp,WebRequest req){
	MyErrorDetails err=new MyErrorDetails(LocalDateTime.now(), exp.getMessage(), req.getDescription(false));
	return new ResponseEntity<>(err,HttpStatus.NOT_FOUND);
	}

	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<MyErrorDetails> ExceptionHandler(Exception se, WebRequest req) {
		
		MyErrorDetails err= new MyErrorDetails();
		err.setTimestamp(LocalDateTime.now());
		err.setMessage(se.getMessage());
		err.setDetails(req.getDescription(false));
		
		return new ResponseEntity<MyErrorDetails>(err,HttpStatus.BAD_REQUEST);
		
	}
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<MyErrorDetails> myMANVExceptionHandler(MethodArgumentNotValidException me) {
	MyErrorDetails err=new MyErrorDetails(LocalDateTime.now(),"Validation Error",me.getBindingResult().getFieldError().getDefaultMessage());
	return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
	}
	
	public static void main(String[] args) {
		

	}

}
