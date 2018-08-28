package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.domain.Child;
import com.example.demo.domain.GrandChild;
import com.example.demo.domain.Items;
import com.example.demo.domain.Parent;
import com.example.demo.form.AddForm;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.ItemRepository;

@Controller
@RequestMapping("/mercari")
public class EditController {
	

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ItemRepository itemRepository;
	
	@ModelAttribute
	public AddForm setUpForm(@RequestParam("id") String Id, Model model) {
		long itemId = Long.parseLong(Id);
		
		Items item = itemRepository.load(itemId);
		System.out.println(item);
		AddForm form = new AddForm();
		BeanUtils.copyProperties(item, form);
		form.setChild(item.getName_child());
		form.setParent(item.getName_parent());
		form.setGrandChild(item.getName_grand_child());
		model.addAttribute("form", form );
		model.addAttribute("item", item);
		System.out.println(form);
		return form;
	}

	@RequestMapping("/edit")
	public String viewDetail(AddForm form, Model model) {
		List<Parent> parentList = categoryRepository.findParent();
		List<Child> childList = categoryRepository.findChild();
		List<GrandChild> grandChildList = categoryRepository.findGrandChild();
		
		model.addAttribute("parentList", parentList);
		model.addAttribute("childList", childList);
		model.addAttribute("grandChildList", grandChildList);
		return "/edit";
		
	}
}
