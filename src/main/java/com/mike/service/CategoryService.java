package com.mike.service;

import com.mike.db.entities.Category;
import com.mike.db.entities.Item;
import com.mike.db.repositories.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class CategoryService
{
	private CategoryRepository categoryRepository;

	@Autowired
	public CategoryService(CategoryRepository categoryRepository)
	{
		this.categoryRepository = categoryRepository;
	}

	public void deleteById(Long id)
	{
		log.info("Deleting a category with id: {}", id);
		categoryRepository.deleteById(id);
	}


	public Category save(Category category)
	{
		log.info("Saving a category: {}", category.toString());
		return categoryRepository.save(category);
	}

	public Optional<Category> findById(Long id)
	{
		log.info("Finding a category by id: {}", id);
		Optional<Category> category = categoryRepository.findById(id);
		if (category == null)
			return Optional.empty();
		else
			return category;
	}

	public Iterable<Category> findAll()
	{
		log.info("Finding all categories");
		return categoryRepository.findAll();
	}
}
