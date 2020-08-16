package com.mike.controller;

import com.mike.db.entities.Item;
import com.mike.db.entities.Status;
import com.mike.service.StatusService;
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
public class StatusController
{
	private final StatusService statusService;

	@Autowired
	public StatusController(StatusService statusService)
	{
		this.statusService = statusService;
	}

	@GetMapping(PageMappings.STATUSES_LIST)
	public String statuses(Model model)
	{
		Iterable<Status> statuses = statusService.findAll();
		model.addAttribute("statuses", statuses);
		return ViewNames.STATUES_LIST;
	}

	@GetMapping(PageMappings.ADD_STATUS)
	public String addStatus(Model model)
	{
		model.addAttribute("status", new Status());
		return ViewNames.ADD_STATUS;
	}

	@PostMapping(PageMappings.ADD_STATUS)
	public String addNewStatus(@ModelAttribute("status") Status status)
	{
		if (status.getId() == null)
		{
			statusService.save(status);
			return PageMappings.REDIRECT_STATUSES_LIST;
		}

		Optional<Status> existingStatusOptional = statusService.findById(status.getId());
		if  (existingStatusOptional.isEmpty())
		{
			log.info("Did not find status with id: {}, in database.", status.getId());
			return PageMappings.REDIRECT_STATUSES_LIST;
		}
		Status existingStatus = existingStatusOptional.get();
		existingStatus.setStatusVal(status.getStatusVal());
		statusService.save(existingStatus);

		return PageMappings.REDIRECT_STATUSES_LIST;
	}

	@PostMapping(PageMappings.EDIT_STATUS)
	public String editStatus(@RequestParam Long statusId, Model model)
	{
		Optional<Status> existingStatus = statusService.findById(statusId);
		model.addAttribute("status", existingStatus.isPresent() ? existingStatus.get() : "");
		return ViewNames.ADD_STATUS;
	}

	@PostMapping(PageMappings.DELETE_STATUS)
	public String deleteStatus(@RequestParam Long statusId)
	{
		statusService.deleteById(statusId);
		return PageMappings.REDIRECT_STATUSES_LIST;
	}
}
