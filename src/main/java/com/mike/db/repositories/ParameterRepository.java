package com.mike.db.repositories;

import com.mike.db.entities.Item;
import com.mike.db.entities.Parameter;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ParameterRepository extends CrudRepository<Parameter, Long>
{
	@Override
	Optional<Parameter> findById(Long aLong);

	@Override
	<S extends Parameter> S save(S s);

	@Override
	void deleteById(Long aLong);

	@Override
	Iterable<Parameter> findAll();
}
