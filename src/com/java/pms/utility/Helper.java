package com.java.pms.utility;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.java.pms.businessLogic.Product;

public class Helper {
	public static void printMenu() {
		System.out.println("1-> Add a new Product\n2->Update a Particular Product\n"
				+ "3->Delete a particular Product\n4->Get a particular Product\n" + "5->Get all the Products\n"
				+ "6-> Get products based on name\n" + "7->Any other number to exit the Program");
	}

	public static int usersChoice(Scanner sc) {
		int choice = sc.nextInt();
		return choice;
	}

	public static Product getProduct(Scanner sc) {
		System.out.println("Enter the id number of the product");
		int id = sc.nextInt();
		System.out.println("Enter the name of the Product : ");
		sc.nextLine();
		String name = sc.nextLine();
		System.out.println("Enter the price of the Product : ");
		double price = sc.nextDouble();
		System.out.println("Enter the description of the product : ");
		sc.nextLine();
		String description = sc.nextLine();
		return new Product(id, name, price, description);
	}

	public static void linebreak() {
		System.out.println("------------------------------------------------------------------------");
	}

	public static int getId(Scanner sc) {
		System.out.println("Enter the id of the Product : ");
		int id = sc.nextInt();
		return id;
	}

	public static void sortMenu() {
		System.out.println("sort the data and print on the basis of : \n" + "1->id\n2->name\n3->price\n4->description");
	}

	public static ArrayList<Product> performSort(int choice, ArrayList<Product> listOfProducts) {
		switch (choice) {
		case 1:
			return (ArrayList<Product>) listOfProducts.stream().sorted(Comparator.comparingInt(Product::getId))
					.collect(Collectors.toList());
		case 2:
			return (ArrayList<Product>) listOfProducts.stream().sorted((o1, o2) -> {
				return (o1.getName().compareToIgnoreCase(o2.getName()));
			}).collect(Collectors.toList());
		case 3:
			return (ArrayList<Product>) listOfProducts.stream().sorted(Comparator.comparingDouble(Product::getPrice))
					.collect(Collectors.toList());
		default:
			return (ArrayList<Product>) listOfProducts.stream().sorted((o1, o2) -> {
				return (o1.getDescription().compareToIgnoreCase(o2.getDescription()));
			}).collect(Collectors.toList());
		}
	}

	public static void printProducts(ArrayList<Product> products) {
		for (Product product : products) {
			System.out.println(product.toString());
		}
	}

	public static String getProductName(Scanner sc) {
		System.out.println("Enter the name of the product");
		sc.nextLine();
		String name = sc.nextLine();
		return name;
	}
}
