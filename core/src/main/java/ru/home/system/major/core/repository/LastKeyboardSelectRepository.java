package ru.home.system.major.core.repository;


import ru.home.system.artifactory.repository.base.BaseSqlRepository;
import ru.home.system.major.core.domain.LastKeyboardSelect;

public interface LastKeyboardSelectRepository extends BaseSqlRepository<LastKeyboardSelect, Long>
{
	LastKeyboardSelect findTopByExternalIdOrderByCreatedAtDesc(Long userId);
}
