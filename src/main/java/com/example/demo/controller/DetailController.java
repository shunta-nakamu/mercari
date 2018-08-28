package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.domain.Items;
import com.example.demo.repository.ItemRepository;

@Controller
@RequestMapping("/mercari")
public class DetailController {

	
	@Autowired
	public ItemRepository itemRepository;
	@RequestMapping("/viewDetail")
	public String viewDetail(@RequestParam("id") long id, Model model) {
		Items item = itemRepository.load(id);
		
		model.addAttribute("item", item);
		
		return "/detail";
		
	}
}
