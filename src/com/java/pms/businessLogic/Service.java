package com.java.pms.businessLogic;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.java.pms.facade.DataFacade;
import com.java.pms.ioccontainer.DependencyInjector;
import com.java.pms.utility.Helper;

public class Service {

	DataFacade dataFacade = DependencyInjector.getInstance().createInstance(DataFacade.class);
	Scanner sc = new Scanner(System.in);

	public boolean addProduct(Product product) {
		try {
			return dataFacade.addProduct(product);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public boolean updateProduct(int id, Product product) {
		try {
			return dataFacade.updateProduct(id, product);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public boolean deleteProduct(int id) {
		try {
			return dataFacade.deleteProduct(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public Product getProductById(int id) {
		try {
			return dataFacade.getProductById(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<Product> getAllProducts() {
		ArrayList<Product> listOfProducts;
		try {
			listOfProducts = dataFacade.getAllProducts();
			Helper.sortMenu();
			int choice = Helper.usersChoice(sc);
			listOfProducts = Helper.performSort(choice, listOfProducts);
			return listOfProducts;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	public ArrayList<Product> getProductByName(String name) {
		try {
			return dataFacade.getProductByName(name);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
