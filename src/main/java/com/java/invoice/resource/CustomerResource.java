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
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.java.invoice.Customer;
import com.java.invoice.service.CustomerService;
	
@Path("/customers")
public class CustomerResource {
		
		CustomerService customerService = new CustomerService();
		
		
		//Get Customer using Phone No
	    @GET
	    @Produces(MediaType.APPLICATION_JSON)
	    public Response getCustomers(@DefaultValue("0") @QueryParam("customer_phn") long phn_no) {
	    	CacheControl cc = new CacheControl();
	        cc.setMaxAge(86400);
	        cc.setPrivate(true);
	    	if(phn_no == Long.parseLong("0")) {
	    		GenericEntity<ArrayList<Customer>> listOfCustomers = new GenericEntity<ArrayList<Customer>>(customerService.getAllCustomers()) {};
	    		return Response.ok(listOfCustomers).cacheControl(cc).build();
	    	}
	    	else {
	    		Customer cust = customerService.findCustomerByPhno(phn_no);
	    		if(cust == null)
	    			return Response.status(404).entity("<message>No Customers Found</message>").build();
	    		return Response.ok(cust).cacheControl(cc).build();
	    	}
	    }
	    
	  //Create a Customer
	    @POST
	    @Consumes(MediaType.APPLICATION_XML)
	    @Produces(MediaType.APPLICATION_XML)
	    public Response createCustomer(Customer customer){
	    	String message = customerService.createCustomer(customer);
	    	if(message.equalsIgnoreCase("Added Successfully"))
	    		return Response.status(Status.CREATED).entity(customer).build();
	    	if(message.contains("Invalid"))
	    		return Response.status(Status.BAD_REQUEST).entity(message).build();
	    	return Response.status(Status.CONFLICT).entity(message).build();
	    }
	   
	    
	    //Get a specific Customer using Id
	    @Path("/{customerId}")
	    @GET
	    @Produces(MediaType.APPLICATION_XML)
	    public Response getCustomer(@PathParam("customerId") int customerId) {
	    	Customer customer =  CustomerService.getCustomer(customerId);
	    	if(customer == null)
	    		return Response.status(404).entity("<message>No Customers Found</message>").build();
			return Response.ok(customer).build();
	    }
	    
	    //Delete a Customer
	    @Path("/{customerId}")
	    @DELETE
	    @Produces(MediaType.APPLICATION_XML)
	    public Response deleteCustomer(@PathParam("customerId")int customerId) {
	    	Customer customer = customerService.deleteCustomer(customerId);
	    	if(customer == null)
	    		return Response.status(404).entity("<message>No Customers Found</message>").build();
			return Response.ok(customer).build();
	    }
	    
	    //Update a Customer
	    @Path("/{customerId}")
	    @PUT
	    @Consumes(MediaType.APPLICATION_XML)
	    @Produces(MediaType.APPLICATION_XML)
	    public Response updateCustomer(@PathParam("customerId") int customerId,Customer customer) {
	    	Object message = customerService.updateCustomer(customerId,customer);
	    	if(message instanceof String && ((String) message).equalsIgnoreCase("Done"))
	    		return Response.ok(customer).build();
	    	if(message instanceof String && ((String) message).contains("Phone"))
	    		return Response.status(Status.CONFLICT).entity(message).build();
	    	if(message instanceof String && ((String) message).contains("No"))
	    		return Response.status(Status.NOT_FOUND).entity(message).build();
	    	return Response.ok(message).build();
	    }
}
