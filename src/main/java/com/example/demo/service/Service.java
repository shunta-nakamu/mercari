package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.domain.Category;
import com.example.demo.domain.Original;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.OriginalRepository;

@org.springframework.stereotype.Service
public class Service {

	@Autowired
	private OriginalRepository originalRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	public void setCategory() {
		int count = 0;  //to confirm loop
		List<Original> originalList = originalRepository.findAllOfOriginal(); // find all original date
		for (Original original : originalList) { //loop of original List
			if (count % 10000 == 0) {
				System.out.println("countOriginalList =" + count);
			}
			count++;
	
			String name_all = original.getCategory_name(); //to get category name from original objects
			
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

			List<Category> listCategoryOfName_middle = categoryRepository.findByNameMiddleName(name_middle, parent); //find middle name by using name_middle and parent

			if (listCategoryOfName_middle.size() == 0) {
				List<Category> categoryListMiddle = categoryRepository.findByName(name_big); //get info of date using name big in order to use the id
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
		}
	}
}
