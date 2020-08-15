package com.mike.service;

import com.mike.db.entities.Parameter;
import com.mike.db.repositories.ParameterRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class ParameterService
{

	private ParameterRepository parameterRepository;

	@Autowired
	public ParameterService(ParameterRepository parameterRepository)
	{
		this.parameterRepository = parameterRepository;
	}

	public Optional<Parameter> findById(Long id)
	{
		log.info("Find parameter by id: {}", id);
		Optional<Parameter> parameter = parameterRepository.findById(id);
		if (parameter == null)
			return Optional.empty();
		else
			return parameter;
	}

	public Parameter save(Parameter parameter)
	{
		log.info("Saving parameter: {}", parameter.toString());
		return parameterRepository.save(parameter);
	}

	public void deleteById(Long id)
	{
		log.info("Deleting parameter by id: {}", id);
		parameterRepository.deleteById(id);
	}

	public Iterable<Parameter> findAll()
	{
		log.info("Finding all parameters");
		return parameterRepository.findAll();
	}
}
