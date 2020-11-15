package ru.home.system.major.web.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import ru.home.system.major.core.service.util.CacheableService;

import java.util.List;

@Configuration
@Slf4j
public class CacheConfiguration
{
	private final List<CacheableService> cacheableServices;

	public CacheConfiguration(List<CacheableService> cacheableServices)
	{
		this.cacheableServices = cacheableServices;
	}

	@EventListener(ContextRefreshedEvent.class)
	public void postCaching()
	{
		log.info("init cache");
		cacheableServices.forEach(CacheableService::findAllCacheable);
	}
}
