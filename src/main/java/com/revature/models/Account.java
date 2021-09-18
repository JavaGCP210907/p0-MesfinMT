package com.revature.models;

public class Account {

	private int account_id;
	private double balance;
	private String opendate;
	private int accounttype_id;
	private int customer_id;

	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Account(int account_id, double balance, String opendate, int accounttype_id, int customer_id) {
		super();
		this.account_id = account_id;
		this.balance = balance;
		this.opendate = opendate;
		this.accounttype_id = accounttype_id;
		this.customer_id = customer_id;
	}

	public Account(double balance, String opendate, int accounttype_id, int customer_id) {
		super();
		this.balance = balance;
		this.opendate = opendate;
		this.accounttype_id = accounttype_id;
		this.customer_id = customer_id;
	}

	public Account(int accounttype_id, int customer_id) {
		super();
		this.accounttype_id = accounttype_id;
		this.customer_id = customer_id;
	}

	public int getAccount_id() {
		return account_id;
	}

	public void setAccount_id(int account_id) {
		this.account_id = account_id;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getOpendate() {
		return opendate;
	}

	public void setOpendate(String opendate) {
		this.opendate = opendate;
	}

	public int getAccounttype_id() {
		return accounttype_id;
	}

	public void setAccounttype_id(int accounttype_id) {
		this.accounttype_id = accounttype_id;
	}

	public int getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}

	@Override
	public String toString() {
		return "Accounts [account_id=" + account_id + ", balance=" + balance + ", opendate=" + opendate
				+ ", accounttype_id=" + accounttype_id + ", customer_id=" + customer_id + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + account_id;
		result = prime * result + accounttype_id;
		long temp;
		temp = Double.doubleToLongBits(balance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + customer_id;
		result = prime * result + ((opendate == null) ? 0 : opendate.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (account_id != other.account_id)
			return false;
		if (accounttype_id != other.accounttype_id)
			return false;
		if (Double.doubleToLongBits(balance) != Double.doubleToLongBits(other.balance))
			return false;
		if (customer_id != other.customer_id)
			return false;
		if (opendate == null) {
			if (other.opendate != null)
				return false;
		} else if (!opendate.equals(other.opendate))
			return false;
		return true;
	}

}
