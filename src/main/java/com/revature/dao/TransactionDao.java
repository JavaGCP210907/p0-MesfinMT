package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Account;
import com.revature.models.Transaction;
import com.revature.utils.ConnectionUtil;

public class TransactionDao implements TransactionDaoInterface {

	@Override
	public List<Transaction> getTransaction() {

		try(Connection conn = ConnectionUtil.getConnection()) { 
			
			ResultSet rs = null;
			
			String sql = "select * from transactions";
			Statement s = conn.createStatement();
			
			rs = s.executeQuery(sql);
			
			List<Transaction> transactionList = new ArrayList<>();
			
			while(rs.next()) { 
				
				Transaction e = new Transaction(
						rs.getInt("transaction_id"),
						rs.getString("postdate"),
						rs.getDouble("amount"),
						rs.getString("description"),
						rs.getInt("account_id")
						);
				
				transactionList.add(e);
			}
			
			return transactionList;
			
		} catch (SQLException e) {
			System.out.println("Something went wrong with your database!"); 
			e.printStackTrace(); 
		}
		
		return null; 
		}

	@Override
	public List<Transaction> getTransactionByAccountId(int id) {

		try(Connection conn = ConnectionUtil.getConnection()) {
			
			ResultSet rs = null;
			
			String sql = "select * from transactions where account_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql); 
			ps.setInt(1, id); 
			rs = ps.executeQuery();
			List<Transaction> transactionList = new ArrayList<>();
			while(rs.next()) { 
				Transaction c = new Transaction(
					rs.getInt("transaction_id"),
					rs.getString("postdate"),
					rs.getDouble("amount"),
					rs.getString("description"),
					rs.getInt("account_id")
					);
			
				transactionList.add(c); 
			}
			
			return transactionList;
			
		} catch (SQLException e) {
			System.out.println("Something went wrong with your database!"); 
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Transaction> getTransactionByTransactionId(int id) {

		try(Connection conn = ConnectionUtil.getConnection()) {
			
			ResultSet rs = null;
			
			String sql = "select * from transactions where transaction_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql); 
			ps.setInt(1, id); 
			rs = ps.executeQuery();
			List<Transaction> transactionList = new ArrayList<>();
			while(rs.next()) { 
				Transaction c = new Transaction(
					rs.getInt("transaction_id"),
					rs.getString("postdate"),
					rs.getDouble("amount"),
					rs.getString("description"),
					rs.getInt("account_id")
					);
			
				transactionList.add(c); 
			}
			
			return transactionList;
			
		} catch (SQLException e) {
			System.out.println("Something went wrong with your database!"); 
			e.printStackTrace();
		}
		return null;
	}


	@Override
	public void addTransaction(Transaction transaction) {

		try(Connection conn = ConnectionUtil.getConnection()){

			String sql = "insert into transactions (postdate, amount, description, account_id)" +
						 "values (default, ?,?,?)";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setDouble(1, transaction.getAmount());
			ps.setString(2, transaction.getDescription());
			ps.setInt(3, transaction.getAccount_id());
			
			ps.executeUpdate(); 
			
			System.out.println("A new Transaction created for Account ID: " + transaction.getAccount_id());
			
		} catch (SQLException e) {
			System.out.println("Add transaction failed :(");
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void removeTransaction(int id) {

		try(Connection conn = ConnectionUtil.getConnection()){
			
			String sql = "delete from transactions where transaction_id = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, id);
			ps.executeUpdate();
			
			System.out.println("Transaction ID: " + id+" is deleted.");
			
		} catch (SQLException e) {
			System.out.println("you can't remove Transaction ID "+id);
			e.printStackTrace();
		}
		
	}

		
	@Override
	public void updateTransaction(int id,String desc) {

		try(Connection conn = ConnectionUtil.getConnection()){
			
			String sql = "update transactions set description = ? where transaction_id = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, desc);
			ps.setInt(2, id);
			
			ps.executeUpdate();
			
			System.out.println("Transaction ID "+id + " Reference is successfully updated to: " + desc);
			
		} catch (SQLException e) {
			System.out.println("You can't update customer : "+id);
			e.printStackTrace();
		}
	}

}
