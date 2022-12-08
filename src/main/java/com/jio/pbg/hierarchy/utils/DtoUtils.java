package com.jio.pbg.hierarchy.utils;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jio.pbg.hierarchy.dto.CategoryDto;
import com.jio.pbg.hierarchy.dto.CategoryWithChildrenDto;
import com.jio.pbg.hierarchy.model.Category;

@Service
public class DtoUtils {

	@Autowired
	ModelMapper modelMapper;

	public CategoryWithChildrenDto toCategoryWithChildrenDTO(Category category) {
		return modelMapper.map(category, CategoryWithChildrenDto.class);
	}
	
	public CategoryDto toCategoryDTO(Category category) {
		return modelMapper.map(category, CategoryDto.class);
	}
}
