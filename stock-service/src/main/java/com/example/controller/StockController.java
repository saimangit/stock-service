package com.example.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.example.dto.StockDTO;
import com.example.exception.StockNotFoundException;

import com.example.model.Stock;

import com.example.service.StockService;

@RestController
@RequestMapping(value = "/stock-product-api")
public class StockController {

	@Autowired
	StockService stockProductService;

	@GetMapping(value = "/stock", produces = "application/json")
	public List<Stock> getNewAllStock() {
		return stockProductService.getAllStock();

	}

	@GetMapping(value = "/stock/{sid}", produces = "application/json")
	public Optional<Stock> getStockBySid(@PathVariable("sid") String sid) throws StockNotFoundException {

		return stockProductService.getStockBySid(sid);

	}

	@PostMapping(value = "/stock", produces = "application/json")
	public ResponseEntity<String> insertNewStock(@Valid @RequestBody StockDTO stock) {

		return stockProductService.addStock(stock);
	}

	@PutMapping(value = "/stock/{sid}", produces = "application/json")
	public Stock updateNewStock(@PathVariable("sid") String sid, @Valid @RequestBody StockDTO stock)
			throws StockNotFoundException {

		return stockProductService.updateStock(sid, stock);

	}

	@DeleteMapping(value = "/stock/{sid}", produces = "application/json")
	public ResponseEntity<Object> deleteNewStock(@PathVariable("sid") String sid) throws StockNotFoundException {

		return stockProductService.deleteStock(sid);

	}

}
