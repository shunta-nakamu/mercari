package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Category;
import com.example.demo.domain.Original;
import com.example.demo.domain.Sample;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.OriginalRepository;
import com.example.demo.repository.SampleRepository;

@Service
public class OriginalService {

	@Autowired
	private OriginalRepository originalRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private SampleRepository sampleRepository;
	
	


	public List<Original> findAllOriginal() {
		List<Original> originalList = originalRepository.findAllOfOriginal();
		return originalList;
	}
	

	public void setCategoryOld() {
		int countList = 0;
		List<Sample> listSample = sampleRepository.findAll();
		List<Original> originalList = originalRepository.findAllOfOriginal();
		System.out.println(originalList.size());
			
//			String[] name = nameAll.split("/");
		
		int count = 0;
		
		for (Sample sample : listSample) {
			Original original = originalList.get(countList);
			System.out.println(countList);
			System.out.println(sample);
			System.out.println(count);
			int record;
			int name_big = 0;
			int name_middle = 0;
			int name_small = 0;
			record = count * 3;
			name_big = record + 1;
			name_middle = record + 2;
			name_small = record + 3;
			count++;
			
			Category category = new Category();
			List<Category> listCategoryOfName_big = categoryRepository.findByName(sample.getName_big());
			if(listCategoryOfName_big.size() == 0) {
				category.setName(sample.getName_big());
				category.setParent(null);
				categoryRepository.setCategory(category);
				category.setName(sample.getName_middle());
				category.setParent(name_big);
				categoryRepository.setCategory(category);
				category.setName(sample.getName_small());
				category.setParent(name_middle);
				category.setName_all(original.getCategory_name());
				categoryRepository.setCategory(category);
		}else {
			
			List<Category> listCategoryOfName_middle = categoryRepository.findByName(sample.getName_middle());
				name_big = listCategoryOfName_big.get(0).getId();
			if(listCategoryOfName_middle.size() == 0) {
				category.setName(sample.getName_middle());
				category.setParent(name_big);
				categoryRepository.setCategory(category);
				category.setName(sample.getName_small());
				category.setParent(name_middle);
				category.setName_all(original.getCategory_name());
				categoryRepository.setCategory(category);
			}
//			else {
//				List<Category> listCategoryOfName_small = categoryRepository.findByName(sample.ge());
//				name_big = listCategoryOfName_big.get(0).getId();
//			}
			
			}
			countList += 1;
		}
		
			//リストからとってきて、すでにそれがあれば、挿入しない、それがなければ挿入する
			
			//1.リストからとってくる
			//2.リストの中身を分割する
			//3.挿入する
			//3.1分割したものを一つ一つ挿入する
			//3.2判定する
			//3.2.1名前で検索する
			//3.2.2見つかった場合
			//3.2.2.1とってきた情報からidをとる
			//3.2.3見つからなかった場合
			//
			
			
		//name
//			String[] categorySplit = category.getName_all().split("/");
			
			
				
			}
		}
	
	
