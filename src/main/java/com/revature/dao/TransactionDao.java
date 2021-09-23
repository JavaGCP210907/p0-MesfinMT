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

import com.revature.models.Menu;
import com.revature.models.Transaction;
import com.revature.utils.ConnectionUtil;

//Implements TransactionDaoInterface abstract methods.
public class TransactionDao implements TransactionDaoInterface {

	Logger log = LogManager.getLogger(Menu.class); //Logger object so that we can implement Logging

	@Override
	public List<Transaction> getTransaction() {

		try(Connection conn = ConnectionUtil.getConnection()) { 
			
			ResultSet rs = null;
			
			String sql = "select * from transactions";
			Statement s = conn.createStatement();
			
			rs = s.executeQuery(sql);
			
			List<Transaction> transactionList = new ArrayList<>();
			
			while(rs.next()) { 
				
				Transaction transaction = new Transaction(
						rs.getInt("transaction_id"),
						rs.getString("postdate"),
						rs.getDouble("amount"),
						rs.getString("description"),
						rs.getInt("account_id")
						);
				
				transactionList.add(transaction);
			}
			
			return transactionList;
			
		} catch (SQLException e) {
			System.out.println("Something went wrong with your database!"); 
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
				Transaction transaction = new Transaction(
					rs.getInt("transaction_id"),
					rs.getString("postdate"),
					rs.getDouble("amount"),
					rs.getString("description"),
					rs.getInt("account_id")
					);
			
				transactionList.add(transaction); 
			}
			
			return transactionList;
			
		} catch (SQLException e) {
			System.out.println("Something went wrong with your database!"); 
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
				Transaction transaction = new Transaction(
					rs.getInt("transaction_id"),
					rs.getString("postdate"),
					rs.getDouble("amount"),
					rs.getString("description"),
					rs.getInt("account_id")
					);
			
				transactionList.add(transaction); 
			}
			
			return transactionList;
			
		} catch (SQLException e) {
			System.out.println("Something went wrong with your database!"); 
		}
		return null;
	}


	@Override
	public void addTransaction(Transaction transaction) {
		int counter = 0;

		try(Connection conn = ConnectionUtil.getConnection()){

			String sql = "insert into transactions (postdate, amount, description, account_id)" +
						 "values (default, ?,?,?)";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setDouble(1, transaction.getAmount());
			ps.setString(2, transaction.getDescription());
			ps.setInt(3, transaction.getAccount_id());
			
			counter=ps.executeUpdate(); 
			
			
		} catch (SQLException e) {
			System.out.println("Add transaction failed :(");
		}
		if (counter == 0) {
			System.out.println("Add Transaction ID "+ transaction.getAccount_id()+" failed: " );
		}
		else
		{
			System.out.println("A new Transaction created for Account ID: " + transaction.getAccount_id());
//			log.info("USER POSTED A TRANSACTION ON ACCOUNT ID: " + transaction.getAccount_id());
		}
	
	}
	
	@Override
	public void removeTransaction(int id) {
		int counter = 0;

		try(Connection conn = ConnectionUtil.getConnection()){
			
			String sql = "delete from transactions where transaction_id = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, id);
			counter=ps.executeUpdate();
			
			
		} catch (SQLException e) {
			System.out.println("Delete Transaction "+id+" failed.");
		}
	
	if (counter == 0) {
		System.out.println("Delete Transaction "+id+" failed.");
	}
	else 
	{
		System.out.println("Transaction ID: " + id+" is deleted.");
	log.warn("USER DELETED TRANSACTION ID: " + id);
	}
		
	}

		
	@Override
	public void updateTransaction(int id,String desc) {

		int counter = 0;

		try(Connection conn = ConnectionUtil.getConnection()){
			
			String sql = "update transactions set description = ? where transaction_id = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, desc);
			ps.setInt(2, id);
			
			counter=ps.executeUpdate();
			
			
		} catch (SQLException e) {
			System.out.println("You can't update Transaction ID : "+id+" Reference.");
		}
		if (counter == 0) {
			System.out.println("Update Transaction ID" + id+" failed.");
		}
		else 
		{
			System.out.println("Transaction ID "+id + " reference is changed to: " + desc);
			log.warn("USER UPDATED TRANSACTION REFERENCE TO : " + desc+ " FOR transaction ID: "+id);
		}

	}

}
