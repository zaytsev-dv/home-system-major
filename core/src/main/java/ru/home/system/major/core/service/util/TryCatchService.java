package ru.home.system.major.core.service.util;

import lombok.extern.slf4j.Slf4j;
import ru.home.system.major.core.exceptions.CustomNotFoundException;

import java.util.function.Supplier;

@Slf4j
public class TryCatchService
{
	private TryCatchService()
	{
	}

	public static void runVoid(Runnable runnable)
	{
		try
		{
			runnable.run();
		}
		catch (CustomNotFoundException e)
		{
			throw e;
		}
		catch (Exception ex)
		{
			log.error(ex.getMessage(), ex);
			throw new RuntimeException("Internal server error");
		}

	}

	public static <T> T runReturned(Supplier<T> supplier)
	{
		try
		{
			return supplier.get();
		}
		catch (CustomNotFoundException e)
		{
			throw e;
		}
		catch (Exception ex)
		{
			log.error(ex.getMessage(), ex);
			throw new RuntimeException("Internal server error");
		}

	}
}
