package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.RawStock;
import com.example.repository.SupplierRepository;

@Service
public class StockService {

	@Autowired
	SupplierRepository supplierRepo;
	
	public List<RawStock> findAllStocks() {
		// TODO Auto-generated method stub
		return supplierRepo.findAll();
	}

	public RawStock saveStock(RawStock rawStock) {
		// TODO Auto-generated method stub
		return supplierRepo.save(rawStock);
	}

	public Optional<RawStock> getByStockId(int i) {
		// TODO Auto-generated method stub
		return supplierRepo.findById((long) i);
	}

	public void deleteStockById(int i) {
		// TODO Auto-generated method stub
		supplierRepo.deleteById((long) i);
		
	}

}
