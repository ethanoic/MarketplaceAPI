package com.sp.marketplaceapi.resources;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import com.sp.marketplaceapi.models.Action;
import com.sp.marketplaceapi.models.Product;
import com.sp.marketplaceapi.models.ProductDetailsModel;
import com.sp.marketplaceapi.models.ProductsPagedResult;

import io.jsonwebtoken.Claims;

import com.sp.marketplaceapi.auth.jwt.JWTToken;
import com.sp.marketplaceapi.managers.ProductsManager;

@Path("products")
public class Products {
	
	private ActionsGenerator actionsGenerator = new ActionsGenerator();
	private ProductsManager productManager = new ProductsManager();
	
	@RolesAllowed("Member")
	// GET /products?category=xxxxx
	@GET
	@Produces("application/json")
	public Response GetAllProducts(@QueryParam("category") int categoryId,
			@QueryParam("pageSize") @DefaultValue("20") int pageSize,
			@QueryParam("pageIndex") @DefaultValue("0") int pageIndex) {
		// TODO
		// Search database return list
		// Provide pagination controls (future)
		
		ProductsPagedResult result = new ProductsPagedResult();
		
		int totalProductCount = productManager.CountProducts(categoryId);
		
		result.List = productManager.FindAllProducts(pageIndex, pageSize, categoryId);
		result.Next = (((pageIndex + 1) * pageSize) >= totalProductCount) ? "" : "/products/?pageSize=" + pageSize + "&pageIndex=" + (pageIndex + 1);
		result.Prev = pageIndex > 0 ? "/products/?pageSize=" + pageSize + "&pageIndex=" + (pageIndex - 1) : "";
		result.TotalCount = totalProductCount;
		
		/*
		Product[] productArray = new Product[2];
		productArray[0] = new Product();
		productArray[0].Name = "Macbook";

		productArray[1] = new Product();
		productArray[1].Name = "Huawwei P30";
		
		List<Product> productList = new ArrayList<Product>();
		productList.add(productArray[0]);
		productList.add(productArray[1]);
		*/
		
		return Response.ok(result).build();
	}
	
	// GET /products/{id}
	@RolesAllowed("Member")
	@GET
	@Path("{id}")
	@Produces("application/json")
	public Response GetOneProduct(@PathParam("id") int id, @Context HttpHeaders httpHeaders) {
		// TODO
		// Search database return 1 product object
		Product getProduct = this.productManager.GetProduct(id);
		ProductDetailsModel detailModel = null;
		
		// get current jwt and extract payload - role user id
		String authToken = httpHeaders.getHeaderString("Authorization");
		int userId = Utility.GetUserIdFromAuthToken(authToken);
		
		if (getProduct != null) {
			detailModel = new ProductDetailsModel(getProduct);
			detailModel.Actions = actionsGenerator.CreateProductDetailActions(getProduct, userId);
		}
		
		return Response.ok(detailModel).build();
	}
	
	@RolesAllowed("Member")
	@Path("{id}/offers")
	public Offers OffersSubResource(@PathParam("id") int id) {
		return new Offers(id);
	}
	
	// POST /products
	@RolesAllowed("Member")
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Response AddProduct(Product newProduct) {
		// TODO
		// Validate data
		
		// Insert into database
		int id = productManager.CreateProduct(newProduct);
		
		// return the id of the product
		return Response.ok(id).build();
	}
	
	// PUT /products/1 {...}
	@RolesAllowed("Member")
	@PUT
	@Path("{id}")
	@Consumes("application/json")
	public Response UpdateProduct(@PathParam("id") int id, 
									Product updateProduct) {
		boolean result = productManager.UpdateProduct(id, updateProduct);
		return Response.ok(result).build();
	}
	
	@RolesAllowed("Member")
	@DELETE
	@Path("{id}")
	public Response RemovalProduct(@PathParam("id") int id) {
		productManager.RemoveProduct(id);
		return Response.ok().build();
	}
	
	@RolesAllowed("Member")
	@Path("{id}/chat")
	public Chats ChatsSubResource(@PathParam("id") int id) {
		return new Chats(id);
	}
}
