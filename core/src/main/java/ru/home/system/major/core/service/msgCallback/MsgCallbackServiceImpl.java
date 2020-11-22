package ru.home.system.major.core.service.msgCallback;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.home.system.artifactory.repository.base.BaseSqlRepository;
import ru.home.system.artifactory.service.base.BaseSqlServiceImpl;
import ru.home.system.major.core.domain.MsgCallback;
import ru.home.system.major.core.repository.MsgCallbackRepository;

@Service
@Slf4j
public class MsgCallbackServiceImpl extends BaseSqlServiceImpl<MsgCallback, Long> implements MsgCallbackService
{
	private final MsgCallbackRepository msgCallbackRepository;

	public MsgCallbackServiceImpl(MsgCallbackRepository msgCallbackRepository)
	{
		this.msgCallbackRepository = msgCallbackRepository;
	}

	@Override
	protected BaseSqlRepository<MsgCallback, Long> getRepository()
	{
		return msgCallbackRepository;
	}
}
