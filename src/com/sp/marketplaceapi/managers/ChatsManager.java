package com.sp.marketplaceapi.managers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.sp.marketplaceapi.database.ConnectionManager;
import com.sp.marketplaceapi.models.ChatSummaryModel;

public class ChatsManager {
	
	public void AddChatMessageToProduct(int productId, int buyerId, int sellerId, String message) {
		try {
			Connection conn = ConnectionManager.Get();
			PreparedStatement statement = conn.prepareStatement("INSERT INTO chat_messages "
					+ "(product_id, buyer_id, seller_id, message, date_sent) "
					+ "VALUES (?, ?, ?, ?, ?)");
			statement.setInt(1, productId);
			statement.setInt(2, buyerId);
			statement.setInt(3, sellerId);
			statement.setString(4, message);
			statement.setTimestamp(5, new Timestamp(new java.util.Date().getTime()));
			statement.execute();
			
		} catch (Exception ex) {
			
		}
	}
	
	public ArrayList<ChatSummaryModel> GetAllByProduct(int productId) {
		ArrayList<ChatSummaryModel> chats = new ArrayList<ChatSummaryModel>();
		
		try {
			Connection conn = ConnectionManager.Get();
			PreparedStatement statement = conn.prepareStatement("SELECT * FROM chats WHERE product_id = ?");
			statement.setInt(1, productId);
			ResultSet result = statement.executeQuery();
			
			while (result.next()) {
				ChatSummaryModel chat = new ChatSummaryModel();
				chat.Id = result.getInt("id");
				chat.ChatCreatedDateTime = result.getTimestamp("chat_created_datetime"); 
				chat.Link = "/products/" + productId + "/chats/" + chat.Id;
				chat.LatestMessageBody = "";
				
				chats.add(chat);
			}
			
			
		} catch (Exception ex) {
			
		}
		
		return chats;
	}
}
