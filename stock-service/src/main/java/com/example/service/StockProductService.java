package com.example.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.dto.StockDTO;
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

	
	private static final String EXCEPTION_MESSAGE = "product not found for the pid: ";
	
	
	public List<Stock> getAllStock() {
		return stockRepository.findAll();
	}
	
	public Optional<Stock> getStockBySid(String sid) throws StockNotFoundException {
		
		int s=Integer.parseInt(sid);
		
		if (!stockRepository.existsById((long)s )) {
	      throw new StockNotFoundException(EXCEPTION_MESSAGE + sid);
		}	
		
		return stockRepository.findById((long)s);
	}

	public ResponseEntity<String> addStock(StockDTO stock) {
		
		Stock s=objectMapping(stock);
		
		
		stockRepository.save(s);
		return new ResponseEntity<>("Stocks got created", HttpStatus.CREATED);
	}

	public Stock objectMapping(StockDTO stock) {
		Stock s= new Stock();
		s.setSupplierId(stock.getSupplierId());
		s.setQty(stock.getQty());
		s.setSupplierContact(stock.getSupplierContact());
		s.setSupplierName(stock.getSupplierName());
		s.setValid(stock.getValid());
		return s;
	}
	public Stock updateStock(String sid, @Valid StockDTO stock) throws StockNotFoundException {

		if (!stockRepository.existsById((long) Integer.parseInt(sid))) {
			throw new StockNotFoundException(EXCEPTION_MESSAGE + sid);
		}

		Stock  entity = objectMapping(stock);
		
		return stockRepository.findById((long) Integer.parseInt(sid)).map(s -> {
			s.setQty(entity.getQty());
			s.setSupplierContact(entity.getSupplierContact());
			s.setSupplierName(entity.getSupplierName());
			s.setValid(entity.getValid());
			return stockRepository.save(s);
		}).orElseThrow(() -> new StockNotFoundException("stock not found for the pid: " + sid));
	}

	public ResponseEntity<Object> deleteStock(String sid) {
		return stockRepository.findById((long) Integer.parseInt(sid)).map(p -> {
			stockRepository.delete(p);
			return ResponseEntity.ok().build();
		}).orElseThrow(() -> new ProductNotFoundException(EXCEPTION_MESSAGE + sid));
	}



}
