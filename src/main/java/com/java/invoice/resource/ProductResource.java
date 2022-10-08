package com.java.invoice.resource;



import java.util.ArrayList;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import com.java.invoice.Product;
import com.java.invoice.service.ProductService;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;


@Path("/products")
public class ProductResource {
	
	ProductService productService = new ProductService();
	
	//Fetch All Products in the Store
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public Response getAllProducts(@DefaultValue("0") @QueryParam("search_product") String pattern) {
		if(pattern.equals("0")) {
		ArrayList<Product> resultProducts =  productService.getAllProducts();
		if(resultProducts.isEmpty()) {
			return Response.status(404).entity("<message>No Products in the Store</message>").build();
		}
		GenericEntity<ArrayList<Product>> products = new GenericEntity<ArrayList<Product>>(resultProducts) {};
		return Response.ok(products).build();
		}
		else {
		ArrayList<Product> resultProducts =  productService.searchProducts(pattern.toLowerCase());
		if(resultProducts.isEmpty()) {
			return Response.status(404).entity("<message>No Products in the Store</message>").build();
		}
		GenericEntity<ArrayList<Product>> products = new GenericEntity<ArrayList<Product>>(resultProducts) {};
		return Response.ok(products).build();
		}
	}	
	
	//Fetch a Specific Product using Id
	@Path("/{productId}")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public Response getProduct(@PathParam("productId") int productId) {
		Product resultProduct  =  productService.getProduct(productId);
		if(resultProduct == null) {
			return Response.status(404).entity("<message>No Product Found</message>").build();
		}
		return Response.ok(resultProduct).build();
	}
	
	//Delete a Specific Product
	@Path("/{productId}")
	@DELETE
	@Produces(MediaType.APPLICATION_XML)
	public Response deleteProduct(@PathParam("productId") int productId) {
		Product product = productService.deleteProduct(productId);
		if(product == null)
			return Response.status(404).entity("<message>No Products Found</message>").build();
		return Response.ok(product).build();
	}
	
	//Create a Product
	 @POST
	 @Consumes(MediaType.APPLICATION_XML)
	 @Produces(MediaType.APPLICATION_XML)
	 public Response createProduct(Product product){
		 String message = productService.createProduct(product);
		 if(message.equalsIgnoreCase("Done"))
			 return Response.status(201).entity(product).build();
		 if(message.contains("Invalid"))
			 return Response.status(Status.BAD_REQUEST).entity(message).build();
		 return Response.status(Status.CONFLICT).entity(message).build();
	 }
	 
	 //Update a Product
	 @Path("/{productId}")
	 @PUT
	 @Consumes(MediaType.APPLICATION_XML)
	 @Produces(MediaType.APPLICATION_XML)
	 public Response updateProduct(@PathParam("productId") int productId,Product product) {
		 Object message = productService.updateProduct(productId,product);
		 
		 if(message instanceof Product)
			 return Response.ok(message).build();
		 
		 if(message instanceof String && ((String)message).contains("Already"))
		 	return Response.status(Status.CONFLICT).entity(message).build();
		 
		 return  Response.status(Status.NOT_FOUND).entity(message).build();
	 }

}
