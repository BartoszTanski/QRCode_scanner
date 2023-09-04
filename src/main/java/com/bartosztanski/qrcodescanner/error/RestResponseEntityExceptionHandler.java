package com.bartosztanski.qrcodescanner.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.bartosztanski.qrcodescanner.model.ErrorMessage;
import com.google.zxing.NotFoundException;


@ControllerAdvice
@ResponseStatus
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<ErrorMessage> fileNotFoundException(NotFoundException notFoundException, 
																					WebRequest webRequest) {
		ErrorMessage message = new ErrorMessage(HttpStatus.NOT_FOUND,notFoundException.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
	}
	@ExceptionHandler(ArgumentNotValidException.class)
	public ResponseEntity<ErrorMessage> argumentNotValidException(ArgumentNotValidException argumentNotValidException, 
																					WebRequest webRequest) {
		ErrorMessage message = new ErrorMessage(HttpStatus.NOT_FOUND,argumentNotValidException.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
	}
	@ExceptionHandler(ImageScanFailureException.class)
	public ResponseEntity<ErrorMessage> imageScanFailureException(ImageScanFailureException imageScanFailureException, 
																					WebRequest webRequest) {
		ErrorMessage message = new ErrorMessage(HttpStatus.NOT_FOUND,imageScanFailureException.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
	}
	
	
}
