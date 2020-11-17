package ru.home.system.major.web.rest;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.home.system.major.core.dto.ApiError;

import javax.servlet.http.HttpServletRequest;

import static ru.home.system.major.web.rest.RestExceptionHandlerUtil.buildApiError;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler
{
	@ExceptionHandler({RuntimeException.class})
	public ResponseEntity<ApiError> notFoundExceptionHandler(HttpServletRequest req, Exception ex)
	{
		ApiError error = buildApiError(req, ex, HttpStatus.INTERNAL_SERVER_ERROR);
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
