package ru.home.system.major.web.rest;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.home.system.major.core.dto.ApiError;
import ru.home.system.major.core.exceptions.CustomNotFoundException;

import javax.servlet.http.HttpServletRequest;

import java.util.*;
import java.util.stream.Collectors;

import static ru.home.system.major.web.rest.RestExceptionHandlerUtil.buildApiError;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler
{
	@ExceptionHandler({RuntimeException.class})
	public ResponseEntity<ApiError> internalServer(HttpServletRequest req, Exception ex)
	{
		ApiError error = buildApiError(req, ex, HttpStatus.INTERNAL_SERVER_ERROR);
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler({CustomNotFoundException.class})
	public ResponseEntity<ApiError> notFound(HttpServletRequest req, Exception ex)
	{
		ApiError error = buildApiError(req, ex, HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}


	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request)
	{
		List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());
		ApiError error = RestExceptionHandlerUtil.buildApiErrorWithBusinessMSG(status, (ServletWebRequest)request, errors);
		return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
	}
}
