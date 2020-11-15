package ru.home.system.major.core.service;


import ru.home.system.major.core.domain.TelegramCommandKeys;
import ru.home.system.major.core.service.base.BaseService;
import ru.home.system.major.core.service.util.CacheableService;

public interface TelegramCommandKeysService extends BaseService<TelegramCommandKeys, Long>, CacheableService<TelegramCommandMapKey, TelegramCommandKeys>
{
}
