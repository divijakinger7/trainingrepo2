package com.seclore.entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.seclore.entity.DbManager;

// DATA ACCESS OBJECT
public class ProductDAO {
	public List<Product> fetchAll() {
		List<Product> allProducts = new ArrayList<Product>();
		Connection conn = null;
		try {
			conn = DbManager.getConnection();
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM product");
			ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
            	Product p = new Product();
            	p.setId(rs.getInt(4));
            	p.setName(rs.getString(1));
            	p.setPrice(rs.getDouble(2));
            	p.setQuantity(rs.getInt(3));
            	allProducts.add(p);
            	
            }
            return allProducts;
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		finally {
			try { conn.close(); } catch(Exception e) { }
		}
	}
	public List<Product> fetch(int from,int row) {
		List<Product> allProducts = new ArrayList<Product>();
		Connection conn = null;
		try {
			conn = DbManager.getConnection();
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM product limit ?,?");
            stmt.setInt(1, from);
            stmt.setInt(2, row);
			ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
            	Product p = new Product();
            	p.setId(rs.getInt(4));
            	p.setName(rs.getString(1));
            	p.setPrice(rs.getDouble(2));
            	p.setQuantity(rs.getInt(3));
            	allProducts.add(p);
            	
            }
            return allProducts;
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		finally {
			try { conn.close(); } catch(Exception e) { }
		}
	}

	
	public int add(Product product) {
        String name = product.getName();
        Double price = product.getPrice();
        int quantity = product.getQuantity();
        String generatedColumns[] = { "id" };

		Connection conn = null;

        try {
			conn = DbManager.getConnection();
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO product(name, price, qty) VALUES(?, ?, ?)",generatedColumns);
            stmt.setString(1, name);
            stmt.setDouble(2, price);
            stmt.setInt(3, quantity);
            int affectedRows = stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                System.out.println("I AM HERE");
                int id = rs.getInt(1);
                return id;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
		return 0;
	}


	public void delete(int id) {
		Connection conn = null;

		String sql = "DELETE FROM product WHERE id = ?";
        try {
			conn = DbManager.getConnection();

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, Integer.toString(id));
            
            int rowsAffected = stmt.executeUpdate();
            System.out.println(rowsAffected + " row(s) deleted.");

        } catch (Exception e) {
            e.printStackTrace();
        }
		
	}
	
	public Product fetchById(int id) {
		Connection conn = null;
		try {
			conn = DbManager.getConnection();
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM product where id = ?");
            stmt.setString(1, Integer.toString(id));
            ResultSet rs = stmt.executeQuery();
        	Product p = new Product();

            while (rs.next()) {
            	p.setId(rs.getInt(4));
            	p.setName(rs.getString(1));
            	p.setPrice(rs.getDouble(2));
            	p.setQuantity(rs.getInt(3));
            	
            }
            return p;
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		finally {
			try { conn.close(); } catch(Exception e) { }
		}
	}


	public void update(Product product) {
		Connection conn = null;
        String sql = "UPDATE product SET name=?,price=?,qty=? where id=? ";
        try {
			conn = DbManager.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, product.getName());
            stmt.setString(2, Double.toString(product.getPrice()));
            stmt.setString(3, Integer.toString(product.getQuantity()));
            stmt.setString(4, Integer.toString(product.getId()));

            int rowsAffected = stmt.executeUpdate();
            System.out.println(rowsAffected + " row(s) updated.");

        } catch (Exception e) {
            e.printStackTrace();
        }
		
	}
	
}
