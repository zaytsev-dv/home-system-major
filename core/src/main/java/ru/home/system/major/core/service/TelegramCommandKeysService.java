package ru.home.system.major.core.service;


import ru.home.system.major.core.domain.TelegramCommandKeys;
import ru.home.system.major.core.service.util.CacheableService;
import ru.home.system.artifactory.service.base.BaseService;

public interface TelegramCommandKeysService extends BaseService<TelegramCommandKeys, Long>, CacheableService<TelegramCommandMapKey, TelegramCommandKeys>
{
}
