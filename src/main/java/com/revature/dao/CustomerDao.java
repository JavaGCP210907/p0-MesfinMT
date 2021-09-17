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

		try(Connection conn = ConnectionUtil.getConnection()) { //getConnection() comes from our ConnectionUtil Class
			
			ResultSet rs = null;
			
			String sql = "select * from customers";
			Statement s = conn.createStatement();
			
			rs = s.executeQuery(sql);
			
			List<Customer> customerList = new ArrayList<>();
			
			while(rs.next()) { //while there are results in the result set...
				
				Customer e = new Customer(
						rs.getInt("customer_id"),
						rs.getString("name"),
						rs.getString("address"),
						rs.getString("city"),
						rs.getString("state"),
						rs.getInt("zipcode")
						);
				
				customerList.add(e); //e is the new Employee object we created above
			}
			
			return customerList;
			
		} catch (SQLException e) {
			System.out.println("Something went wrong with your database!"); //generic console message
			e.printStackTrace(); //stack trace so we actually know what went wrong
		}
		
		return null; //we add this after the try/catch so Java won't yell.
					 //(Since there is no guarantee the try with resources block will run)
	}

	@Override
	public List<Customer> getCustomerByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Customer> getCustomerById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addCustomer(Customer customer) {
		try(Connection conn = ConnectionUtil.getConnection()){
			
			
			//you can line break a sql statement in Java by concatenation (not the +)
			String sql = "insert into customers (name, address, city, state,zipcode)" +
						 "values (?, ?, ?, ?,?)";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, customer.getName());
			ps.setString(2, customer.getAddress());
			ps.setString(3, customer.getCity());
			ps.setString(4, customer.getState());
			ps.setInt(5, customer.getZipcode());
			
			ps.executeUpdate(); //for anything that is NOT a SELECT statement, we use executeUpdate();
			
			//send confirmation to the console if successful
			System.out.println("customer " + customer.getName() + " created. Welcome aboard!");
			
		} catch (SQLException e) {
			System.out.println("add customer failed :(");
			e.printStackTrace();
		}
		
	}
	

	@Override
	public void removeCustomer(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateCustomer(String name, int address) {
		// TODO Auto-generated method stub

	}

}