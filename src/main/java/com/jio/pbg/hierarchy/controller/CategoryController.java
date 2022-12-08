package com.jio.pbg.hierarchy.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jio.pbg.hierarchy.dto.CategoryDto;
import com.jio.pbg.hierarchy.dto.CategoryWithChildrenDto;
import com.jio.pbg.hierarchy.model.Category;
import com.jio.pbg.hierarchy.service.CategoryService;
import com.jio.pbg.hierarchy.utils.DtoUtils;

@RestController
@RequestMapping("/api")
public class CategoryController {

	@Autowired
	CategoryService categoryService;
	
	@Autowired
	DtoUtils dtoUtils;
	
	@GetMapping("/categories")
	public ResponseEntity<List<CategoryWithChildrenDto>> getAllCategories() {
		List<Category> categories;
		try {
			categories = categoryService.getAllCategories();
			
			if(categories.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			
			List<CategoryWithChildrenDto> result = new ArrayList<CategoryWithChildrenDto>();
			for(Category cat : categories) {
				result.add(dtoUtils.toCategoryWithChildrenDTO(cat));
			}
			
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/subcategories")
	public ResponseEntity<List<CategoryDto>> getAllSubCategories(@RequestParam(required = false) Integer brand_id) {
		List<Category> categories;
		try {
			categories = categoryService.getAllCategoriesByBrand(brand_id);
			
			if(categories.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			
			List<CategoryDto> result = new ArrayList<CategoryDto>();
			for(Category cat : categories) {
				result.add(dtoUtils.toCategoryDTO(cat));
			}
			
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
