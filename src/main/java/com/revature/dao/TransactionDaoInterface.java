package com.revature.dao;

import java.util.List;

import com.revature.models.Transaction;

public interface TransactionDaoInterface {

	public List<Transaction> getTransaction(); //returns a List of all Transaction (select *)
	
	public List<Transaction> getTransactionByAccountId(int id); //this will List Transactions with a certain Account id (select where)
	
	public List<Transaction> getTransactionByTransactionId(int id); //return a Transaction given their Transaction id
	
	public void addTransaction(Transaction transaction); //this will add an Transaction to the database (insert)
	
	public void removeTransaction(int id); //this will remove Transaction using their id (delete)

	public void updateTransaction(int id,String Description); //this will change a Transaction's reference based on its id

	
}
