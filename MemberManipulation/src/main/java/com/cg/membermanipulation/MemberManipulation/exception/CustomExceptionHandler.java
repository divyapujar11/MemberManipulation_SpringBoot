package com.cg.membermanipulation.MemberManipulation.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(AddressNotValidException.class)
	public Map<String, String> handleAddressNotValidException(AddressNotValidException ex) {
		Map<String, String> errorMap = new HashMap<>();
		errorMap.put("errorMessage", ex.getMessage());
		return errorMap;
	}
	

}
