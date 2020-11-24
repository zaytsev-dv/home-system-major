package ru.home.system.major.core.service.util;

import io.opentracing.Scope;
import io.opentracing.Span;
import io.opentracing.Tracer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import ru.home.system.artifactory.exceptions.CustomNotFoundException;

import java.util.Map;
import java.util.function.Supplier;

@Slf4j
public class TryCatchService
{
	private static Tracer tracer;

	public static void setTracer(Tracer tracer)
	{
		TryCatchService.tracer = tracer;
	}

	public static Tracer getTracer()
	{
		return tracer;
	}

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
		catch (Throwable ex)
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

	public static <T> T runReturnedTraced(Supplier<T> supplier, String methodName, Map<String, String> params)
	{
		Span span = getTracer().buildSpan(methodName).start();

		if (!CollectionUtils.isEmpty(params))
		{
			params.forEach(span::setTag);
		}

		try (Scope ignore = getTracer().activateSpan(span))
		{
			return supplier.get();
		}
		catch (CustomNotFoundException e)
		{
			throw e;
		}
		catch (Throwable e)
		{
			span.log(e.getMessage());
			log.error(e.getMessage(), e);
			throw new RuntimeException("Internal server error");
		}
		finally
		{
			span.finish();
		}
	}
}
