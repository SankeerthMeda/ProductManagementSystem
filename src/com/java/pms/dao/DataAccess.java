package com.java.pms.dao;

import java.util.ArrayList;

import com.java.pms.businessLogic.Product;

public interface DataAccess {
	boolean addProduct(Product product) throws Exception;

	boolean updateProduct(int id, Product product) throws Exception;

	boolean deleteProduct(int id) throws Exception;

	Product getProductById(int id) throws Exception;

	ArrayList<Product> getAllProducts() throws Exception;

	ArrayList<Product> getProductByName(String name) throws Exception;
}
