package ru.home.system.major.core.service.lastKeyboardSelect;


import org.telegram.telegrambots.meta.api.objects.Update;
import ru.home.system.artifactory.service.base.BaseService;
import ru.home.system.major.core.domain.LastKeyboardSelect;

public interface LastKeyboardSelectService extends BaseService<LastKeyboardSelect, Long>
{
	LastKeyboardSelect findTopByExternalIdOrderByCreatedAtDesc(Long userId);
	void create(Update update, String type);
}
