package com.java.pms.ui;

import java.util.ArrayList;
import java.util.Scanner;

import com.java.pms.businessLogic.Product;
import com.java.pms.businessLogic.Service;
import com.java.pms.ioccontainer.DependencyInjector;
import com.java.pms.utility.Helper;

public class Application {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		Service service=DependencyInjector.getInstance().createInstance(Service.class);
		performTasks(sc,service);


	}

	private static void performTasks(Scanner sc, Service service) {
		boolean status=false;
		int id=0;
		Product product=null;
		ArrayList<Product> products = null;
		do {
			Helper.printMenu();
			int choice=Helper.usersChoice(sc);
			switch(choice) {
			case 1:
			    product = Helper.getProduct(sc);
				status=service.addProduct(product);
				if(!status) {
					System.out.println("The Product already exists");
					System.exit(0);
				}
				Helper.linebreak();
				break;
			case 2:
			    product = Helper.getProduct(sc);
			    status=service.updateProduct(product.getId(), product);
			    if(!status) {
					System.out.println("The Product do not exists");
					System.exit(-1);
				}
				Helper.linebreak();
				break;
			case 3:
			    id=Helper.getId(sc);
				status=service.deleteProduct(id);
				if(!status) {
					System.out.println("Product do not exist");
					System.exit(-2);
				}
				Helper.linebreak();
				break;
			case 4:
			    id=Helper.getId(sc);
			    product = service.getProductById(id);
			    if(product == null) {
					System.out.println("Product do not exist");
					System.exit(-3);
				}
				System.out.println(product.toString());
				Helper.linebreak();
				break;
			case 5:
			    products = service.getAllProducts();
				Helper.printProducts(products);
			    Helper.linebreak();
			    break;
			case 6:
				String name=Helper.getProductName(sc);
				products = service.getProductByName(name);
				Helper.printProducts(products);
				Helper.linebreak();
				break;
			default:
				System.exit(-5);
			}
			
		}while(true);
	}

}
