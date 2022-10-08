package com.java.invoice.service;

import java.util.ArrayList;
import com.java.invoice.InvoicedItem;


public class InvoicedItemsService {
	
	static ArrayList<InvoicedItem> listOfInvoicedItems = new ArrayList<InvoicedItem>(); 
	
	
	public static void delete(int invoiceId) {
		
		for(InvoicedItem items : listOfInvoicedItems) {
			if(items.getInvoiceId() == invoiceId)
				listOfInvoicedItems.remove(items);
		}
	}
	
	
	public double add(ArrayList<InvoicedItem> invoicedItem,int CustomerId,int invoiceId) {
		double total = 0;
		for(InvoicedItem item : invoicedItem) {
			item.setInvoiceId(invoiceId);
			listOfInvoicedItems.add(item);
			//ProductService productService = new ProductService();
			//productService.reduceStock();
			total += item.getProductAmount();
		}
		return total;
	} 
	
	public ArrayList<InvoicedItem> getAllInvoices(){
		return listOfInvoicedItems;
	}
	
	public ArrayList<InvoicedItem> getInvoicedItems(int invoiceId){
		ArrayList<InvoicedItem> result = new ArrayList<InvoicedItem>();
		for(InvoicedItem item : listOfInvoicedItems) {
			if(item.getInvoiceId() == invoiceId) {
				result.add(item);
			}
		}
			return result;
	}
}
