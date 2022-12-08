package com.jio.pbg.hierarchy;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.jio.pbg.hierarchy.model.Brand;
import com.jio.pbg.hierarchy.model.Category;
import com.jio.pbg.hierarchy.model.Distributor;
import com.jio.pbg.hierarchy.repository.BrandRepository;
import com.jio.pbg.hierarchy.repository.CategoryRepository;
import com.jio.pbg.hierarchy.repository.DistributorRepository;

import lombok.extern.java.Log;

@Component
public class DatabaseSeeder {
	
	private DistributorRepository distributorRepository;
	private BrandRepository brandRepository;
	private CategoryRepository categoryRepository;
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public DatabaseSeeder(DistributorRepository distributorRepository, BrandRepository brandRepository, CategoryRepository categoryRepository, JdbcTemplate jdbcTemplate) {
		this.distributorRepository = distributorRepository;
		this.brandRepository = brandRepository;
		this.categoryRepository = categoryRepository;
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@EventListener
	public void seed(ContextRefreshedEvent event) {
//		seedDistributorsTable();
//		seedBrandsTable();
//		seedCategoriesTable();
	}

	private void seedCategoriesTable() {
		//create sub categories
		Category televisionCategory = categoryRepository.findByName("Televisions");
		
		Category screenTypeCategory = new Category("Screen Type", 1);
		Category ledCat = new Category("LED Screen", 2);
		ledCat.setParent(screenTypeCategory);
		Category lcdCat = new Category("LCD Screen", 2);
		lcdCat.setParent(screenTypeCategory);
		Category oledCat = new Category("OLED Screen", 2);
		oledCat.setParent(screenTypeCategory);
		categoryRepository.saveAll(Arrays.asList(ledCat, lcdCat, oledCat));
		
		screenTypeCategory.setParent(televisionCategory);
		categoryRepository.save(screenTypeCategory);
	}

	private void seedBrandsTable() {
		String brandOne = "BPL", brandTwo = "Kelvinator", brandThree = "Life";
		String sql = "SELECT name from brands b WHERE b.name IN ('" + brandOne + "','" + brandTwo + "', '" + brandThree + "')";
		List<Object> rs = jdbcTemplate.query(sql, (resultSet, rowNum) -> null);
		if(rs == null || rs.size() <= 0) {
			Category televisions = new Category("Televisions", 0);
			Category refridgerators = new Category("Refridgerators", 0);
			Category airConditioners = new Category("Air Conditioners", 0);
			Category headPhones = new Category("Headphones", 0);
			
			Brand bOne = new Brand("BPL");
			Set<Category> bOneCategories = new HashSet<Category>();
			bOneCategories.add(televisions);
			bOneCategories.add(refridgerators);
			bOne.setProductCategories(bOneCategories);
			
			Brand bTwo = new Brand("Kelvinator");
			Set<Category> bTwoCategories = new HashSet<Category>();
			bTwoCategories.add(refridgerators);
			bTwoCategories.add(airConditioners);
			bTwo.setProductCategories(bTwoCategories);
			
			Brand bThree = new Brand("Life");
			Set<Category> bThreeCategories = new HashSet<Category>();
			bThreeCategories.add(headPhones);
			bThree.setProductCategories(bThreeCategories);
			
			brandRepository.saveAll(Arrays.asList(bOne, bTwo, bThree));
		}
	}

	private void seedDistributorsTable() {
		String distOne = "BPLDistributor", distTwo = "KelvinatorDistributor", distThree = "LifeDistributor";
		String sql = "SELECT name from distributors d WHERE d.name IN ('" + distOne + "','" + distTwo + "', '" + distThree + "')";
		List<Object> rs = jdbcTemplate.query(sql, (resultSet, rowNum) -> null);
		if(rs == null || rs.size() <= 0) {
			Distributor dOne = new Distributor("BPLDistributor", "Bengaluru Urban");
			Distributor dTwo = new Distributor("KelvinatorDistributor", "Ernakulam");
			Distributor dThree = new Distributor("LifeDistributor", "Srinagar");
			distributorRepository.saveAll(Arrays.asList(dOne, dTwo, dThree));
		}
		
	}
}
