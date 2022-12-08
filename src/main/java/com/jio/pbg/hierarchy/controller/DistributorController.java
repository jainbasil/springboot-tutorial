package com.jio.pbg.hierarchy.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jio.pbg.hierarchy.model.Distributor;
import com.jio.pbg.hierarchy.repository.DistributorRepository;

@RestController
@RequestMapping("/api")
public class DistributorController {

	@Autowired
	DistributorRepository distributorRepository;
	
	@GetMapping("/distributors")
	public ResponseEntity<List<Distributor>> getAllDistributors() {
		List<Distributor> distributors;
		try {
			distributors = distributorRepository.findAll();
			
			if(distributors.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			
			return new ResponseEntity<>(distributors, HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
