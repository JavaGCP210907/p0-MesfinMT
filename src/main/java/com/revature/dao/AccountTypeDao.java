package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.AccountType;
import com.revature.utils.ConnectionUtil;

public class AccountTypeDao implements AccountTypeDaoInterface {

	@Override
	public List<AccountType> getAccountTypes() {
		try(Connection conn = ConnectionUtil.getConnection()) { //getConnection() comes from our ConnectionUtil Class
			
			ResultSet rs = null;
			
			String sql = "select * from accounttypes";
			Statement s = conn.createStatement();
			
			rs = s.executeQuery(sql);
			
			List<AccountType> accounttypeList = new ArrayList<>();
			
			while(rs.next()) { //while there are results in the result set...
				
				AccountType e = new AccountType(
						rs.getInt("accounttype_id"),
						rs.getString("type"),
						rs.getDouble("rate")
						);
				
				accounttypeList.add(e); //e is the new Employee object we created above
			}
			
			return accounttypeList;
			
		} catch (SQLException e) {
			System.out.println("Something went wrong with your database!"); //generic console message
			e.printStackTrace(); //stack trace so we actually know what went wrong
		}
		
		return null; //we add this after the try/catch so Java won't yell.
					 //(Since there is no guarantee the try with resources block will run)
	}

	@Override
	public void addAccountType(AccountType accounttype) {

		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "insert into accounttypes (type, rate)" +
						 "values (?, ?)";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, accounttype.getType());
			ps.setDouble(2, accounttype.getRate());
			
			ps.executeUpdate();
			
			System.out.println("AccountType " + accounttype.getType() + " is created.");
			
		} catch (SQLException e) {
			System.out.println("add AccountType failed :(");
			e.printStackTrace();
		}
		
	}
	@Override
	public void removeAccountType(int id) {
		try(Connection conn = ConnectionUtil.getConnection()){
			
			String sql = "delete from accounttypes where accounttype_id = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, id);
			ps.executeUpdate();
			
			System.out.println("Account type: " + id+" is deleted.");
			
		} catch (SQLException e) {
			System.out.println("you can't remove Account type "+id);
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void updateInterestRate(int id,double rate) {
	try(Connection conn = ConnectionUtil.getConnection()){
			
			String sql = "update accounttypes set rate = ? where accounttype_id = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setDouble(1, rate);
			ps.setInt(2, id);
			
			ps.executeUpdate();
			
			System.out.println("Account Type "+id + " Interest rate is successfully updated to: " + rate);
			
		} catch (SQLException e) {
			System.out.println("You can't update Account Type : "+id);
			e.printStackTrace();
		}

	}
}
