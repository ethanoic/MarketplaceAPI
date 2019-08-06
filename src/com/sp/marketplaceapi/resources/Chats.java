package com.sp.marketplaceapi.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import com.sp.marketplaceapi.managers.ChatsManager;
import com.sp.marketplaceapi.managers.ProductsManager;
import com.sp.marketplaceapi.models.ChatMessageModel;

public class Chats {
	
	private int productId;
	private ChatsManager manager = new ChatsManager();
	private ProductsManager productsManager = new ProductsManager();
	
	public Chats(int productId) {
		this.productId = productId;
	}
	
	@GET
	public Response GetAll() {
		// from chat manager class return all chats for product
		return Response.ok().build();
	}
	
	/*
	 * POST /products/{product id}/chat
	 * {
	 * 	"message": "...."
	 * }
	 */
	
	@POST
	@Consumes("application/json")
	public Response AddChatMessage(ChatMessageModel message, @Context HttpHeaders httpHeaders) {
		
		String authToken = httpHeaders.getHeaderString("Authorization");
		int userId = Utility.GetUserIdFromAuthToken(authToken);
		
		// check if user Id is a buyer or seller or if own product
		Boolean isUserSeller = productsManager.isOwnedByUser(this.productId, userId);
		
		if (isUserSeller) {
			// if userId is seller, get buyer Id from message model 
			manager.AddChatMessageToProduct(this.productId, message.BuyerId, userId, message.Message);	
		} else {
			int sellerId = productsManager.GetSellerId(this.productId);
			// if userId is buyer, add chat message to product
			manager.AddChatMessageToProduct(this.productId, userId, sellerId, message.Message);	
		}
		
		// manager class add chat message
		return Response.ok().build();
	}
}
