package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Category;
import com.example.demo.domain.Items;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.ItemRepository;
import com.example.demo.repository.OriginalRepository;

@Service
public class InsertItemService {

	@Autowired
	private OriginalRepository originalRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ItemRepository itemRepository;
	
	public void insertItemToCategoryAndItems() {
		int maxId = originalRepository.findMaxId();
		String name_all = originalRepository.findById(maxId);
		
		if (name_all == null) {
			name_all = "null/null/null";
		}
		
		String[] name = name_all.split("/"); //split words to 3 separated word
		String name_big = name[0];
		String name_middle = name[1];
		String name_small = name[2];
		
		List<Category> listCategoryOfName_big = categoryRepository.findByName(name_big); //find identified parent word
		if (listCategoryOfName_big.size() == 0) { // if the size is 0, to insert the parent word
			Category category = new Category();
			category.setName(name_big);
			categoryRepository.setCategory(category);
		}
		List<Category> listCategoryOfName_big2 = categoryRepository.findByName(name_big); // in order to use the parent word, getting the parent before exit
		int parent = listCategoryOfName_big2.get(0).getId();

		List<Category> listCategoryOfName_middle = categoryRepository.findByNameMiddleName(name_middle, parent);

		if (listCategoryOfName_middle.size() == 0) {
			List<Category> categoryListMiddle = categoryRepository.findByName(name_big);
			Integer parentId = categoryListMiddle.get(0).getId();
			Category categoryMiddle = new Category();
			
			categoryMiddle.setName(name_middle);
			categoryMiddle.setParent(parentId);
			categoryRepository.setCategory(categoryMiddle);
		}

		List<Category> listCategoryOfName_small = categoryRepository.findByNameAndNameAll(name_small, name_all);
		if (listCategoryOfName_small.size() == 0) {
			List<Category> categoryList = categoryRepository.findByNameMiddleName(name_middle, parent);
			Integer parentIdSmall = categoryList.get(0).getId();
			Category categorySmall = new Category();
			categorySmall.setName(name_small);
			categorySmall.setParent(parentIdSmall);
			categorySmall.setName_all(name_all);

			categoryRepository.setCategory(categorySmall);
		}
	
		String itemName = originalRepository.findNameById(maxId);
		itemRepository.insertItem(name_all, itemName);
		
}
	public void insertItemToCategoryAndItem(int insertId, Items item) {
	
		String name_all = item.getName_all();
		
		if (name_all == null) {
			name_all = "null/null/null";
		}
		
		String[] name = name_all.split("/"); 
		String name_big = name[0];
		String name_middle = name[1];
		String name_small = name[2];
		
		List<Category> listCategoryOfName_big = categoryRepository.findByName(name_big); 
		if (listCategoryOfName_big.size() == 0) {
			Category category = new Category();
			category.setName(name_big);
			categoryRepository.setCategory(category);
		}
		
		List<Category> listCategoryOfName_big2 = categoryRepository.findByName(name_big);
		int parent = listCategoryOfName_big2.get(0).getId();

		List<Category> listCategoryOfName_middle = categoryRepository.findByNameMiddleName(name_middle, parent); 

		if (listCategoryOfName_middle.size() == 0) {
			List<Category> categoryListMiddle = categoryRepository.findByName(name_big);
			Integer parentId = categoryListMiddle.get(0).getId();
			Category categoryMiddle = new Category();
			categoryMiddle.setName(name_middle);
			categoryMiddle.setParent(parentId);
			categoryRepository.setCategory(categoryMiddle);
		}

		List<Category> listCategoryOfName_small = categoryRepository.findByNameAndNameAll(name_small, name_all);
		if (listCategoryOfName_small.size() == 0) {
			List<Category> categoryList = categoryRepository.findByNameMiddleName(name_middle, parent);
			Integer parentIdSmall = categoryList.get(0).getId();
			Category categorySmall = new Category();
			categorySmall.setName(name_small);
			categorySmall.setParent(parentIdSmall);
			categorySmall.setName_all(name_all);

			categoryRepository.setCategory(categorySmall);
		}
	
		itemRepository.updateItem(item);
}
}
