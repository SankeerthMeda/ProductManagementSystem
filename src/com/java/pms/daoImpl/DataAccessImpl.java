package com.java.pms.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.stream.Collectors;

import com.java.pms.businessLogic.Product;
import com.java.pms.dao.DataAccess;
import com.java.pms.databaseHandler.JDBCApp;
import com.java.pms.ioccontainer.DependencyInjector;

public class DataAccessImpl implements DataAccess {

	JDBCApp jdbc = DependencyInjector.getInstance().createInstance(JDBCApp.class);
	Connection connection = jdbc.getConnection();
	PreparedStatement ps = jdbc.getPs();

	@Override
	public boolean addProduct(Product product) throws SQLException {
		if (getProductById(product.getId()) != null) {
			try {
				jdbc.closeConnection(connection);
			} catch (SQLException e) {
				throw e;
			}
			return false;
		}
		try {
			ps = connection.prepareStatement("INSERT INTO PRODUCTS(id,name,price,description) values(?,?,?,?)");
			ps.setInt(1, product.getId());
			ps.setString(2, product.getName());
			ps.setDouble(3, product.getPrice());
			ps.setString(4, product.getDescription());
			int res = ps.executeUpdate();
			if (res <= 0) {
				System.out.println("did not inserted");
				try {
					jdbc.closeConnection(connection);
				} catch (SQLException e) {
					throw e;
				}
				return false;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw e;
		}

		return true;
	}

	@Override
	public boolean updateProduct(int id, Product product) throws SQLException {
		if (getProductById(id) == null) {
			try {
				jdbc.closeConnection(connection);
			} catch (SQLException e) {
				throw e;
			}
			return false;
		}
		try {
			ps = connection.prepareStatement("update products set name=?,price=?,description=? where id=?");
			ps.setInt(4, id);
			ps.setString(1, product.getName());
			ps.setDouble(2, product.getPrice());
			ps.setString(3, product.getDescription());
			int res = ps.executeUpdate();
			if (res <= 0) {
				System.out.println("did not updated");
				return false;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw e;
		}
		return true;
	}

	@Override
	public boolean deleteProduct(int id) throws SQLException {
		if (getProductById(id) == null) {
			try {
				jdbc.closeConnection(connection);
			} catch (SQLException e) {
				throw e;
			}
			return false;
		}
		try {
			ps = connection.prepareStatement("delete from products where id=?");
			ps.setInt(1, id);
			int res = ps.executeUpdate();
			if (res > 0) {
				System.out.println("deletion success");
			} else {
				System.out.println("did not deleted");
				return false;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw e;
		}
		return true;
	}

	@Override
	public Product getProductById(int id) throws SQLException {
		Product product = null;
		try {
			ps = connection.prepareStatement("select id,name,price,description from products where id=?");
			ps.setInt(1, id);
			ResultSet res = ps.executeQuery();

			if (res.getFetchSize() == 0) {
				return null;
			}
			while (res.next()) {
				product = new Product(res.getInt(1), res.getString(2), res.getDouble(3), res.getString(4));
			}
		} catch (SQLException e) {
			throw e;
		}
		return product;
	}

	@Override
	public ArrayList<Product> getAllProducts() throws SQLException {
		ArrayList<Product> fin = new ArrayList<>();
		try {
			ps = connection.prepareStatement("select id,name,price,description from products");
			ResultSet res = ps.executeQuery();
			while (res.next()) {
				fin.add(new Product(res.getInt(1), res.getString(2), res.getDouble(3), res.getString(4)));
			}
		} catch (SQLException e) {
			throw e;
		}
		return fin;
	}

	@Override
	public ArrayList<Product> getProductByName(String name) throws SQLException {
		ArrayList<Product> products = null;
		try {
			products = (ArrayList<Product>) getAllProducts().stream().filter(tempProduct -> {
				return tempProduct.getName().contains(name);
			}).collect(Collectors.toList());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw e;
		}
		return products;
	}

}
