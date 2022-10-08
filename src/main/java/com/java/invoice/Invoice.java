package com.java.invoice;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Invoice {
	
	static private int id = 1;
	private int invoiceId;
	private double invoiceTotal;
	private String invoicedDate;
	@XmlElement(required=true) 
	private int customerId;
	boolean paymentStatus;
	
	public Invoice(){
		
	}
	
	public Invoice(int invoiceId, double invoiceTotal, String invoicedDate, int customerId,boolean paymentStatus) {
		this.invoiceId = invoiceId;
		this.invoiceTotal = invoiceTotal;
		this.invoicedDate = invoicedDate;
		this.customerId = customerId;
		this.paymentStatus = paymentStatus;
	}

	public boolean isPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(boolean paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public int getInvoiceid() {
		return invoiceId;
	}

	public void setInvoiceid(int invoiceid) {
		this.invoiceId = invoiceid;
	}

	public double getInvoiceTotal() {
		return invoiceTotal;
	}

	public void setInvoiceTotal(double invoiceTotal) {
		this.invoiceTotal = invoiceTotal;
	}

	public String getInvoicedDate() {
		return invoicedDate;
	}

	public void setInvoicedDate(String invoicedDate) {
		this.invoicedDate = invoicedDate;
	}

	public int getCustomerId() {
		return customerId;
	}
	
	public static int getId() {
		return id;
	}
	
	public static void incrementId() {
		Invoice.id ++;
	}
	
	
	

}
