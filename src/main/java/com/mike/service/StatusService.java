package com.mike.service;

import com.mike.db.entities.Status;
import com.mike.db.repositories.StatusRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class StatusService
{
	private final StatusRepository statusRepository;

	@Autowired
	public StatusService(StatusRepository statusRepository)
	{
		this.statusRepository = statusRepository;
	}

	public Optional<Status> findById(Long id)
	{
		log.info("Find status by id: {}", id);
		Optional<Status> status = statusRepository.findById(id);
		if (status.isEmpty())
			return Optional.empty();
		else
			return status;
	}

	public Status save(Status status)
	{
		log.info("Saving status: {}", status.toString());
		return statusRepository.save(status);
	}

	public void deleteById(Long id)
	{
		log.info("Deleting status by id: {}", id);
		statusRepository.deleteById(id);
	}

	public Iterable<Status> findAll()
	{
		log.info("Finding all statuses");
		return statusRepository.findAll();
	}
}