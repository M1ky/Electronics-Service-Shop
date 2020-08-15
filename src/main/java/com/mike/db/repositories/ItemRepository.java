package com.mike.db.repositories;

import com.mike.db.entities.Item;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ItemRepository extends CrudRepository<Item, Long>
{
	@Override
	void deleteById(Long aLong);

	@Override
	<S extends Item> S save(S s);

	@Override
	Optional<Item> findById(Long aLong);

	@Override
	Iterable<Item> findAll();
}
