package ru.home.system.major.core.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import ru.home.system.artifactory.repository.base.BaseSqlRepository;
import ru.home.system.major.core.domain.UserApp;

import java.util.Optional;

public interface UserAppRepository extends BaseSqlRepository<UserApp, Long>
{
	@EntityGraph(attributePaths = "authorities")
	Optional<UserApp> findOneWithAuthoritiesByUsername(String username);

	@EntityGraph(attributePaths = "authorities")
	Optional<UserApp> findOneWithAuthoritiesByEmailIgnoreCase(String email);
}
