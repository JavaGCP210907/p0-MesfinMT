package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Account;
import com.revature.models.Customer;
import com.revature.utils.ConnectionUtil;

public class AccountDao implements AccountDaoInterface {

	@Override
	public List<Account> getAccount() {
		try(Connection conn = ConnectionUtil.getConnection()) { 
			
			ResultSet rs = null;
			
			String sql = "select * from accounts";
			Statement s = conn.createStatement();
			
			rs = s.executeQuery(sql);
			
			List<Account> accountList = new ArrayList<>();
			
			while(rs.next()) { 
				
				Account e = new Account(
						rs.getInt("account_id"),
						rs.getDouble("balance"),
						rs.getString("opendate"),
						rs.getInt("accounttype_id"),
						rs.getInt("customer_id")
						);
				
				accountList.add(e);
			}
			
			return accountList;
			
		} catch (SQLException e) {
			System.out.println("Something went wrong with your database!"); 
			e.printStackTrace(); 
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
			List<Account> accountList = new ArrayList<>();
			while(rs.next()) { 
				Account c = new Account(
						rs.getInt("account_id"),
						rs.getDouble("balance"),
						rs.getString("opendate"),
						rs.getInt("accounttype_id"),
						rs.getInt("customer_id")
						);
			
				accountList.add(c); 
			}
			
			return accountList;
			
		} catch (SQLException e) {
			System.out.println("Something went wrong with your database!"); 
			e.printStackTrace();
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
				Account c = new Account(
					rs.getInt("account_id"),
					rs.getDouble("balance"),
					rs.getString("opendate"),
					rs.getInt("accounttype_id"),
					rs.getInt("customer_id")
					);
			
				accountList.add(c); 
			}
			
			return accountList;
			
		} catch (SQLException e) {
			System.out.println("Something went wrong with your database!"); 
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void addAccount(Account account) {
	
		try(Connection conn = ConnectionUtil.getConnection()){

			String sql = "insert into accounts (balance, opendate, accounttype_id, customer_id)" +
						 "values (default, default,?,?)";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, account.getAccounttype_id());
			ps.setInt(2, account.getCustomer_id());
			
			ps.executeUpdate(); 
			
			System.out.println("A new account created with customer ID: " + account.getCustomer_id());
			
		} catch (SQLException e) {
			System.out.println("add customer failed :(");
			e.printStackTrace();
		}
		
	}
		
	@Override
	public void removeAccount(int id) {

		try(Connection conn = ConnectionUtil.getConnection()){
			
			String sql = "delete from accounts where account_id = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, id);
			ps.executeUpdate();
			
			System.out.println("Account ID: " + id+" is deleted.");
			
		} catch (SQLException e) {
			System.out.println("you can't remove Account ID "+id);
			e.printStackTrace();
		}
		
	}

	@Override
	public void updateAccount(int accountid, int accounttypeid) {

		try(Connection conn = ConnectionUtil.getConnection()){
			
			String sql = "update accounts set accounttype_id = ? where account_id = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, accounttypeid);
			ps.setInt(2, accountid);
			
			ps.executeUpdate();
			
			System.out.println("Account ID "+accountid + " Account Type is successfully updated to: " + accounttypeid);
			
		} catch (SQLException e) {
			System.out.println("You can't update customer : "+accountid);
			e.printStackTrace();
		}
	}

}
