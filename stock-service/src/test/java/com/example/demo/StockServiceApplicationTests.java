package com.example.demo;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


import org.junit.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.model.Stock;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.sun.jersey.core.header.MediaTypes;

//@SpringBootTest
class StockServiceApplicationTests extends AbstractTest {

	
    @Override
    @BeforeEach
	public void setup() {
    	super.setup();
    }
	
	@Test
    void getStockList() throws Exception{
		String uri="http://localhost:8010/stock-product-api/stock";
		MvcResult mvcResult= mock.perform(MockMvcRequestBuilders.get(uri).
				accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		
		int status=mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String json=mvcResult.getResponse().getContentAsString();
		Stock[] stock=super.mapFromJson(json, Stock[].class);
		assertTrue(stock.length>0);
		
	}
  
	@Test
    void getOneStock() throws Exception{
		String uri="http://localhost:8010/stock-product-api/stock/400";
		MvcResult mvcResult= mock.perform(MockMvcRequestBuilders.get(uri).
				accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		
		int status=mvcResult.getResponse().getStatus();
		assertEquals(200, status);		
	}
	
	@Test
     void createStock() throws Exception{
		String uri="http://localhost:8010/stock-product-api/stock";
		String inputJson = getStockJson();
			
		MvcResult mvcResult= mock.perform(MockMvcRequestBuilders.post(uri).
				contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
		
		int status=mvcResult.getResponse().getStatus();
		assertEquals(201, status);
		String json=mvcResult.getResponse().getContentAsString();
		assertEquals("Stocks got created", json);
		
	}

	public String getStockJson() throws JsonProcessingException {
	
		Stock s= new Stock();
		s.setSupplierId((long) 602);
		s.setQty(60);
		s.setSupplierContact("55656");
		s.setSupplierName("saiman");
		s.setValid("yes");
		
		String inputJson=super.mapToJson(s);
		return inputJson;
	}
	
	@Test
    void updateStock() throws Exception{
		String uri="http://localhost:8010/stock-product-api/stock/905";
		Stock s= new Stock();
		s.setSupplierId((long)905);
		s.setQty(20);
		s.setSupplierContact("949449");
		s.setSupplierName("samsung");
		s.setValid("yes");
		
		String inputJson=super.mapToJson(s);
		
		
		
		MvcResult mvcResult= mock.perform(MockMvcRequestBuilders.put(uri).
				contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
		
		int status=mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String json=mvcResult.getResponse().getContentAsString();
	    assertEquals(inputJson, json);
		
	}
	
	@Test
    void deleteStock() throws Exception{
		String uri="http://localhost:8010/stock-product-api/stock/404";
		
		MvcResult mvcResult= mock.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
		
		int status=mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	
	}

	
}
