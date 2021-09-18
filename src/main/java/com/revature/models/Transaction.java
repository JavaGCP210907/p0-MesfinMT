package com.revature.models;

public class Transaction {

	private int transaction_id;
	private String postdate;
	private double amount;
	private String description;
	private int account_id;
	
	public Transaction() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Transaction(int transaction_id, String postdate, double amount, String description, int account_id) {
		super();
		this.transaction_id = transaction_id;
		this.postdate = postdate;
		this.amount = amount;
		this.description = description;
		this.account_id = account_id;
	}

	public Transaction(String postdate, double amount, String description, int account_id) {
		super();
		this.postdate = postdate;
		this.amount = amount;
		this.description = description;
		this.account_id = account_id;
	}

	public Transaction(double amount, String description, int account_id) {
		super();
		this.amount = amount;
		this.description = description;
		this.account_id = account_id;
	}

	public int getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(int transaction_id) {
		this.transaction_id = transaction_id;
	}

	public String getPostdate() {
		return postdate;
	}

	public void setPostdate(String postdate) {
		this.postdate = postdate;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getAccount_id() {
		return account_id;
	}

	public void setAccount_id(int account_id) {
		this.account_id = account_id;
	}

	@Override
	public String toString() {
		return "Transactions [transaction_id=" + transaction_id + ", postdate=" + postdate + ", amount=" + amount
				+ ", description=" + description + ", account_id=" + account_id + "]";
	}



	
	
}
