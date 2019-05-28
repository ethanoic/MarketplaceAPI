package com.sp.marketplaceapi.resources;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import com.sp.marketplaceapi.models.Action;
import com.sp.marketplaceapi.models.Product;
import com.sp.marketplaceapi.models.ProductDetailsModel;
import com.sp.marketplaceapi.models.ProductsPagedResult;

@Path("products")
public class Products {
	
	private ActionsGenerator actionsGenerator = new ActionsGenerator();
	
	// GET /products?category=xxxxx
	@GET
	@Produces("application/json")
	public Response GetAllProducts(@QueryParam("category") String category,
			@QueryParam("pageSize") @DefaultValue("20") int pageSize,
			@QueryParam("pageIndex") @DefaultValue("0") int pageIndex) {
		// TODO
		// Search database return list
		// Provide pagination controls (future)
		
		ProductsPagedResult result = new ProductsPagedResult();
		result.List = new ArrayList();
		result.Next = "";
		result.Prev = "";
		result.TotalCount = 0;
		
		Product[] productArray = new Product[2];
		productArray[0] = new Product();
		productArray[0].Name = "Macbook";

		productArray[1] = new Product();
		productArray[1].Name = "Huawwei P30";
		
		List<Product> productList = new ArrayList<Product>();
		productList.add(productArray[0]);
		productList.add(productArray[1]);
		
		return Response.ok(result).build();
	}
	
	// GET /products/{id}
	@GET
	@Path("{id}")
	@Produces("application/json")
	public Response GetOneProduct(@PathParam("id") int id) {
		// TODO
		// Search database return 1 product object
		Product getProduct = new Product();
		
		// create instance of product details model
		ProductDetailsModel detailModel = new ProductDetailsModel();
		detailModel.Actions = actionsGenerator.CreateProductDetailActions(getProduct);
		
		return Response.ok(getProduct).build();
	}
	
	@Path("{id}/offers")
	public Offers OffersSubResource(@PathParam("id") int id) {
		return new Offers(id);
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
