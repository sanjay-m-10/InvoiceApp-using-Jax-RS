package com.java.invoice.resource;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.java.invoice.InvoicedItem;
import com.java.invoice.Invoice;
import com.java.invoice.service.InvoicesService;
import com.sun.tools.javac.util.Pair;

@Path("/invoices")
public class InvoicesResource {
	
	InvoicesService invoicesService = new InvoicesService();

	//Get All Invoices Of the Store
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public Response getAllInvoices(@DefaultValue("0") @QueryParam("customer_id")int customerId){
		if(customerId==Integer.parseInt("0")) {
			ArrayList<Invoice> invoice = invoicesService.getAllInvoices();
			
			if(invoice == null)
				return Response.status(Status.NOT_FOUND).entity("<message>No Invoices Found</message>").build();
			
			GenericEntity<ArrayList<Invoice>> resultInvoice = new GenericEntity<ArrayList<Invoice>>(invoice) {};
			
			return Response.ok(resultInvoice).build();
		}
		else {
			Pair<ArrayList<Invoice>,String> invoiceListwithMessage = invoicesService.getInvoices(customerId);
			
			if(invoiceListwithMessage.fst == null)
				return Response.status(Status.NOT_FOUND).entity(invoiceListwithMessage.snd).build();
			
			GenericEntity<ArrayList<Invoice>> resultInvoices = new GenericEntity<ArrayList<Invoice>>(invoiceListwithMessage.fst) {};
			
			return Response.ok(resultInvoices).build();
		}
	}
	
	//Get the Line Items of the Invoice
	@Path("/{invoiceId}")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public Response getAllInvoiceditems(@DefaultValue("0") @QueryParam("customer_id") int customerId, @PathParam("invoiceId")int invoiceId){
		if(customerId==Integer.parseInt("0"))
			return Response.status(Status.BAD_REQUEST).entity("<message>Invalid Request</message>").build();
		else {
			Pair<ArrayList<InvoicedItem>,String> invoiceListwithMessage = invoicesService.getAllInvoiceditems(invoiceId,customerId);
			if(invoiceListwithMessage.fst == null)
				return Response.status(Status.NOT_FOUND).entity(invoiceListwithMessage.snd).build();
			GenericEntity<ArrayList<InvoicedItem>> resultLineItems = new GenericEntity<ArrayList<InvoicedItem>>(invoiceListwithMessage.fst) {};
			return Response.ok(resultLineItems).build();
		}
	}

	//Create the Invoice
	@Path("/{paymentStatus}")
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public Response createInvoice(@DefaultValue("0") @QueryParam("customer_id")int customerId,@PathParam("paymentStatus") boolean paymentStatus,ArrayList<InvoicedItem> lineItems){
		
		if(customerId==Integer.parseInt("0") || lineItems.size()==0)
			return Response.status(Status.BAD_REQUEST).entity("<message>Invalid Request</message>").build();
			
		else {
			
			Pair<ArrayList<InvoicedItem>,String> invoiceWithMessage = invoicesService.addInvoice(customerId,lineItems,paymentStatus);
			
			if(invoiceWithMessage.fst == null)
				return Response.status(Status.NOT_FOUND).entity(invoiceWithMessage.snd).build();
			
			GenericEntity<ArrayList<InvoicedItem>> resultLineItems = new GenericEntity<ArrayList<InvoicedItem>>(invoiceWithMessage.fst) {};
			return Response.status(Status.CREATED).entity(resultLineItems).build();
		}
		
	}
	
	//Delete Invoice
	@Path("/{invoiceId}")
	@DELETE
	public Response deleteInvoice(@DefaultValue("0") @QueryParam("customer_id")int customerId,@PathParam("invoiceId")int invoiceId) {
		Pair<Invoice,String> inv = invoicesService.deleteInvoice(customerId,invoiceId);
		if(inv.fst == null)
			return Response.status(Status.NOT_FOUND).entity(inv.snd).build();
		return Response.ok(inv.fst).build();
	}
}
