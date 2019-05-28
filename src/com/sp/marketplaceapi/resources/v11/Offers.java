package com.sp.marketplaceapi.resources.v11;

import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

public class Offers {
	private int productId;
	
	public Offers(int productId) {
		this.productId = productId;
	}
	
	@DELETE
	@Path("{offerid}")
	public Response CancelOffer(
			@PathParam("offerid") int offerid,
			@QueryParam("vote") int vote) {
		// use id here to remove the offer from the product
		// use vote to store into votes tables
		return Response.ok("vote is " + vote).build();
	}
}
