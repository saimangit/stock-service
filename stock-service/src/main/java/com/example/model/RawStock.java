package com.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Entity
@Table(name="stock")
public class RawStock {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="supplier_id")
  private Long supplierId;
  @NotBlank(message="supplier name is mandatory")
  @Column(name="supplier_name",nullable = false)  
  private String supplierName;
  @Column(name="qty",nullable = false)  
  private int qty;
  @NotBlank(message="valid must not be null")
  @Column(name="valid",nullable = false)  
  private String valid;
  @NotBlank(message="product name must not be null")
  @Column(name="product_name",nullable = false)
  private String productName;
  @Column(name="list_price",nullable = false) 
  private int listPrice;
  @Column(name="discount",nullable = false)   
  private int discount;
  
  @Column(name="supplier_contact",nullable=false)
  private String supplierContact;
  @Column(name="version",nullable=false)
  private int version;
  @NotBlank(message="createdUser must not be null")
  @Column(name="created_user",nullable = false) 
  private String createdUser;
  @NotBlank(message="createdDate must not be null")
  @Column(name="created_date",nullable = false)   
  private String createdDate;
   

public String getSupplierContact() {
	return supplierContact;
}
public void setSupplierContact(String supplierContact) {
	this.supplierContact = supplierContact;
}
public int getVersion() {
	return version;
}
public void setVersion(int version) {
	this.version = version;
}
public String getCreatedUser() {
	return createdUser;
}
public void setCreatedUser(String createdUser) {
	this.createdUser = createdUser;
}
public String getCreatedDate() {
	return createdDate;
}
public void setCreatedDate(String createdDate) {
	this.createdDate = createdDate;
}
public String getProductName() {
	return productName;
}
public void setProductName(String productName) {
	this.productName = productName;
}
public int getListPrice() {
	return listPrice;
}
public void setListPrice(int listPrice) {
	this.listPrice = listPrice;
}
public int getDiscount() {
	return discount;
}
public void setDiscount(int discount) {
	this.discount = discount;
}
public Long getSupplierId() {
	return supplierId;
}
public void setSupplierId(Long supplierId) {
	this.supplierId = supplierId;
}
public String getSupplierName() {
	return supplierName;
}
public void setSupplierName(String supplierName) {
	this.supplierName = supplierName;
}
public int getQty() {
	return qty;
}
public void setQty(int qty) {
	this.qty = qty;
}
public String getValid() {
	return valid;
}
public void setValid(String valid) {
	this.valid = valid;
}
@Override
public String toString() {
	return "RawStock [supplierId=" + supplierId + ", supplierName=" + supplierName + ", qty=" + qty + ", valid=" + valid
			+ ", productName=" + productName + ", listPrice=" + listPrice + ", discount=" + discount + "]";
}

  
  
  
}
