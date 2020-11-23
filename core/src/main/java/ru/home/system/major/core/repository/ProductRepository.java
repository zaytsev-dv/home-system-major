package ru.home.system.major.core.repository;


import org.springframework.data.jpa.repository.Query;
import ru.home.system.artifactory.repository.base.BaseSqlRepository;
import ru.home.system.major.core.domain.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository extends BaseSqlRepository<Product, Long>
{
	@Query("select p from Product p where upper(p.realm.value) =?1 and p.price <= ?2")
	List<Product> getAllByRealmAndPrice(String realm, BigDecimal price);
}
