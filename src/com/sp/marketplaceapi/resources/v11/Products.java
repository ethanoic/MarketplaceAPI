package com.sp.marketplaceapi.resources.v11;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import com.sp.marketplaceapi.resources.v11.Offers;

@Path("v1.1/products")
public class Products {
	
	@Path("{id}/offers")
	public Offers OffersSubResource(@PathParam("id") int id) {
		return new Offers(id);
	}
}
