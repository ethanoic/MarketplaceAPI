package com.sp.marketplaceapi.resources;

import javax.ws.rs.core.Response;

import com.sp.marketplaceapi.models.CreateOffer;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.DELETE;

/*
 * sub resource of products
 */
public class Offers {
	
	private int productId;
	
	public Offers(int productId) {
		this.productId = productId;
	}
	
	@POST
	@Consumes("application/json")
	public Response AddOffer(CreateOffer newOffer) {
		// call offers DB to send offer information
		// send to db wuth new Offer and product id
		return Response.ok("this is post an offer for " + this.productId + ' ' + newOffer.Price).build();
	}
	
	@GET
	public Response GetOffers() {
		return Response.ok("get offers for product").build();
	}
	
	/*
	 * DELETE /products/{id}/offers/{offerid}
	 */
	
	@DELETE
	@Path("{offerid}")
	public Response CancelOffer(@PathParam("offerid") int offerid) {
		// use id here to remove the offer from the product
		return Response.ok().build();
	}
	
}
