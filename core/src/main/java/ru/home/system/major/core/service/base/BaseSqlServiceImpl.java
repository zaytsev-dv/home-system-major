package ru.home.system.major.core.service.base;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.home.system.major.core.repository.base.BaseSqlRepository;

import java.io.Serializable;
import java.util.List;

public abstract class BaseSqlServiceImpl<T, ID extends Serializable> implements BaseService<T, ID>
{
	protected abstract BaseSqlRepository<T, ID> getRepository();

	public T save(T data)
	{
		return getRepository().save(data);
	}

	public T getById(ID id)
	{
		return getRepository().findById(id).orElse(null);
	}

	public void delete(T entity)
	{
		this.getRepository().delete(entity);
	}

	public List<T> findAll()
	{
		return (List<T>) getRepository().findAll();
	}

	public List<T> findAll(List<ID> ids)
	{
		return (List<T>) getRepository().findAllById(ids);
	}

	public List<T> saveAll(List<T> data)
	{
		return (List<T>) getRepository().saveAll(data);
	}

	public Page<T> findAll(Pageable pageable)
	{
		return getRepository().findAll(pageable);
	}
}
