package com.jio.pbg.hierarchy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.jio.pbg.hierarchy.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

	Category findByName(String name);
	List<Category> findByLevel(Integer level);
	List<Category> findByAssociatedBrands_id(Integer brand_id);
}
