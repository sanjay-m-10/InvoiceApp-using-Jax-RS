package com.java.invoice.service;


import java.util.ArrayList;


import com.java.invoice.Customer;


public class CustomerService {
	
	static ArrayList<Customer> listOfCustomers = new ArrayList<Customer>();
	
	
	public String createCustomer(Customer newCustomer) {
		
		if(((String.valueOf(newCustomer.getPhn_no()).length()!=10))) {
			newCustomer = null;
			return "<message>Invalid Phone No</message>";
		}
		
		for(Customer customer : listOfCustomers) {
			if(customer.getPhn_no()==newCustomer.getPhn_no()) {
				newCustomer = null;
				return "<message>Customer Already Present</message>";
			}
		}
		newCustomer.setCusId(Customer.getId());
		listOfCustomers.add(newCustomer);
		return "Added Successfully";
		 
	}
		
	public  ArrayList<Customer> getAllCustomers() {
		
		return listOfCustomers;
	}
	
	
	static public Customer getCustomer(int id) {
		
		for(Customer customer : listOfCustomers) {
			if(customer.getCusId()==id)
				return customer;
		}
		return null;
	}
	
	public Customer deleteCustomer(int customerId) {
		
		for(Customer customer : listOfCustomers) {
			if(customer.getCusId() == customerId) {
				listOfCustomers.remove(customer);
				return customer;
			}	
		}
		return null;
	}
	
	public Object updateCustomer(int id,Customer updatedCustomer) {
		
		for(Customer customer : listOfCustomers) {
			if(customer.getPhn_no() == updatedCustomer.getPhn_no()) {
				updatedCustomer = null;
				return "<message>Phone Number Already Present</message>";
			}
		}
		
		for(Customer customer : listOfCustomers) {
			if(customer.getCusId()==id) {
				customer.setName(updatedCustomer.getName());
				customer.setPhn_no(updatedCustomer.getPhn_no());
				return customer;
			}	
		}
		
		updatedCustomer = null;
		return "<message>No Customers Found</message>";
	}
	
	public Customer findCustomerByPhno(long phn_no) {
		
		for(Customer customer : listOfCustomers) {
			if(customer.getPhn_no() == phn_no) {
				return customer;
			}	
		}
		return null;

	}
	
}