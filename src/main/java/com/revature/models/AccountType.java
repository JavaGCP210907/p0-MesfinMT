package com.revature.models;

public class AccountType {
	
	private int accounttype_id;
	private String type;
	private double rate;

	
	public AccountType() {
		super();
		// TODO Auto-generated constructor stub
	}


	public AccountType(int accounttype_id, String type, double rate) {
		super();
		this.accounttype_id = accounttype_id;
		this.type = type;
		this.rate = rate;
	}


	public AccountType(String type, double rate) {
		super();
		this.type = type;
		this.rate = rate;
	}


	public int getAccounttype_id() {
		return accounttype_id;
	}


	public void setAccounttype_id(int accounttype_id) {
		this.accounttype_id = accounttype_id;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public double getRate() {
		return rate;
	}


	public void setRate(double rate) {
		this.rate = rate;
	}


	@Override
	public String toString() {
		return "AccountTypes [accounttype_id=" + accounttype_id + ", type=" + type + ", rate=" + rate + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + accounttype_id;
		long temp;
		temp = Double.doubleToLongBits(rate);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		AccountType other = (AccountType) obj;
		if (accounttype_id != other.accounttype_id)
			return false;
		if (Double.doubleToLongBits(rate) != Double.doubleToLongBits(other.rate))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	
	
}
