package com.jio.pbg.hierarchy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jio.pbg.hierarchy.model.Distributor;

@Repository
public interface DistributorRepository extends JpaRepository<Distributor, Long> {}
