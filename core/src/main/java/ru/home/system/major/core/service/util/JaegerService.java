package ru.home.system.major.core.service.util;

import io.opentracing.Scope;
import io.opentracing.Span;
import io.opentracing.Tracer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import ru.home.system.major.core.config.StaticContextInitializer;

import java.util.Map;


@Slf4j
public class JaegerService
{
	public static Tracer getTracer()
	{
		return StaticContextInitializer.getBean(Tracer.class);
	}

	private JaegerService()
	{
	}

	public static void traceVoid(Runnable runnable, String methodName, Map<String, String> params)
	{
		Span span = getTracer().buildSpan(methodName).start();

		if (!CollectionUtils.isEmpty(params))
		{
			params.forEach(span::setTag);
		}

		try (Scope ignore = getTracer().activateSpan(span))
		{
			runnable.run();
		}

		finally
		{
			span.finish();
		}
	}
}
