package com.mike.controller;

import com.mike.db.entities.Category;
import com.mike.db.entities.Item;
import com.mike.db.entities.Parameter;
import com.mike.db.entities.Status;
import com.mike.service.CategoryService;
import com.mike.service.ItemService;
import com.mike.service.ParameterService;
import com.mike.service.StatusService;
import com.mike.util.PageMappings;
import com.mike.util.ViewNames;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.swing.text.View;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Controller
public class ItemController
{
	private final ItemService itemService;
	private final StatusService statusService;
	private final ParameterService parameterService;
	private final CategoryService categoryService;

	@Autowired
	public ItemController(ItemService itemService, StatusService statusService, ParameterService parameterService, CategoryService categoryService)
	{
		this.itemService = itemService;
		this.statusService = statusService;
		this.parameterService = parameterService;
		this.categoryService = categoryService;
	}

	@GetMapping(PageMappings.ITEMS_LIST)
	public String items(Model model)
	{
		model.addAttribute("items", itemService.findAll());
		return ViewNames.ITEMS_LIST;
	}

	@GetMapping(PageMappings.ADD_ITEM)
	public String addItem(Model model)
	{
		model.addAttribute("item", new Item());

		addAttributes(model);
		return ViewNames.ADD_ITEM;
	}

	private void addAttributes(Model model)
	{
		Iterable<Status> statuses = statusService.findAll();
		model.addAttribute("statuses", statuses);

		Iterable<Parameter> parameters = parameterService.findAll();
		model.addAttribute("allParameters", parameters);

		Iterable<Category> categories = categoryService.findAll();
		model.addAttribute("categories", categories);
	}

	@PostMapping(PageMappings.ADD_ITEM)
	public String addNewItem(@ModelAttribute("item") Item item)
	{
		itemService.save(item);
		return PageMappings.REDIRECT_ITEMS_LIST;
	}

	@PostMapping(PageMappings.EDIT_ITEM)
	public String editItem(@RequestParam Long itemId, Model model)
	{
		itemService.findById(itemId).ifPresent(item -> {
			model.addAttribute("item", item);
		});

		addAttributes(model);
		return ViewNames.ADD_ITEM;
	}

	@PostMapping(PageMappings.DELETE_ITEM)
	public String deleteItem(@RequestParam Long itemId)
	{
		itemService.deleteById(itemId);
		return PageMappings.REDIRECT_ITEMS_LIST;
	}

	@GetMapping(PageMappings.DELETE_ITEM_PARAM)
	public String deleteItemParamView(Long itemId, Model model)
	{
		itemService.findById(itemId).ifPresent(item -> {
			model.addAttribute("item", item);
		});
		return ViewNames.DELETE_PARAM;
	}

	@PostMapping(PageMappings.DELETE_ITEM_PARAM)
	public String deleteItemParam(@RequestParam Long itemId, Long itemParamId, Model model)
	{
		if (itemParamId == null)
		{
			itemService.findById(itemId).ifPresent(item -> {
				model.addAttribute("item", item);
			});
			return ViewNames.DELETE_PARAM;
		}

		itemService.findById(itemId).ifPresent(item -> {
			parameterService.findById(itemParamId).ifPresent(toBeRemoved -> {
				Set<Parameter> itemParameters = item.getParameters();
				itemParameters.remove(toBeRemoved);
				item.setParameters(itemParameters);
				itemService.save(item);
			});
		});

		return PageMappings.REDIRECT_ITEMS_LIST;
	}

	@PostMapping(PageMappings.ADD_ITEM_PARAM)
	public String addItemParam(@RequestParam Long itemId, Model model)
	{
		itemService.findById(itemId).ifPresent(item -> {
			model.addAttribute("item", item);
		});

		model.addAttribute("allParameters", parameterService.findAll());
		model.addAttribute("paramToAdd", new Parameter());

		return ViewNames.ADD_ITEM_PARAM;
	}

	@PostMapping(PageMappings.ADD_PARAM_TO_ITEM)
	public String addParamToItem(@RequestParam Long itemId, @ModelAttribute("paramToAdd") Parameter paramToAdd)
	{
		itemService.findById(itemId).ifPresent(item -> {
			parameterService.findById(paramToAdd.getId()).ifPresent(parameter -> {
				Set<Parameter> itemParameters = item.getParameters();
				itemParameters.add(parameter);
				item.setParameters(itemParameters);
				itemService.save(item);
			});
		});

		return PageMappings.REDIRECT_ITEMS_LIST;
	}
}
