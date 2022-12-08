package com.jio.pbg.hierarchy.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "distributors")
@Getter @Setter @NoArgsConstructor
public class Distributor {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	private String name;
	private String district;

	public Distributor(String name, String district) {
		this.name = name;
		this.district = district;
	}
}
