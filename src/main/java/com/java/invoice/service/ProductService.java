package com.java.invoice.service;


import java.util.ArrayList;
import com.java.invoice.Product;


public class ProductService {
	
	static ArrayList<Product> listOfProducts = new ArrayList<Product>();
	
	
	public String createProduct(Product product) {
		
			if(product.getProductPrice() <= 0 || product.getProductStock() <= 0)
				return "<message>Invalid Stocks or Price</message>";
			
			for(Product prod : listOfProducts) {
				if(prod.getProductName().equalsIgnoreCase(product.getProductName()))
					return "<message>Product Already in Store</message>";
			}
			
			product.setPid(Product.getId());
			listOfProducts.add(product);
			
		return "Done";
		 
	}
		
	public  ArrayList<Product> getAllProducts() {
		return listOfProducts;
	}
	
	public Product getProduct(int id) {
		for(Product product : listOfProducts) {
			if(product.getPid()==id)
				return product;
		}
		return null;
	}
	
	public Product deleteProduct(int id) {
		Product product = getProduct(id);
		if(product!=null)
			listOfProducts.remove(product);
		return product;
	}	
	

	public Object updateProduct(int productId,Product prod) {
		Product product = getProduct(productId);
		
		if(product!=null) {
			for(Product p : listOfProducts)
				if(p.getProductName().equalsIgnoreCase(prod.getProductName()) && p.getPid()!=productId)
					return "<message>Products Already in Store</message>";
			product.setPid(productId);
			product.setProductName(prod.getProductName());
			product.setProductPrice(prod.getProductPrice());
			product.setProductStock(prod.getProductStock());
			product.setProductType(prod.getProductType());
			return product;
		}
		
		return "<message>No Products Found</message>";
	}
	
	public ArrayList<Product> searchProducts(String pattern){
		ArrayList<Product> products = new ArrayList<Product>();
		for(Product product : listOfProducts) {
			if(product.getProductName().toLowerCase().contains(pattern)){
				products.add(product);
			}	
		}
		return products;
	} 	
}