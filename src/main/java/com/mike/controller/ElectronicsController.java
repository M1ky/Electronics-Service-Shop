package com.mike.controller;

import com.mike.service.ElectronicsService;
import com.mike.util.PageMappings;
import com.mike.util.ViewNames;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class ElectronicsController
{
	private ElectronicsService electronicsService;

	@Autowired
	public ElectronicsController(ElectronicsService electronicsService)
	{
		this.electronicsService = electronicsService;
	}

	@GetMapping(PageMappings.ERROR)
	public String error()
	{
		return  ViewNames.ERROR;
	}
}
