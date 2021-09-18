package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.models.Customer;
import com.revature.models.Menu;
import com.revature.utils.ConnectionUtil;

//Implements CustomerDaoInterface abstract methods.
public class CustomerDao implements CustomerDaoInterface {

	Logger log = LogManager.getLogger(Menu.class); //Logger object so that we can implement Logging

	@Override
	public List<Customer> getCustomer() {

		try(Connection conn = ConnectionUtil.getConnection()) {
			
			ResultSet rs = null;
			
			String sql = "select * from customers";
			Statement s = conn.createStatement();
			
			rs = s.executeQuery(sql);
			
			List<Customer> customerList = new ArrayList<>();
			
			while(rs.next()) { 
				
				Customer customer = new Customer(
						rs.getInt("customer_id"),
						rs.getString("name"),
						rs.getString("address"),
						rs.getString("city"),
						rs.getString("state"),
						rs.getInt("zipcode")
						);
				
				customerList.add(customer);
			}
			
			return customerList;
			
		} catch (SQLException e) {
			System.out.println("Something went wrong with your database!"); 
			e.printStackTrace(); 
		}
		
		return null; 
		}

	@Override
	public List<Customer> getCustomerByName(String name) {

		try(Connection conn = ConnectionUtil.getConnection()) {
			
			ResultSet rs = null;
			
			String sql = "select * from customers where name = ?";
			PreparedStatement ps = conn.prepareStatement(sql); 
			ps.setString(1, name); 
			
			rs = ps.executeQuery();
			List<Customer> customerList = new ArrayList<>();
			while(rs.next()) { 
				Customer customer = new Customer(
					rs.getInt("customer_id"),
					rs.getString("name"),
					rs.getString("address"),
					rs.getString("city"),
					rs.getString("state"),
					rs.getInt("zipcode")
					);
			
				customerList.add(customer); 
			}
			
			return customerList;
			
		} catch (SQLException e) {
			System.out.println("Something went wrong with your database!"); 
			e.printStackTrace();
		}
		return null;
	}
	

	@Override
	public List<Customer> getCustomerById(int id) {

		try(Connection conn = ConnectionUtil.getConnection()) {
			
			ResultSet rs = null;
			
			String sql = "select * from customers where customer_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql); 
			ps.setInt(1, id); 
			rs = ps.executeQuery();
			List<Customer> customerList = new ArrayList<>();
			while(rs.next()) { 
				Customer customer = new Customer(
					rs.getInt("customer_id"),
					rs.getString("name"),
					rs.getString("address"),
					rs.getString("city"),
					rs.getString("state"),
					rs.getInt("zipcode")
					);
			
				customerList.add(customer); 
			}
			
			return customerList;
			
		} catch (SQLException e) {
			System.out.println("Something went wrong with your database!"); 
		}
		return null;
	}
	
	@Override
	public void addCustomer(Customer customer) {

		int counter = 0;

		try(Connection conn = ConnectionUtil.getConnection()){

			String sql = "insert into customers (name, address, city, state,zipcode)" +
						 "values (?, ?, ?, ?,?)";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, customer.getName());
			ps.setString(2, customer.getAddress());
			ps.setString(3, customer.getCity());
			ps.setString(4, customer.getState());
			ps.setInt(5, customer.getZipcode());
			
			counter=ps.executeUpdate(); 
			
		} catch (SQLException e) {
			System.out.println("Add customer failed :(");
			e.printStackTrace();
		}
		
		if (counter == 0) {
			System.out.println("Add Customer "+ customer.getName()+" failed: " );
		}
		else
		{
			System.out.println("Customer "+customer.getName() + " successfully added.");
			log.warn("USER ADDED CUSTOMER WITH NAME: " + customer.getName());
		}
	}
	

	@Override
	public void removeCustomer(int id)  {

		int counter = 0;

		try(Connection conn = ConnectionUtil.getConnection()){
			
			String sql = "delete from customers where customer_id = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, id);
			
			counter=ps.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("you can't remove Customer ID "+id);
			e.printStackTrace();
		}
		
		if (counter == 0) {
			System.out.println("Delete Customer failed: " + id);
		}
		else 
		{
		System.out.println("Customer ID "+id + " successfully deleted");
		log.warn("USER DELETED CUSTOMER ID: " + id);
		}
}
	
	
	
	@Override
	public void updateCustomer( int id,String address) {

		int counter = 0;

		try(Connection conn = ConnectionUtil.getConnection()){
			
			String sql = "update customers set address = ? where customer_id = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, address);
			ps.setInt(2, id);
			
			counter=ps.executeUpdate();
			
			
		} catch (SQLException e) {
			System.out.println("You can't update customer : "+id);
			e.printStackTrace();
		}
		if (counter == 0) {
			System.out.println("Update Customer failed: " + id);
		}
		else 
		{
			System.out.println("Customer ID "+id + " Address is changed to: " + address);
			log.warn("USER UPDATED CUSTOMER ADDRESS TO : " + address+ " FOR CUSTOMER ID: "+id);
		}
	}
}