package com.sp.marketplaceapi.resources;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import com.sp.marketplaceapi.auth.jwt.JWTToken;
import com.sp.marketplaceapi.managers.OffersManager;
import com.sp.marketplaceapi.models.CreateOffer;
import com.sp.marketplaceapi.models.OfferModel;

import io.jsonwebtoken.Claims;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.DELETE;

/*
 * sub resource of products
 */
public class Offers {
	
	private int productId;
	private OffersManager manager = new OffersManager();
	
	public Offers(int productId) {
		this.productId = productId;
	}
	
	@POST
	@Consumes("application/json")
	public Response AddOffer(CreateOffer newOffer, @Context HttpHeaders httpHeaders) {
		// get buyer user id
		// get current jwt and extract payload - role user id
		String authToken = httpHeaders.getHeaderString("Authorization");
		int userId = Utility.GetUserIdFromAuthToken(authToken);
		
		// send to object to db to create offer
		manager.AddOffer(userId, newOffer.Price, newOffer.MeetupLocation, this.productId);
		
		return Response.ok().build();
	}
	
	@GET
	@Produces("application/json")
	public Response GetOffers() {
		List<OfferModel> offers = manager.GetOffers(this.productId);
		return Response.ok(offers).build();
	}
	
	/*
	 * DELETE /products/{id}/offers
	 */
	
	@DELETE
	public Response CancelOffer(@Context HttpHeaders httpHeaders) {
		// use id here to remove the offer from the product
		String authToken = httpHeaders.getHeaderString("Authorization");
		int userId = Utility.GetUserIdFromAuthToken(authToken);
		
		manager.RemoveOffer(userId, this.productId);
		
		return Response.ok().build();
	}
	
}
