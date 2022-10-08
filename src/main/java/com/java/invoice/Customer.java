package com.java.invoice;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Customer {
	
	
	private int cusId;
	private static int id = 0;
	private String name;
	private long phn_no;
	
	public Customer() {
		
	}
	
	public Customer(String name,long phn_no){
		this.setCusId(++id);
		this.name = name;
		this.phn_no = phn_no;
		
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public long getPhn_no() {
		return phn_no;
	}
	
	public void setPhn_no(long phn_no) {
		this.phn_no = phn_no;
	}
	

	public int getCusId() {
		return cusId;
	}

	public void setCusId(int cusId) {
		this.cusId = cusId;
	}

	public static int getId() {
		++Customer.id;
		return id;
	}
	
	
}
