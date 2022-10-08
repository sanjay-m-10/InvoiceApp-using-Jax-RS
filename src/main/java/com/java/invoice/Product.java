package com.java.invoice;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Product {
	
	private int pid;
	private static int id = 0;
	private int productStock;
	private String productName;
	private double productPrice;
	private String productType;
	
	
	public Product() {	
		
	}
	
	public Product(String productName,String productType, double productPrice,int productStock){
	
		this.productStock = productStock;
		this.productName = productName;
		this.productType = productType;
		this.productPrice = productPrice;
		
		
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public int getProductStock() {
		return productStock;
	}

	public void setProductStock(int productStock) {
		this.productStock = productStock;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public static int getId() {
		++(Product.id);
		return id;
	}
	
	
	
}
