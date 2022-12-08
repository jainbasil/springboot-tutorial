package com.jio.pbg.hierarchy.dto;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryWithChildrenDto {
	
	private Long id;
	private String name;
	private Set<CategoryWithChildrenDto> children;

}
