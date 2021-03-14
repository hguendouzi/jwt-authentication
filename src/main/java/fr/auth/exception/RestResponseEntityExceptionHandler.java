package fr.auth.exception;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author hicham
 *
 */
@Slf4j
@RestControllerAdvice
public class RestResponseEntityExceptionHandler {

	@ExceptionHandler(GlobalException.class)
	public ResponseEntity<List<MessageError>> handleGlobalException(GlobalException ex) {
		log.error("Handling gloablException with message [{}]", ex.getMessage());
		MessageError messageError = new MessageError(ex.getField(), ex.getMessage());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonList(messageError));
	}
	
	/***
	 * validation exception 
	 * send message error with field
	 * @param ex
	 * @return list of messages errors
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<List<MessageError>> validationException(MethodArgumentNotValidException ex) {
		List<MessageError> results=new ArrayList<>();
		 ex.getBindingResult().getAllErrors()
	      .stream()
	      .filter(FieldError.class::isInstance)
	      .map(FieldError.class::cast)
	      .forEach(fieldError -> results.add(new MessageError(fieldError.getField(), fieldError.getDefaultMessage())));
		log.error("validation exception [{}]", results.toString());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(results);
	}


}
