package ru.home.system.major.core.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class HandlerNotFoundException extends RuntimeException
{
	private static final String HANDLER_NOT_FOUND = "HANDLER with name : [%s] not found";
	private static final Logger logger = LoggerFactory.getLogger(HandlerNotFoundException.class);

	public HandlerNotFoundException(String adapterName)
	{
		super(String.format(HANDLER_NOT_FOUND, adapterName));
		String format = String.format(HANDLER_NOT_FOUND, adapterName);

		logger.error(format);
	}
}
