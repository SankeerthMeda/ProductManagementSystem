package com.java.pms.facade;

import java.sql.SQLException;
import java.util.ArrayList;

import com.java.pms.businessLogic.Product;
import com.java.pms.daoImpl.DataAccessImpl;
import com.java.pms.ioccontainer.DependencyInjector;

public class DataFacade {
	DataAccessImpl dao = DependencyInjector.getInstance().createInstance(DataAccessImpl.class);
	public boolean addProduct(Product product) throws SQLException {
		return dao.addProduct(product);
	}

	public boolean updateProduct(int id, Product product) throws SQLException {
		return dao.updateProduct(id, product);
	}

	public boolean deleteProduct(int id) throws SQLException {
		return dao.deleteProduct(id);
	}

	public Product getProductById(int id) throws SQLException {
		return dao.getProductById(id);
	}
	public ArrayList<Product> getAllProducts() throws SQLException {
		return dao.getAllProducts();
	}
	public ArrayList<Product> getProductByName(String name) throws SQLException {
		return dao.getProductByName(name);
	}
}
