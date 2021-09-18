package com.revature.dao;

import java.util.List;

import com.revature.models.Customer;

//Interfaces used to implement customer related operations.
public interface CustomerDaoInterface {

	public List<Customer> getCustomer(); //returns a List of all customers (select *)
	
	public List<Customer> getCustomerByName(String name); //this will get customer with a certain name (select where)
	
	public List<Customer> getCustomerById(int id); //return a customer given their id
	
	public void addCustomer(Customer customer); //this will add a customer to the database (insert)
	
	public void removeCustomer(int id) throws Exception; //this will remove customer using their id (delete)

	public void updateCustomer(int id,String address); //this will change a customer's address based on its id

	
}
