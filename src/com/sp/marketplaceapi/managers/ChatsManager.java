package com.sp.marketplaceapi.managers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.sp.marketplaceapi.database.ConnectionManager;
import com.sp.marketplaceapi.models.ChatSummaryModel;

public class ChatsManager {
	
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
