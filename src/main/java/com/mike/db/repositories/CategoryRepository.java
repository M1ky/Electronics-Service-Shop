package com.mike.db.repositories;

import com.mike.db.entities.Category;
import com.mike.db.entities.Item;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category, Long>
{
	@Override
	void deleteById(Long aLong);

	@Override
	<S extends Category> S save(S s);

	@Override
	Optional<Category> findById(Long aLong);

	@Override
	Iterable<Category> findAll();
}