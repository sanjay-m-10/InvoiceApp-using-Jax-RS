package com.java.invoice.service;

import java.util.ArrayList;
import java.util.Date;
import com.java.invoice.InvoicedItem;
import com.java.invoice.Product;
import com.java.invoice.Customer;
import com.java.invoice.Invoice;
import com.sun.tools.javac.util.Pair;

public class InvoicesService {
	
   static ArrayList<Invoice> listOfInvoices = new ArrayList<Invoice>();
    
	
	public Pair<ArrayList<InvoicedItem>,String> addInvoice(int customerId,ArrayList<InvoicedItem> items,boolean paymentStatus) {
		Customer customer = CustomerService.getCustomer(customerId);
		if(customer==null)
			return new Pair<ArrayList<InvoicedItem>,String>(null,"<message>No Customers Found</message>");
		InvoicedItemsService service = new InvoicedItemsService();
		for(InvoicedItem item : items) {
			boolean flag = true;
			for(Product prod : ProductService.listOfProducts) {	
				if(prod.getPid() == item.getProductObj().getPid() && prod.getProductName().equalsIgnoreCase(item.getProductObj().getProductName())) {
					flag = false;
					break;
				}
			}
			if(flag) 
				return new Pair<ArrayList<InvoicedItem>,String>(null,"<message>No Products Found</message>");
		}
		Invoice invoice = new Invoice(Invoice.getId(),0,new Date().toString(),customerId,paymentStatus);
		double total = service.add(items,customerId,Invoice.getId());
		invoice.setInvoiceTotal(total);
		listOfInvoices.add(invoice);
		Invoice.incrementId();
		return new Pair<ArrayList<InvoicedItem>,String>(items,"");
		
	}
	
	public ArrayList<Invoice> getAllInvoices() {
		if(listOfInvoices.size()==0)
			return null;
		return listOfInvoices;
	}
	
	public Invoice getInvoice(int invoiceId) {
		 for(Invoice invoice : listOfInvoices)
			 if(invoice.getInvoiceid()==invoiceId)
				return invoice;
		return null;
	}
	
	public Pair<ArrayList<InvoicedItem>,String> getAllInvoiceditems(int invoiceId,int customerId){
		ArrayList<Invoice> cusInv  = getInvoices(customerId).fst;
		if(cusInv == null)
			return new Pair<ArrayList<InvoicedItem>,String>(null,"<message>No Invoices Found for the Customer</message>");
		for(Invoice inv : cusInv) {
			if(invoiceId==inv.getInvoiceid()) {
				ArrayList<InvoicedItem> result = new ArrayList<InvoicedItem>();
				for(InvoicedItem item : InvoicedItemsService.listOfInvoicedItems) {
					if(invoiceId==item.getInvoiceId())
						result.add(item);
				}
				return new Pair<ArrayList<InvoicedItem>,String>(result,"");
			}
		}
		return new Pair<ArrayList<InvoicedItem>,String>(null,"<message>No Invoices Found</message>");
	}
	
	public Pair<ArrayList<Invoice>,String> getInvoices(int customerId){
		Customer cust = CustomerService.getCustomer(customerId);
		if(cust==null)
			return new Pair<ArrayList<Invoice>,String>(null,"<message>No Customers Found</message>");
		ArrayList<Invoice> result = new ArrayList<Invoice>();
		for(Invoice item : listOfInvoices) {
			if(customerId==item.getCustomerId())
				result.add(item);
		}
		if(result.size()==0)
			return new Pair<ArrayList<Invoice>,String>(null,"<message>No Invoices Found for the Customer</message>");
		return new Pair<ArrayList<Invoice>,String>(result,"");	
}
	
	public Pair<Invoice,String> deleteInvoice(int customerId,int invoiceId) {
		
		Invoice inv = getInvoice(invoiceId);
		if(inv == null)
			return new Pair<Invoice,String>(null,"<message>No Invoices Found</message>");
		if(inv.isPaymentStatus()==false) {
			for(InvoicedItem items : InvoicedItemsService.listOfInvoicedItems) {
				if(items.getInvoiceId() == invoiceId)
					for(Product product : ProductService.listOfProducts) {
						if(product.getPid() == items.getProductObj().getPid())
							product.setProductStock(product.getProductStock()+items.getQuantityBought());
					}
			}
			
			listOfInvoices.remove(inv);
			
			return new Pair<Invoice,String>(inv,"<message>Deleted</message>");
		}
		return new Pair<Invoice,String>(null,"<message>Invoice cannot be Deleted</message>");
	}

}
