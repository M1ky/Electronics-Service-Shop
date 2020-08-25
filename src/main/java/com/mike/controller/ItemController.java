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
		Iterable<Item> items = itemService.findAll();
		model.addAttribute("items", items);
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

	// TODO - add remove Param from Item functionality
	@PostMapping(PageMappings.ADD_ITEM)
	public String addNewItem(@ModelAttribute("item") Item item)
	{
		itemService.save(item);
		return PageMappings.REDIRECT_ITEMS_LIST;
	}

	@PostMapping(PageMappings.EDIT_ITEM)
	public String editItem(@RequestParam Long itemId, Model model)
	{
		Optional<Item> existingItem = itemService.findById(itemId);
		model.addAttribute("item", existingItem.isPresent() ? existingItem.get() : "");

		addAttributes(model);
		return ViewNames.ADD_ITEM;
	}

	@PostMapping(PageMappings.DELETE_ITEM)
	public String deleteItem(@RequestParam Long itemId)
	{
		itemService.deleteById(itemId);
		return PageMappings.REDIRECT_ITEMS_LIST;
	}

	@PostMapping(PageMappings.DELETE_ITEM_PARAM)
	public String deleteItemParam(@RequestParam Long itemId, @RequestParam Long itemParamId, Model model)
	{
		Optional<Item> existingItem = itemService.findById(itemId);
		Item item = existingItem.get();
		Optional<Parameter> toBeRemoved = parameterService.findById(itemParamId);

		Set<Parameter> itemParameters = item.getParameters();
		itemParameters.remove(toBeRemoved.get());
		item.setParameters(itemParameters);
		itemService.save(item);

		return PageMappings.REDIRECT_ITEMS_LIST;
	}

	@PostMapping(PageMappings.ADD_ITEM_PARAM)
	public String addItemParam(@RequestParam Long itemId, Model model)
	{
		Optional<Item> existingItem = itemService.findById(itemId);
		model.addAttribute("item", existingItem.isPresent() ? existingItem.get() : "");

		Iterable<Parameter> allParameters = parameterService.findAll();
		model.addAttribute("allParameters", allParameters);

		Parameter parameterToAdd = new Parameter();
		model.addAttribute("paramToAdd", parameterToAdd);

		return ViewNames.ADD_ITEM_PARAM;
	}

	@PostMapping(PageMappings.ADD_PARAM_TO_ITEM)
	public String addParamToItem(@RequestParam Long itemId, @ModelAttribute("paramToAdd") Parameter paramToAdd)
	{
		Optional<Item> existingItem = itemService.findById(itemId);
		Item item = existingItem.get();

		Optional<Parameter> existingParam = parameterService.findById(paramToAdd.getId());
		Parameter parameter = existingParam.get();

		Set<Parameter> itemParameters = item.getParameters();
		itemParameters.add(parameter);

		item.setParameters(itemParameters);
		itemService.save(item);

		return PageMappings.REDIRECT_ITEMS_LIST;
	}
}
