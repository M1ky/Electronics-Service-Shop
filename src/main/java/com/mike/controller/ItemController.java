package com.mike.controller;

import com.mike.db.entities.Item;
import com.mike.service.ItemService;
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
public class ItemController
{
	private final ItemService itemService;

	@Autowired
	public ItemController(ItemService itemService)
	{
		this.itemService = itemService;
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
		return ViewNames.ADD_ITEM;
	}

	@PostMapping(PageMappings.ADD_ITEM)
	public String addNewItem(@ModelAttribute("item") Item item)
	{
		if (item.getId() == null)
		{
			itemService.save(item);
			return PageMappings.REDIRECT_ITEMS_LIST;
		}

		Optional<Item> existingItemOptional = itemService.findById(item.getId());
		if  (existingItemOptional.isEmpty())
		{
			log.info("Did not find item with id: {}, in database.", item.getId());
			return PageMappings.REDIRECT_ITEMS_LIST;
		}
		Item existingItem = existingItemOptional.get();
		existingItem.setCategory(item.getCategory());
		existingItem.setComment(item.getComment());
		existingItem.setModel(item.getModel());
		existingItem.setSerialNr(item.getSerialNr());
		itemService.save(existingItem);

		return PageMappings.REDIRECT_ITEMS_LIST;
	}

	@PostMapping(PageMappings.EDIT_ITEM)
	public String editItem(@RequestParam Long itemId, Model model)
	{
		Optional<Item> existingItem = itemService.findById(itemId);
		model.addAttribute("item", existingItem.isPresent() ? existingItem.get() : "");
		return ViewNames.ADD_ITEM;
	}

	@PostMapping(PageMappings.DELETE_ITEM)
	public String deleteItem(@RequestParam Long itemId)
	{
		itemService.deleteById(itemId);
		return PageMappings.REDIRECT_ITEMS_LIST;
	}
}
