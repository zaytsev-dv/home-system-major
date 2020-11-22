package ru.home.system.major.core.service.product;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.home.system.artifactory.repository.base.BaseSqlRepository;
import ru.home.system.artifactory.service.base.BaseSqlServiceImpl;
import ru.home.system.major.core.domain.Product;
import ru.home.system.major.core.repository.ProductRepository;

@Service
@Slf4j
public class ProductServiceImpl extends BaseSqlServiceImpl<Product, Long> implements ProductService
{
	private final ProductRepository productRepository;

	public ProductServiceImpl(ProductRepository productRepository)
	{
		this.productRepository = productRepository;
	}

	@Override
	protected BaseSqlRepository<Product, Long> getRepository()
	{
		return productRepository;
	}
}
