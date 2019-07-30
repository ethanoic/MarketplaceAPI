package com.sp.marketplaceapi.resources;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import com.sp.marketplaceapi.models.ChatMessageModel;

public class Chats {
	
	private int productId;
	
	public Chats(int productId) {
		this.productId = productId;
	}
	
	@GET
	public Response GetAll() {
		// from chat manager class return all chats for product
		return Response.ok().build();
	}
	
	@POST
	public Response StartChat(ChatMessageModel message) {
		
		// manager class add chat message
		return Response.ok().build();
	}
}
