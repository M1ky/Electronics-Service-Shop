package com.mike.controller;

import com.mike.db.entities.Category;
import com.mike.service.CategoryService;
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
public class CategoryController
{
	private final CategoryService categoryService;

	@Autowired
	public CategoryController(CategoryService categoryService)
	{
		this.categoryService = categoryService;
	}

	@GetMapping(PageMappings.CATEGORIES_LIST)
	public String categories(Model model)
	{
		model.addAttribute("categories", categoryService.findAll());
		return ViewNames.CATEGORIES_LIST;
	}

	@GetMapping(PageMappings.ADD_CATEGORY)
	public String addCategoryGet(Model model)
	{
		model.addAttribute("category", new Category());
		return ViewNames.ADD_CATEGORY;
	}

	@PostMapping(PageMappings.ADD_CATEGORY)
	public String addCategoryPost(@ModelAttribute("category") Category category)
	{
		categoryService.save(category);
		return PageMappings.REDIRECT_CATEGORIES_LIST;
	}

	@PostMapping(PageMappings.EDIT_CATEGORY)
	public String editItem(@RequestParam Long categoryId, Model model)
	{
		categoryService.findById(categoryId).ifPresent(category -> {
			model.addAttribute("category", category);
		});

		return ViewNames.ADD_CATEGORY;
	}

	@PostMapping(PageMappings.DELETE_CATEGORY)
	public String deleteItem(@RequestParam Long categoryId)
	{
		categoryService.deleteById(categoryId);
		return PageMappings.REDIRECT_CATEGORIES_LIST;
	}
}
