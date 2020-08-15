package com.mike.controller;

import com.mike.db.entities.Parameter;
import com.mike.service.ParameterService;
import com.mike.util.PageMappings;
import com.mike.util.ViewNames;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Slf4j
@Controller
public class ParameterController
{
	private final ParameterService parameterService;

	@Autowired
	public ParameterController(ParameterService parameterService)
	{
		this.parameterService = parameterService;
	}

	@GetMapping(PageMappings.PARAMETERS_LIST)
	public String parameters(Model model)
	{
		Iterable<Parameter> parameters = parameterService.findAll();
		model.addAttribute("parameters", parameters);
		return ViewNames.PARAMETERS_LIST;
	}

	@GetMapping(PageMappings.ADD_PARAMETER)
	public String addParameter(Model model)
	{
		model.addAttribute("parameter", new Parameter());
		return ViewNames.ADD_PARAMETER;
	}

	@PostMapping(PageMappings.ADD_PARAMETER)
	public String addParameter(@ModelAttribute("parameter") Parameter parameter)
	{
		if (parameter.getId() == null)
		{
			parameterService.save(parameter);
			return PageMappings.REDIRECT_PARAMETERS_LIST;
		}

		Optional<Parameter> existingParameterOptional = parameterService.findById(parameter.getId());
		if (existingParameterOptional.isEmpty())
		{
			log.info("Did not find parameter with id: {}", parameter.getId());
			return PageMappings.REDIRECT_PARAMETERS_LIST;
		}
		Parameter existingParameter = existingParameterOptional.get();
		existingParameter.setDescription(parameter.getDescription());
		existingParameter.setValue(parameter.getValue());
		parameterService.save(existingParameter);

		return PageMappings.REDIRECT_PARAMETERS_LIST;
	}

	@PostMapping(PageMappings.EDIT_PARAMETER)
	public String editParameter(@RequestParam Long parameterId, Model model)
	{
		Optional<Parameter> existingParameter = parameterService.findById(parameterId);
		model.addAttribute("parameter", existingParameter.isPresent() ? existingParameter.get() : "");
		return ViewNames.ADD_PARAMETER;
	}

	@PostMapping(PageMappings.DELETE_PARAMETER)
	public String deleteParameter(@RequestParam Long parameterId)
	{
		parameterService.deleteById(parameterId);
		return PageMappings.REDIRECT_PARAMETERS_LIST;
	}
}
