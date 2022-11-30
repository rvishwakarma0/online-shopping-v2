package com.item.controllers;

import javax.websocket.DecodeException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.item.entities.ErrorResponse;


@RestControllerAdvice
public class ExceptionConfiguration extends ResponseEntityExceptionHandler {

    @ExceptionHandler(JsonMappingException.class) // Or whatever exception type you want to handle
    public ResponseEntity<ErrorResponse> handleConverterErrors(JsonMappingException exception) { // Or whatever exception type you want to handle
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("Invalid Inputs"));
    }
    
    
    @ExceptionHandler(DecodeException.class) // Or whatever exception type you want to handle
    public ResponseEntity<ErrorResponse> handleDecodeExceptionErrors(DecodeException exception) { // Or whatever exception type you want to handle
        return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body(new ErrorResponse(HttpStatus.FAILED_DEPENDENCY.value(),"Invalid inputs or failed dependency"));
    }
    
    @ExceptionHandler(NullPointerException.class) // Or whatever exception type you want to handle
    public ResponseEntity<ErrorResponse> handleNullPointerExceptionErrors(NullPointerException exception) { // Or whatever exception type you want to handle
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(HttpStatus.BAD_REQUEST.value(),"Invalid inputs or bad request"));
    }
    
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	public ErrorResponse handleException(Exception ex)
	{
		return new ErrorResponse(HttpStatus.CONFLICT.value(),
                       ex.getMessage());
	}

}
