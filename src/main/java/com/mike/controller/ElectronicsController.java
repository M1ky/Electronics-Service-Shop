package com.mike.controller;

import com.mike.service.ElectronicsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

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


}
