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
		model.addAttribute("statuses", statusService.findAll());
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
		statusService.save(status);
		return PageMappings.REDIRECT_STATUSES_LIST;
	}

	@PostMapping(PageMappings.EDIT_STATUS)
	public String editStatus(@RequestParam Long statusId, Model model)
	{
		statusService.findById(statusId).ifPresent(status -> {
			model.addAttribute("status", status);
		});

		return ViewNames.ADD_STATUS;
	}

	@PostMapping(PageMappings.DELETE_STATUS)
	public String deleteStatus(@RequestParam Long statusId)
	{
		statusService.deleteById(statusId);
		return PageMappings.REDIRECT_STATUSES_LIST;
	}
}
