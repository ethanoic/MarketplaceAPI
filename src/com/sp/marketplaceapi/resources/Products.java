package com.sp.marketplaceapi.resources;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import com.sp.marketplaceapi.models.Product;

@Path("products")
public class Products {
	
	// GET /products?category=xxxxx
	@GET
	@Produces("application/json")
	public Response GetAllProducts(@QueryParam("category") String category) {
		// TODO
		// Search database return list
		// Provide pagination controls (future)
		Product[] productArray = new Product[2];
		productArray[0] = new Product();
		productArray[0].Name = "Macbook";

		productArray[1] = new Product();
		productArray[1].Name = "Huawwei P30";
		
		List<Product> productList = new ArrayList<Product>();
		productList.add(productArray[0]);
		productList.add(productArray[1]);
		
		return Response.ok(productList).build();
	}
	
	// GET /products/{id}
	@GET
	@Path("{id}")
	@Produces("application/json")
	public Response GetOneProduct(@PathParam("id") int id) {
		// TODO
		// Search database return 1 product object
		Product getProduct = new Product();
		return Response.ok(getProduct).build();
	}
	
	// POST /products
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Response AddProduct(Product newProduct) {
		// TODO
		// Validate data
		// Insert into database
		// return the id of the product
		return Response.ok(newProduct).build();
	}
	
	
}
