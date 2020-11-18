package ru.home.system.major.web.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.ServletWebRequest;
import ru.home.system.major.core.dto.ApiError;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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

	static ApiError buildApiErrorWithBusinessMSG(HttpStatus status, ServletWebRequest request, List<String> errors)
	{
		return ApiError.builder()
				.status(status)
				.code(status.value())
				.message("Validation error")
				.validationErrors(errors)
				.path(request.getRequest().getRequestURI())
				.build();
	}
}
