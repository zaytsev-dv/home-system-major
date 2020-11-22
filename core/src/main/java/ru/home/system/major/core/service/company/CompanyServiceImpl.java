package ru.home.system.major.core.service.company;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.home.system.artifactory.repository.base.BaseSqlRepository;
import ru.home.system.artifactory.service.base.BaseSqlServiceImpl;
import ru.home.system.major.core.domain.Company;
import ru.home.system.major.core.repository.CompanyRepository;

@Service
@Slf4j
public class CompanyServiceImpl extends BaseSqlServiceImpl<Company, Long> implements CompanyService
{
	private final CompanyRepository companyRepository;

	public CompanyServiceImpl(CompanyRepository companyRepository)
	{
		this.companyRepository = companyRepository;
	}

	@Override
	protected BaseSqlRepository<Company, Long> getRepository()
	{
		return companyRepository;
	}
}
