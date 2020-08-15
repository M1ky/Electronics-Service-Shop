package com.mike.service;

import com.mike.db.entities.Item;
import com.mike.db.repositories.ItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class ItemService
{
	private ItemRepository itemRepository;

	@Autowired
	public ItemService(ItemRepository itemRepository)
	{
		this.itemRepository = itemRepository;
	}

	public void deleteById(Long id)
	{
		log.info("Deleting an item with id: {}", id);
		itemRepository.deleteById(id);
	}


	public Item save(Item item)
	{
		log.info("Saving an item: {}", item.toString());
		return itemRepository.save(item);
	}

	public Optional<Item> findById(Long id)
	{
		log.info("Finding an item by id: {}", id);
		Optional<Item> item = itemRepository.findById(id);
		if (item == null)
			return Optional.empty();
		else
			return item;
	}

	public Iterable<Item> findAll()
	{
		log.info("Finding all items");
		return itemRepository.findAll();
	}
}
