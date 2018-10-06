package com.example.ticket.ticketbooking.controller;

import java.util.Date;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.csrf.InvalidCsrfTokenException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.UnsupportedMediaTypeStatusException;

import com.example.ticket.ticketbooking.model.CustomDTO;

@ControllerAdvice
@RestController
public class ExceptionController {

	@ExceptionHandler({ MethodArgumentNotValidException.class, HttpMessageNotReadableException.class })
	public ResponseEntity<CustomDTO> notValidRequestException(Exception ex, WebRequest req) {
		CustomDTO error = new CustomDTO(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(),
				ex.getMessage(), new Date(), "Error");
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<CustomDTO> notFoundException(Exception ex, WebRequest req) {
		CustomDTO error = new CustomDTO(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase(),
				ex.getMessage(), new Date(), "Error");
		return new ResponseEntity<CustomDTO>(error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler({ InvalidCsrfTokenException.class, AuthenticationException.class, AccessDeniedException.class })
	public ResponseEntity<CustomDTO> authenticationException(Exception ex, WebRequest req) {
		CustomDTO error = new CustomDTO(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase(),
				ex.getMessage(), new Date(), "Error");
		return new ResponseEntity<CustomDTO>(error, HttpStatus.UNAUTHORIZED);

	}

	@ExceptionHandler({ UnsupportedMediaTypeStatusException.class, HttpRequestMethodNotSupportedException.class,
			HttpMediaTypeNotAcceptableException.class, HttpMediaTypeNotSupportedException.class })
	public ResponseEntity<CustomDTO> unsupportedMediaException(Exception ex, WebRequest req) {
		CustomDTO error = new CustomDTO(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value(),
				HttpStatus.UNSUPPORTED_MEDIA_TYPE.getReasonPhrase(), ex.getMessage(), new Date(), "Error");
		return new ResponseEntity<CustomDTO>(error, HttpStatus.UNSUPPORTED_MEDIA_TYPE);

	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<CustomDTO> sqlException(Exception ex, WebRequest req) {
		CustomDTO error = new CustomDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(),
				HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), ex.getMessage(), new Date(), "Error");
		return new ResponseEntity<CustomDTO>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
