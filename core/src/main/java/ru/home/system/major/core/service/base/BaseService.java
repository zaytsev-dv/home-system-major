package ru.home.system.major.core.service.base;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public interface BaseService<T, ID extends Serializable>
{
	T save(T entity);

	T getById(ID id);

	void delete(T entity);

	List<T> findAll();

	List<T> findAll(List<ID> ids);

	List<T> saveAll(List<T> var1);

	Page<T> findAll(Pageable pageable);
}
