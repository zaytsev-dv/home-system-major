package ru.home.system.major.core.service.realm;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.home.system.artifactory.repository.base.BaseSqlRepository;
import ru.home.system.artifactory.service.base.BaseSqlServiceImpl;
import ru.home.system.major.core.domain.Realm;
import ru.home.system.major.core.repository.RealmRepository;

@Service
@Slf4j
public class RealmServiceImpl extends BaseSqlServiceImpl<Realm, Long> implements RealmService
{
	private final RealmRepository realmRepository;

	public RealmServiceImpl(RealmRepository realmRepository)
	{
		this.realmRepository = realmRepository;
	}

	@Override
	protected BaseSqlRepository<Realm, Long> getRepository()
	{
		return realmRepository;
	}
}
