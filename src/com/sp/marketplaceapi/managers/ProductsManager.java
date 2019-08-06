package com.sp.marketplaceapi.managers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.sp.marketplaceapi.database.ConnectionManager;
import com.sp.marketplaceapi.models.Product;

public class ProductsManager {
	
	public int GetSellerId(int productId) {
		int sellerId = 0;
		try {
			Connection conn = ConnectionManager.Get();
			PreparedStatement statement = conn.prepareStatement("SELECT owned_by_user FROM products WHERE id = ?");
			statement.setInt(1, productId);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				sellerId = result.getInt("owned_by_user");
			}
		} catch(Exception ex) {
		}
		return sellerId;
	}
	
	public Boolean isOwnedByUser(int productId, int userId) {
		Boolean isOwned = false;
		try {
			Connection conn = ConnectionManager.Get();
			PreparedStatement statement = conn.prepareStatement("SELECT * FROM products WHERE id = ? AND owned_by_user = ?");
			statement.setInt(1, productId);
			statement.setInt(2, userId);
			ResultSet result = statement.executeQuery();
			isOwned = result.next();
		} catch(Exception ex) {
		}
		return isOwned;
	}
	
	public Product GetProduct(int id) {
		Product findProduct = null;
		
		try {
			Connection conn = ConnectionManager.Get();
			PreparedStatement statement = conn.prepareStatement("SELECT * FROM products WHERE id = ?");
			statement.setInt(1, id);
			
			ResultSet result = statement.executeQuery();
			
			ArrayList products = this.ResultSetToProductList(result);
			if (products.size() == 1)
				findProduct = (Product) products.get(0);
			
		} catch(Exception ex) {
		}
		
		return findProduct;
	}
	
	public boolean RemoveProduct(int id) {
		boolean result = true;
		try {
			Connection conn = ConnectionManager.Get();
			PreparedStatement statement = conn.prepareStatement("DELETE FROM products WHERE id = ?");
			statement.setInt(1, id);
			
			statement.execute();
		} catch(Exception ex) {
			result = false;
		}
		return result;
	}
	
	private ArrayList ResultSetToProductList(ResultSet result) throws Exception{
		ArrayList list = new ArrayList();
		try {
			while (result.next()) {
				Product product = new Product();
				product.Id = result.getInt("id");
				product.Name = result.getString("name");
				product.Price = result.getDouble("price");
				product.Images = result.getString("images").split(",");
				product.FullDescription = result.getString("full_description");
				product.DealTypes = result.getString("deal_types").split(",");
				product.ShortDescription = result.getString("short_description");
				product.HeroImage = result.getString("hero_image");
				product.Likes = result.getInt("likes");
				product.CategoryId = result.getInt("category_id");
				product.OwnedByUser = result.getInt("owned_by_user");
				
				list.add(product);
			}
		} catch (Exception ex) {
			throw ex;
		}
		return list;
	}
	
	public ArrayList FindAllProducts() {
		ArrayList list = new ArrayList();
		
		// DB stuff here
		Connection conn = ConnectionManager.Get();
		
		try {
			PreparedStatement statement = conn.prepareStatement("SELECT * FROM products");
			ResultSet result = statement.executeQuery();
			
			list = this.ResultSetToProductList(result);
			
		} catch (Exception ex) {
			
		}
		
		
		return list;
	}
	
	public ArrayList FindAllProducts(int page, int pageSize, int categoryId) {

		ArrayList list = new ArrayList();
		
		// DB stuff here
		Connection conn = ConnectionManager.Get();
		
		int offset = page * pageSize;
		
		try {
			PreparedStatement statement = conn.prepareStatement("SELECT * FROM products WHERE category_id = ? LIMIT ? OFFSET ?");
			statement.setInt(1, categoryId);
			statement.setInt(2, pageSize);
			statement.setInt(3, offset);
			
			ResultSet result = statement.executeQuery();
			
			list = this.ResultSetToProductList(result);
			
		} catch (Exception ex) {
			
		}
		
		return list;
	}

	public int CountProducts() {
		int count = 0;
		
		try {
			Connection conn = ConnectionManager.Get();
			PreparedStatement statement = conn.prepareStatement("SELECT COUNT(*) FROM products");
			ResultSet result = statement.executeQuery();
			
			if (result.next()) {
				count = result.getInt(1);
			}
			
		} catch (Exception ex) {
			
		}
		
		return count;
	}
	
	public int CountProducts(int categoryId) {
		int count = 0;
		
		try {
			Connection conn = ConnectionManager.Get();
			PreparedStatement statement = conn.prepareStatement("SELECT COUNT(*) FROM products WHERE category_id = ?");
			statement.setInt(1, categoryId);
			ResultSet result = statement.executeQuery();
			
			if (result.next()) {
				count = result.getInt(1);
			}
			
		} catch (Exception ex) {
			
		}
		
		return count;
	}

	public int CreateProduct(Product newProduct) {
		int id = 0;
		
		try {
			Connection conn = ConnectionManager.Get();
			PreparedStatement statement = conn.prepareStatement("INSERT INTO products "
					+ "(name, price, full_description, deal_types, short_description, "
					+ "category_id, images, hero_image, likes, owned_by_user) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", 
					Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, newProduct.Name);
			statement.setDouble(2, newProduct.Price);
			statement.setString(3, newProduct.FullDescription);
			statement.setString(4, String.join(",", newProduct.DealTypes));
			statement.setString(5, newProduct.ShortDescription);
			statement.setInt(6, newProduct.CategoryId);
			statement.setString(7, "");
			statement.setString(8, "");
			statement.setInt(9, 0);
			statement.setInt(10, newProduct.OwnedByUser);
			
			statement.execute();
			
			ResultSet result = statement.getGeneratedKeys();
			if (result.next()) {
				id = result.getInt(1);
			}
			
		} catch (Exception ex) {
			
		}
		
		return id;
	}
	
	public boolean UpdateProduct(int id, Product updatedProduct) {
		boolean result = true;
		try {
			Connection conn = ConnectionManager.Get();
			PreparedStatement statement = conn.prepareStatement("UPDATE products SET name = ?, "
					+ "	price = ?, full_description = ?, deal_types = ?, short_description = ?, "
					+ "category_id = ?, images = ?, hero_image = ?, likes = ? "
					+ "WHERE id = ?");
			statement.setString(1, updatedProduct.Name);
			statement.setDouble(2, updatedProduct.Price);
			statement.setString(3, updatedProduct.FullDescription);
			statement.setString(4, String.join(",", updatedProduct.DealTypes));
			statement.setString(5, updatedProduct.ShortDescription);
			statement.setInt(6, updatedProduct.CategoryId);
			statement.setString(7, String.join(",", updatedProduct.Images));
			statement.setString(8, updatedProduct.HeroImage);
			statement.setInt(9, updatedProduct.Likes);
			statement.setInt(10, id);
			
			statement.execute();
			
		} catch (Exception ex) {
			result = false;
			// log the exception into a logging facility or db or log file
		}
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
