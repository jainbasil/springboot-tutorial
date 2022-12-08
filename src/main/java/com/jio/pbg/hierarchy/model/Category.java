package com.jio.pbg.hierarchy.model;

import java.util.Set;

//import com.fasterxml.jackson.annotation.JsonBackReference;
//import com.fasterxml.jackson.annotation.JsonIdentityInfo;
//import com.fasterxml.jackson.annotation.JsonIdentityReference;
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import com.fasterxml.jackson.annotation.JsonManagedReference;
//import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "categories")
@Getter @Setter @NoArgsConstructor
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Category.class)
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	private String name;
	
	private Integer level;
	
	@OneToOne(optional = true, cascade= {CascadeType.ALL})
	@JoinColumn(name="parent_id")
//	@JsonBackReference
	private Category parent;
	
	@OneToMany(mappedBy = "parent", cascade= {CascadeType.ALL})
//	@JsonManagedReference
	private Set<Category> children;
	
//	@JsonIgnore
	@ManyToMany(mappedBy = "productCategories") // this is the field name in Brand.java
	private Set<Brand> associatedBrands;

	public Category(String name, Integer level) {
		this.name = name;
		this.level = level;
	}
}
