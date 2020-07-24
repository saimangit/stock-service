package com.example.controller;

import java.util.List;

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


import com.example.exception.StockNotFoundException;

import com.example.model.Stock;

import com.example.service.StockProductService;



@RestController
@RequestMapping(value = "/stock-product-api")
public class StockProductController {

	@Autowired
	StockProductService stockProductService;

	@GetMapping(value = "/stock", produces = "application/json")
	public List<Stock> getNewAllStock() {
		return stockProductService.getStock();

	}

	@PostMapping(value = "/stock", produces = "application/json")
	public ResponseEntity<String> insertNewStock(@Valid @RequestBody Stock stock) {

		return stockProductService.addStock(stock);
	}

	@PutMapping(value = "/stock/{sid}", produces = "application/json")
	public Stock updateNewStock(@PathVariable("sid") String sid, @Valid @RequestBody Stock stock)
			throws StockNotFoundException {

		return stockProductService.updateStock(sid, stock);

	}

	@DeleteMapping(value = "/stock/{sid}", produces = "application/json")
	public ResponseEntity<?> deleteNewStock(@PathVariable("sid") String sid) {

		return stockProductService.deleteStock(sid);

	}

}
