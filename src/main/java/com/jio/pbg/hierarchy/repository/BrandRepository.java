package com.jio.pbg.hierarchy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jio.pbg.hierarchy.model.Brand;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {

}
