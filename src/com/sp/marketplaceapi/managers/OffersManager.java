package com.sp.marketplaceapi.managers;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.sp.marketplaceapi.database.ConnectionManager;
import com.sp.marketplaceapi.models.OfferModel;

public class OffersManager {
	
	public List<OfferModel> GetOffers(int productId) {
		ArrayList<OfferModel> offers = new ArrayList<OfferModel>();
		try {
			Connection conn = ConnectionManager.Get();
			PreparedStatement statement = conn.prepareStatement("SELECT * FROM offers WHERE product_id = ?");
			statement.setInt(1, productId);
			ResultSet result = statement.executeQuery();
			
			while(result.next()) {
				OfferModel offer = new OfferModel();
				offer.Price = result.getDouble("price");
				offer.MeetupLocation = result.getString("meet_up_location");
				offer.OfferTimeStamp = result.getTimestamp("offer_datetime");
				
				offers.add(offer);
			}

		} catch(Exception ex) {
			System.out.println(ex.getMessage());
		}	
		return offers;
	}
	
	public void AddOffer(int buyerId, double price, String meetUpLocation, int productId) {
		
		if (!this.HasOffered(buyerId, productId)) {
			try {
				Connection conn = ConnectionManager.Get();
				PreparedStatement statement = conn.prepareStatement("INSERT INTO offers "
						+ "(price, meet_up_location, offer_datetime, product_id, buyer_id) VALUES "
						+ "(?, ?, ?, ?, ?)");
				statement.setDouble(1, price);
				statement.setString(2, meetUpLocation);
				statement.setTimestamp(3, new Timestamp(new java.util.Date().getTime()));
				statement.setInt(4, productId);
				statement.setInt(5, buyerId);
				
				statement.execute();
			} catch(Exception ex) {
				
			}	
		}
	}
	
	public boolean HasOffered(int buyerId, int productId) {
		boolean hasOffered = false;
		try {
			Connection conn = ConnectionManager.Get();
			PreparedStatement statement = conn.prepareStatement("SELECT * FROM offers WHERE buyer_id = ? AND product_id = ?");
			statement.setInt(1, buyerId);
			statement.setInt(2, productId);
			ResultSet result = statement.executeQuery();
			hasOffered = result.next();
			
		} catch (Exception ex) {
			
		}
		return hasOffered;
	}
	
	public void RemoveOffer(int buyerId, int productId) {
		try {
			Connection conn = ConnectionManager.Get();
			PreparedStatement statement = conn.prepareStatement("DELETE FROM offers "
					+ "WHERE buyer_id = ? AND product_id = ?");
			statement.setInt(1, buyerId);
			statement.setInt(2, productId);
			statement.execute();
		} catch (Exception ex) {
			
		}
	}
}
