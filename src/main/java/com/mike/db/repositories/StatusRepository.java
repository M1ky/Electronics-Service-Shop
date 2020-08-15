package com.mike.db.repositories;

import com.mike.db.entities.Status;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface StatusRepository extends CrudRepository<Status, Long>
{
	@Override
	Optional<Status> findById(Long aLong);

	@Override
	<S extends Status> S save(S s);

	@Override
	void deleteById(Long aLong);

	@Override
	Iterable<Status> findAll();
}
