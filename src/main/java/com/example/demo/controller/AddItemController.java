package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.domain.Child;
import com.example.demo.domain.GrandChild;
import com.example.demo.domain.Items;
import com.example.demo.domain.Parent;
import com.example.demo.form.AddForm;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.OriginalRepository;
import com.example.demo.service.InsertItemService;

@Controller
@RequestMapping("/mercari")
public class AddItemController {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private OriginalRepository originalRepository;
	
	@Autowired
	private InsertItemService insertItemService;
		
	@ModelAttribute
	public AddForm setUpForm() {
		return new AddForm();
	}
	
	@RequestMapping("/viewAddPage")
	public String view(AddForm addForm, Model model) {
		List<Parent> parentList = categoryRepository.findParent();
		List<Child> childList = categoryRepository.findChild();
		List<GrandChild> grandChildList = categoryRepository.findGrandChild();
		
		model.addAttribute("parentList", parentList);
		model.addAttribute("childList", childList);
		model.addAttribute("grandChildList", grandChildList);
		
		return "/add";
	}
	
	@RequestMapping("/addItem")
	public String addItem(Model model, @Validated AddForm addForm, BindingResult result, 
			@RequestParam("parent") String parent, @RequestParam("child") String child, @RequestParam("grandChild") String grandChild) {
		if(result.hasErrors()) {
			return view(addForm, model);
		}
		Items item = new Items();
		BeanUtils.copyProperties(addForm, item);
		
		item.setName_all(parent + "/" + child + "/" + grandChild);
		item.setPrice(addForm.getPrice());
		
		int insertId = originalRepository.findMaxId() + 1;
		item.setId(insertId);
		originalRepository.insert(item);
		
		insertItemService.insertItemToCategoryAndItems();
		
		
		return "redirect:/mercari/viewAll";
	}
	
	@RequestMapping("/editItem")
	public String addItem(Model model, @Validated AddForm addForm, BindingResult result, @RequestParam("id") String id,
			@RequestParam("parent") String parent, @RequestParam("child") String child, @RequestParam("grand_child") String grandChild) {
		if(result.hasErrors()) {
			return view(addForm, model);
		}
		
		Items item = new Items();
		
		BeanUtils.copyProperties(addForm, item);
		
		
		item.setName_all(parent + "/" + child + "/" + grandChild);
		item.setPrice(addForm.getPrice());
	
		int insertId = Integer.parseInt(id);
		
		item.setId(insertId);
		
		insertItemService.insertItemToCategoryAndItem(insertId, item);
		
		
		
		return "redirect:/mercari/viewAll";
	}
	
}
