package com.revature.dao;

import java.util.List;

import com.revature.models.Account;

//Interfaces used to implement account related operations.
public interface AccountDaoInterface {

	public List<Account> getAccount(); //returns a List of all Accounts (select *)
	
	public List<Account> getAccountByName(String name); //this will get Account with a certain customer name (select where)
	
	public List<Account> getAccountById(int id); //return an Account given their id
	
	public void addAccount(Account account); //this will add an Account to the database (insert)
	
	public void removeAccount(int id); //this will remove Account using their id (delete)

	public void updateAccount(int accountid, int accounttypeid); //this will change the Account type based on its id

	
}
