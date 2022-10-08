package com.java.invoice.resource;


import java.util.ArrayList;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.java.invoice.InvoicedItem;
import com.java.invoice.service.InvoicedItemsService;

@Path("/invoiceditems")
public class InvoicedItemsResource {
	
	InvoicedItemsService invoicedItemsService = new InvoicedItemsService();
	
/*	@POST
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	@Path("/{customerId}")
	public double addInvoice(ArrayList<InvoicedItem> invoicedItem,@PathParam("customerId") int CustomerId) {
		//return invoicedItemsService.add(invoicedItem,CustomerId);
	}*/
	
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public ArrayList<InvoicedItem> getAllInvoices(){
		return invoicedItemsService.getAllInvoices();	
	}
	
	@Path("/{invoiceId}")
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public ArrayList<InvoicedItem> getInvoicedItems(@PathParam("invoiceId") int id) {
    	return invoicedItemsService.getInvoicedItems(id);
    }

}
