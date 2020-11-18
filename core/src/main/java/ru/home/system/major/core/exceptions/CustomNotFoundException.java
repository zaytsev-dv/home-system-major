package ru.home.system.major.core.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CustomNotFoundException extends RuntimeException
{
	private static final String MSG = " [%s] not found";

	public CustomNotFoundException(String msg, String param)
	{
		super(msg + String.format(MSG, param));
	}
}
