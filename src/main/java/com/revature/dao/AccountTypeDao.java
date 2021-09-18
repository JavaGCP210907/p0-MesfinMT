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

import com.revature.models.AccountType;
import com.revature.models.Menu;
import com.revature.utils.ConnectionUtil;

//Implements AccountTypeDaoInterface abstract methods.
public class AccountTypeDao implements AccountTypeDaoInterface {

	Logger log = LogManager.getLogger(Menu.class); //Logger object so that we can implement Logging
	
	@Override
	public List<AccountType> getAccountTypes() {
		try(Connection conn = ConnectionUtil.getConnection()) { 
			
			ResultSet rs = null;
			
			String sql = "select * from accounttypes";
			Statement s = conn.createStatement();
			
			rs = s.executeQuery(sql);
			
			List<AccountType> accounttypeList = new ArrayList<>();
			
			while(rs.next()) { 
				
				AccountType accounttype = new AccountType(
						rs.getInt("accounttype_id"),
						rs.getString("type"),
						rs.getDouble("rate")
						);
				
				accounttypeList.add(accounttype); 
			}
			
			return accounttypeList;
			
		} catch (SQLException e) {
			System.out.println("Something went wrong with your database!"); 
			e.printStackTrace();
		}
		
		return null; 
	}

	@Override
	public void addAccountType(AccountType accounttype) {

		int counter = 0;

		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "insert into accounttypes (type, rate)" +
						 "values (?, ?)";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, accounttype.getType());
			ps.setDouble(2, accounttype.getRate());
			
			counter=ps.executeUpdate();
			
			
		} catch (SQLException e) {
			System.out.println("Add AccountType failed :(");
			e.printStackTrace();
		}
		if (counter == 0) {
			System.out.println("Add AccountType "+ accounttype.getType()+" failed: " );
		}
		else
		{
			System.out.println("AccountType " + accounttype.getType() + " is created.");
			log.info("USER ADDED NEW ACCOUNT TYPE: "+accounttype.getType());
		}
		
	}
	@Override
	public void removeAccountType(int id) {
		int counter = 0;
		try(Connection conn = ConnectionUtil.getConnection()){
			
			String sql = "delete from accounttypes where accounttype_id = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, id);
			counter=ps.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("you can't remove Account type "+id);
			e.printStackTrace();
		}
		if (counter == 0) {
			System.out.println("Delete Account type failed: " + id);
		}
		else 
		{
		System.out.println("Account type ID "+id + " is successfully deleted");
		log.warn("USER DELETED ACCOUNT TYPE " + id);
		}
		
	}
	
	@Override
	public void updateInterestRate(int id,double rate) {
		int counter = 0;
	try(Connection conn = ConnectionUtil.getConnection()){
			
			String sql = "update accounttypes set rate = ? where accounttype_id = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setDouble(1, rate);
			ps.setInt(2, id);
			
			counter=ps.executeUpdate();
			
			
		} catch (SQLException e) {
			System.out.println("You can't update Account Type : "+id);
			e.printStackTrace();
		}
	if (counter == 0) {
		System.out.println("Update Account Type failed: " + id);
	}
	else 
	{
		System.out.println("Account Type "+id + " Interest rate is changed to: " + rate);
		log.info("USER UPDATED THE INTEREST RATE OF ACCOUNT TYPE: "+id+" TO "+rate);
	}

	}

}
