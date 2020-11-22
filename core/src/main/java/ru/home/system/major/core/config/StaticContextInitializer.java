package ru.home.system.major.core.config;

import io.opentracing.Tracer;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import ru.home.system.major.core.service.util.TryCatchService;

import javax.annotation.PostConstruct;

@Component
public class StaticContextInitializer implements ApplicationContextAware
{
	private final Tracer tracer;
	private ApplicationContext applicationContext;
	private static StaticContextInitializer instance;

	public StaticContextInitializer(Tracer tracer)
	{
		this.tracer = tracer;
	}

	@PostConstruct
	public void init()
	{
		instance = this;
		TryCatchService.setTracer(tracer);
	}


	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException
	{
		this.applicationContext = applicationContext;
	}

	public static <T> T getBean(Class<T> clazz)
	{
		return instance.applicationContext.getBean(clazz);
	}

}
