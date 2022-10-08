package com.java.invoice;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class InvoicedItem{
	
	private int invoiceId;
	private Product productObj;
	private int quantityBought;
	private double productAmount;
	
	public InvoicedItem() {
		
	}
	
	public InvoicedItem(Product product, int quantityBought, double productAmount) {
		this.setInvoiceId(Invoice.getId());
		this.productObj = product;
		this.quantityBought = quantityBought;
		this.productAmount = productAmount;
	}

	public int getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(int invoiceId) {
		this.invoiceId = invoiceId;
	}


	public void setProduct(Product product) {
		this.setProductObj(product);
	}

	public int getQuantityBought() {
		return quantityBought;
	}

	public void setQuantityBought(int quantityBought) {
		this.quantityBought = quantityBought;
	}

	public double getProductAmount() {
		return productAmount;
	}

	public void setProductAmount(double productAmount) {
		this.productAmount = productAmount;
	}

	public Product getProductObj() {
		return productObj;
	}

	public void setProductObj(Product productObj) {
		this.productObj = productObj;
	}
	
	
	
}
