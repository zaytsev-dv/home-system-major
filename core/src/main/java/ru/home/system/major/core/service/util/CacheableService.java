package ru.home.system.major.core.service.util;

import java.util.Map;

public interface CacheableService<T,S>
{
	Map<T,S> findAllCacheable();
}
