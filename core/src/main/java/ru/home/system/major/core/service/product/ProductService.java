package ru.home.system.major.core.service.product;


import ru.home.system.artifactory.service.base.BaseService;
import ru.home.system.major.core.domain.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService extends BaseService<Product, Long>
{
	List<Product> getAllByRealmAndPrice(String realm, BigDecimal price);
}
