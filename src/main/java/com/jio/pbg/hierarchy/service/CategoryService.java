package com.jio.pbg.hierarchy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jio.pbg.hierarchy.model.Category;
import com.jio.pbg.hierarchy.repository.CategoryRepository;

@Service
public class CategoryService {
	@Autowired
	CategoryRepository categoryRepository;
	
	public List<Category> getAllCategories() {
		List<Category> categories = categoryRepository.findByLevel(0);
		return categories;
	}
	
	public List<Category> getAllCategoriesByBrand(Integer brand_id) {
		List<Category> categories;
		if(brand_id != null) {
			categories = categoryRepository.findByAssociatedBrands_id(brand_id);
		} else {
			categories = categoryRepository.findByLevel(1);
		}
		return categories;
	}
}
