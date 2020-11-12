package ru.home.system.major.core.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AdapterNotFoundException extends RuntimeException
{
	private static final String ADAPTER_NOT_FOUND = "adapter with name : [%s] not found";
	private static final String ADAPTER_NOT_FOUND_INT = "adapter with id : [%s] not found";
	private static final Logger logger = LoggerFactory.getLogger(AdapterNotFoundException.class);

	public AdapterNotFoundException(String adapterName)
	{
		super(String.format(ADAPTER_NOT_FOUND, adapterName));
		String format = String.format(ADAPTER_NOT_FOUND, adapterName);

		logger.error(format);
	}

	public AdapterNotFoundException(Integer id)
	{
		super(String.format(ADAPTER_NOT_FOUND_INT, id));
		String format = String.format(ADAPTER_NOT_FOUND_INT, id);

		logger.error(format);
	}
}
