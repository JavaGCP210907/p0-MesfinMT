package com.revature.dao;

import java.util.List;

import com.revature.models.AccountType;

public interface AccountTypeDaoInterface {

	public List<AccountType> getAccountTypes(); //returns a List of all AccountTypes (select *)
	
	public void addAccountType(AccountType accounttype); //this will add a AccountType to the database (insert)
	
	public void removeAccountType(int id); //this will remove AccountType using their id (delete)

	public void updateInterestRate(int id,double rate); //this will change a InterestRate based on its ID

}
