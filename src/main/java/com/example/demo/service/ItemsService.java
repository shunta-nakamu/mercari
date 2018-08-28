package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Items;
import com.example.demo.repository.ItemRepository;

@Service
public class ItemsService {

	@Autowired
	private ItemRepository itemRepository;
	
	public List<Items> findAll (Integer status){
		List<Items> itemsList = itemRepository.findAll(status);
		return itemsList;
	}
}
