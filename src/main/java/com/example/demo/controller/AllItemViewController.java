package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.domain.Child;
import com.example.demo.domain.GrandChild;
import com.example.demo.domain.Items;
import com.example.demo.domain.Parent;
import com.example.demo.domain.User;
import com.example.demo.form.UserForm;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.ItemRepository;
import com.example.demo.repository.listStatusRepository;
import com.example.demo.service.ItemsService;

@Controller
@RequestMapping("/mercari")
public class AllItemViewController {

	
	@Autowired
	private ItemsService itemsService;

	@Autowired
	private listStatusRepository statusRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ItemRepository itemRepository;

	@ModelAttribute
	public UserForm setUpForm() {
		return new UserForm();
	}
	
	@RequestMapping("/viewAll")
	public String view(Model model) {
		
		List<Parent> parentList = categoryRepository.findParent();
		List<Child> childList = categoryRepository.findChild();
		List<GrandChild> grandChildList = categoryRepository.findGrandChild();

		model.addAttribute("parentList", parentList);
		model.addAttribute("childList", childList);
		model.addAttribute("grandChildList", grandChildList);

		return "list";
	}

	/**
	 * ページ番号に合わせて900件を表示するメソッド.
	 * 
	 * @param model
	 * @return ページ遷移
	 */
	@RequestMapping("/getJason")
	@ResponseBody
	public Items[] getJason(Integer status) {

		List<Items> itemsList = itemsService.findAll(status);// 商品を表示する
		Items[] datas = itemsList.toArray(new Items[itemsList.size()]);

		return datas;
	}

	@RequestMapping("/getSearchData")
	@ResponseBody
	public Items[] getSearchData(String name, String brand, String parent, String child, String grandChild, String page) {

		List<Items> itemList = itemRepository.searchItems(name, brand, parent, child, grandChild);
		Items[] data = itemList.toArray(new Items[itemList.size()]);

		return data;

	}
	
	@RequestMapping("/sampl")
	public String sample(UserForm userForm, Model model) {
		System.out.println("test");
		User user = new User();
		user.setEmail(userForm.getEmail());
		user.setPassword(userForm.getPassword());
		System.out.println(user);
		return "redirect:/mercari/viewAll";
		
	}

}
