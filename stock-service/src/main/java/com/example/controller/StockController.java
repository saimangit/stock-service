package com.example.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.exception.StockNotFoundException;
import com.example.model.Product;
import com.example.model.RawStock;
import com.example.repository.SupplierRepository;
import com.example.service.StockService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Validated
@RequestMapping("/stock-api")
@Api(value = "stock-service",description = "operations on the stock service")
public class StockController {
	

	@Autowired 
	StockService stockService;
	
    @ApiOperation(value = "view the list of stocks at supplier",response = Iterable.class)
    @RequestMapping(value = "/stock",method = RequestMethod.GET,produces = "application/json")
    public List<RawStock> getAllStock() {
	      return stockService.findAllStocks();
	}
	

	@ApiOperation(value = "view a particular stock by suppling sid",response = RawStock.class)
    @RequestMapping(value = "/stock/{sid}",method = RequestMethod.GET,produces ="application/json")
    //@Cacheable(value="product",key="#pid")
	public ResponseEntity<Optional<RawStock>> getStock(@PathVariable("sid") String sid) throws StockNotFoundException  { 
               Optional<RawStock> raw= stockService.getByStockId(Integer.parseInt(sid));
               if(!raw.isPresent()) {
            	   throw new StockNotFoundException("Stock not found for the sid:"+sid);
               }
               
               return ResponseEntity.ok().body(raw);
    }	
  
    
    


	@ApiOperation(value = "add a stock")
    @RequestMapping(value="/astock",method = RequestMethod.POST,produces = "application/json")
	public ResponseEntity<String> insertStock( @Valid @RequestBody RawStock rawStock) {
    	stockService.saveStock(rawStock);
		 return new ResponseEntity<String>("Stocks got created", HttpStatus.CREATED);
	}
	
	
	
  
    @ApiOperation(value = "update a stock")
    @RequestMapping(value="/astock/{sid}",method = RequestMethod.PUT,produces = "application/json")
  	public ResponseEntity<RawStock> updateStock(@PathVariable("sid") String sid, @Valid @RequestBody RawStock rawStock ) throws StockNotFoundException { 
      	  Optional<RawStock> p= stockService.getByStockId(Integer.parseInt(sid));
      	  
      	  if(!p.isPresent()) {
      		throw new StockNotFoundException("Stock not found for the sid:"+sid);
      	  }
      	  System.out.println("value of p " +p.isPresent());
      	  
      		RawStock prod=p.get();
      		
      		prod.setSupplierName(rawStock.getSupplierName());
      		prod.setProductName(rawStock.getProductName());
      		prod.setDiscount(rawStock.getDiscount());
      		prod.setListPrice(rawStock.getListPrice());
      		prod.setQty(rawStock.getDiscount());
      		prod.setValid(rawStock.getValid());
      		
      		
      	    stockService.saveStock(prod); 
      	  
      	    return ResponseEntity.ok().body(prod);
      	   
      	  } 
      	  
    @ApiOperation(value = "delete a stock")
    @RequestMapping(value="/astock/{sid}",method = RequestMethod.DELETE,produces = "application/json")
  	public Map<String,Boolean> deleteStock(@PathVariable("sid") String sid ) throws StockNotFoundException { 
    	Optional<RawStock> p= stockService.getByStockId(Integer.parseInt(sid));
    	  
    	  if(!p.isPresent()) {
    		throw new StockNotFoundException("Stock not found for the sid:"+sid);
    	  }
    	
    	  stockService.deleteStockById(Integer.parseInt(sid));
    	  Map<String,Boolean> map= new HashMap<String, Boolean>();
    	  map.put("deleted Stock", Boolean.TRUE);
    	  return map;
    	  
    	  
}
}