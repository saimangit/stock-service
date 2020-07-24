package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.exception.ProductNotFoundException;
import com.example.exception.StockNotFoundException;
import com.example.model.Stock;
import com.example.repository.ProductRepository;
import com.example.repository.StockRepository;

@Service
public class StockProductService {

	@Autowired
	StockRepository stockRepository;

	@Autowired
	ProductRepository productRepository;

	public List<Stock> getStock() {
		return stockRepository.findAll();
	}

	public ResponseEntity<String> addStock(Stock stock) {
		stockRepository.save(stock);
		return new ResponseEntity<>("Stocks got created", HttpStatus.CREATED);
	}

	public Stock updateStock(String sid, Stock stock) throws StockNotFoundException {

		if (!stockRepository.existsById((long) Integer.parseInt(sid))) {
			throw new StockNotFoundException("product not found for the pid: " + sid);
		}

		return stockRepository.findById((long) Integer.parseInt(sid)).map(s -> {
			s.setQty(stock.getQty());
			s.setSupplierContact(stock.getSupplierContact());
			s.setSupplierName(stock.getSupplierName());
			s.setValid(stock.getValid());
			return stockRepository.save(s);
		}).orElseThrow(() -> new StockNotFoundException("stock not found for the pid: " + sid));
	}

	public ResponseEntity<Object> deleteStock(String sid) {
		return stockRepository.findById((long) Integer.parseInt(sid)).map(p -> {
			stockRepository.delete(p);
			return ResponseEntity.ok().build();
		}).orElseThrow(() -> new ProductNotFoundException("product not found for the pid: " + sid));
	}

}
