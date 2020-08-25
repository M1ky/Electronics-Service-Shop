package com.mike.controller;

import com.mike.util.PageMappings;
import com.mike.util.ViewNames;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class ElectronicsController
{
	@GetMapping(PageMappings.ERROR)
	public String error()
	{
		return ViewNames.ERROR;
	}
}
