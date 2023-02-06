package com.firstapispring.api.exeptionhandler;


import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.firstapispring.domain.exeption.NegocioException;

import org.springframework.lang.Nullable;


@ControllerAdvice
public class ApiExeptionHandler extends ResponseEntityExceptionHandler {
	
	
    @Override
    @Nullable
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        // TODO Auto-generated method stub
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) ->{

            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });
        return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST);

    }

	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<Object> handleNegocioException(NegocioException ex, WebRequest request){
		var status = HttpStatus.BAD_REQUEST;
		var problema = new Problema();
		problema.setStatus(status.value());
		problema.setTitulo(ex.getMessage());
		problema.setData(OffsetDateTime.now());
		return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
	}

}

