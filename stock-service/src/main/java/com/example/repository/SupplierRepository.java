package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.RawStock;

public interface SupplierRepository extends JpaRepository<RawStock, Long> {

}
