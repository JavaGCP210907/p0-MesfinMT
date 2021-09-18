package com.revature.models;

public class Transaction {

	private int transaction_id;
	private String postdate;
	private String amount;
	private String description;
	private int account_id;
	
	public Transaction() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Transaction(int transaction_id, String postdate, String amount, String description, int account_id) {
		super();
		this.transaction_id = transaction_id;
		this.postdate = postdate;
		this.amount = amount;
		this.description = description;
		this.account_id = account_id;
	}

	public Transaction(String postdate, String amount, String description, int account_id) {
		super();
		this.postdate = postdate;
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

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + account_id;
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((postdate == null) ? 0 : postdate.hashCode());
		result = prime * result + transaction_id;
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
		Transaction other = (Transaction) obj;
		if (account_id != other.account_id)
			return false;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (postdate == null) {
			if (other.postdate != null)
				return false;
		} else if (!postdate.equals(other.postdate))
			return false;
		if (transaction_id != other.transaction_id)
			return false;
		return true;
	}

	
	
}
