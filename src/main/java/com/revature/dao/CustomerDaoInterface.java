package com.revature.dao;

import java.util.List;

import com.revature.models.Customer;

//Remember, Interfaces provide methods with no body (abstract methods) to be implemented in another class
//Great for organization, and making sure certain classes implement certain functionality
//Here, we'll lay out all of the behaviors (methods) that an EmployeeDao should have
public interface CustomerDaoInterface {

	public List<Customer> getCustomer(); //returns a List of all customers (select *)
	
	public List<Customer> getCustomerByName(String name); //this will get customer with a certain name (select where)
	
	public List<Customer> getCustomerById(int id); //return a customer given their id
	
	public void addCustomer(Customer customer); //this will add a customer to the database (insert)
	
	public void removeCustomer(int id); //this will remove customer using their id (delete)

	public void updateCustomer(String name, int address); //this will change a customer's address based on its name

	
}
