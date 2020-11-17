package ru.home.system.major.web.rest;

import org.springframework.http.HttpStatus;
import ru.home.system.major.core.dto.ApiError;

import javax.servlet.http.HttpServletRequest;

public class RestExceptionHandlerUtil
{
	private RestExceptionHandlerUtil()
	{
	}

	static ApiError buildApiError(HttpServletRequest req, Exception ex, HttpStatus httpStatus)
	{
		return ApiError.builder()
				.status(httpStatus)
				.code(httpStatus.value())
				.message(ex.getCause() == null ? ex.getMessage() : ex.getCause().getCause().getMessage())
				.path(req.getRequestURI())
				.build();
	}
}
