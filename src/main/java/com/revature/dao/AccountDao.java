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

import com.revature.models.Account;
import com.revature.models.Customer;
import com.revature.models.Menu;
import com.revature.utils.ConnectionUtil;

//Implements AccountDaoInterface abstract methods.
public class AccountDao implements AccountDaoInterface {

	Logger log = LogManager.getLogger(Menu.class); //Logger object so that we can implement Logging

	@Override
	public List<Account> getAccount() {
		try(Connection conn = ConnectionUtil.getConnection()) { 
			
			ResultSet rs = null;
			
			String sql = "select * from accounts";
			Statement s = conn.createStatement();
			
			rs = s.executeQuery(sql);
			
			List<Account> accountList = new ArrayList<>();
			
			while(rs.next()) { 
				
				Account eaccount = new Account(
						rs.getInt("account_id"),
						rs.getDouble("balance"),
						rs.getString("opendate"),
						rs.getInt("accounttype_id"),
						rs.getInt("customer_id")
						);
				
				accountList.add(eaccount);
			}
			
			return accountList;
			
		} catch (SQLException e) {
			System.out.println("Something went wrong with your database!"); 
		}
		
		return null; 
		}

	@Override
	public List<Account> getAccountByName(String name) {


		try(Connection conn = ConnectionUtil.getConnection()) {
			
			ResultSet rs = null;
			
			String sql = "select * from accounts inner join customers on accounts.customer_id=customers.customer_id where customers.name = ?";
			PreparedStatement ps = conn.prepareStatement(sql); 
			ps.setString(1, name); 
			
			rs = ps.executeQuery();
			if (rs == null) {
				System.out.println("An account with the name " + name +" not found.");
			}
			
			List<Account> accountList = new ArrayList<>();
			while(rs.next()) { 
				Account eaccount = new Account(
						rs.getInt("account_id"),
						rs.getDouble("balance"),
						rs.getString("opendate"),
						rs.getInt("accounttype_id"),
						rs.getInt("customer_id")
						);
			
				accountList.add(eaccount); 
			}
			
			return accountList;
			
		} catch (SQLException e) {
			System.out.println("Something went wrong with your database!"); 
		}
		return null;
	

	}

	@Override
	public List<Account> getAccountById(int id) {

		try(Connection conn = ConnectionUtil.getConnection()) {
			
			ResultSet rs = null;
			
			String sql = "select * from accounts where account_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql); 
			ps.setInt(1, id); 
			rs = ps.executeQuery();
			List<Account> accountList = new ArrayList<>();
			while(rs.next()) { 
				Account eaccount = new Account(
					rs.getInt("account_id"),
					rs.getDouble("balance"),
					rs.getString("opendate"),
					rs.getInt("accounttype_id"),
					rs.getInt("customer_id")
					);
			
				accountList.add(eaccount); 
			}
			
			return accountList;
			
		} catch (SQLException e) {
			System.out.println("Something went wrong with your database!"); 
		}
		return null;
	}

	@Override
	public void addAccount(Account account) {
		int counter = 0;
	
		try(Connection conn = ConnectionUtil.getConnection()){

			String sql = "insert into accounts (balance, opendate, accounttype_id, customer_id)" +
						 "values (default, default,?,?)";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, account.getAccounttype_id());
			ps.setInt(2, account.getCustomer_id());
			
			counter=ps.executeUpdate(); 
			
			
		} catch (SQLException e) {
			System.out.println("Add account failed :(");
		}
		if (counter == 0) {
			System.out.println("Creat account with account ID "+ account.getAccounttype_id()+" failed: " );
		}
		else
		{
			System.out.println("A new account created with customer ID: " + account.getCustomer_id());
			log.info("USER ADDED ACCOUNT ID: " + account.getAccounttype_id());
		}
		
	}
		
	@Override
	public void removeAccount(int id) {

		int counter = 0;

		try(Connection conn = ConnectionUtil.getConnection()){
			
			String sql = "delete from accounts where account_id = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, id);
			counter=ps.executeUpdate();
			
			
		} catch (SQLException e) {
			System.out.println("you can't remove Account ID "+id+". You have to remove the associated transactions first.");
		}
		if (counter == 0) {
			System.out.println("Delete Account failed: " + id);
		}
		else 
		{
		System.out.println("Account ID: " + id+" is deleted.");
		log.warn("USER DELETED Account ID: " + id);
		}
		
		
	}

	@Override
	public void updateAccount(int accountid, int accounttypeid) {

		int counter = 0;

		try(Connection conn = ConnectionUtil.getConnection()){
			
			String sql = "update accounts set accounttype_id = ? where account_id = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, accounttypeid);
			ps.setInt(2, accountid);
			
			counter=ps.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("You can't update Account : "+accountid);
		}
		if (counter == 0) {
			System.out.println("Update Account failed: " + accountid);
		}
		else 
		{
			System.out.println("Account ID "+accountid + " changed to: " + accounttypeid);
			log.warn("USER UPDATED ACCOUNT TYPE ID TO : " + accounttypeid+ " FOR ACCOUNT ID: "+accountid);
		}

	}

}
