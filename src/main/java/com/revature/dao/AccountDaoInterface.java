package com.revature.dao;

import java.util.List;

import com.revature.models.Account;

public interface AccountDaoInterface {

	public List<Account> getAccount(); //returns a List of all Accounts (select *)
	
	public List<Account> getAccountByName(String name); //this will get Account with a certain name (select where)
	
	public Account getAccountById(int id); //return an Account given their id
	
	public void addAccount(Account account); //this will add an Account to the database (insert)
	
	public void removeAccount(int id); //this will remove Account using their id (delete)

	public void updateAccount(String name, int id); //this will change a Account's owner's name based on its id

	
}
