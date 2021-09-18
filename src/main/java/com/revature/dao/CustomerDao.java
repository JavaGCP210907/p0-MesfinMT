package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Customer;
import com.revature.utils.ConnectionUtil;

public class CustomerDao implements CustomerDaoInterface {

	@Override
	public List<Customer> getCustomer() {

		try(Connection conn = ConnectionUtil.getConnection()) {
			
			ResultSet rs = null;
			
			String sql = "select * from customers";
			Statement s = conn.createStatement();
			
			rs = s.executeQuery(sql);
			
			List<Customer> customerList = new ArrayList<>();
			
			while(rs.next()) { 
				
				Customer e = new Customer(
						rs.getInt("customer_id"),
						rs.getString("name"),
						rs.getString("address"),
						rs.getString("city"),
						rs.getString("state"),
						rs.getInt("zipcode")
						);
				
				customerList.add(e);
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
				Customer c = new Customer(
					rs.getInt("customer_id"),
					rs.getString("name"),
					rs.getString("address"),
					rs.getString("city"),
					rs.getString("state"),
					rs.getInt("zipcode")
					);
			
				customerList.add(c); 
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
				Customer c = new Customer(
					rs.getInt("customer_id"),
					rs.getString("name"),
					rs.getString("address"),
					rs.getString("city"),
					rs.getString("state"),
					rs.getInt("zipcode")
					);
			
				customerList.add(c); 
			}
			
			return customerList;
			
		} catch (SQLException e) {
			System.out.println("Something went wrong with your database!"); 
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public void addCustomer(Customer customer) {

		try(Connection conn = ConnectionUtil.getConnection()){

			String sql = "insert into customers (name, address, city, state,zipcode)" +
						 "values (?, ?, ?, ?,?)";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, customer.getName());
			ps.setString(2, customer.getAddress());
			ps.setString(3, customer.getCity());
			ps.setString(4, customer.getState());
			ps.setInt(5, customer.getZipcode());
			
			ps.executeUpdate(); 
			
			System.out.println("customer " + customer.getName() + " created.");
			
		} catch (SQLException e) {
			System.out.println("add customer failed :(");
			e.printStackTrace();
		}
		
	}
	

	@Override
	public void removeCustomer(int id) {
		try(Connection conn = ConnectionUtil.getConnection()){
			
			String sql = "delete from customers where customer_id = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, id);
			ps.executeUpdate();
			
			System.out.println("Customer ID: " + id+" is deleted.");
			
		} catch (SQLException e) {
			System.out.println("you can't remove Customer ID "+id);
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void updateCustomer( int id,String address) {

		try(Connection conn = ConnectionUtil.getConnection()){
			
			String sql = "update customers set address = ? where customer_id = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, address);
			ps.setInt(2, id);
			
			ps.executeUpdate();
			
			System.out.println("Customer ID "+id + " Address is successfully updated to: " + address);
			
		} catch (SQLException e) {
			System.out.println("You can't update customer : "+id);
			e.printStackTrace();
		}
	}
}