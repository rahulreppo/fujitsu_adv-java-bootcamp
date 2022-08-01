package com.fujitsu.accountmgmt.model;

public class Account {
	int accountId;
	double balance;
	String branch;
    String name;
    String type;
   
    
    
    public Account() {
    	
    }
    
	public Account(int accountId, double balance, String branch,String name, String type) {
		super();
		this.accountId = accountId;
		this.balance = balance;
		this.branch = branch;
		this.name = name;
		this.type = type;
		
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Account [accountId=" + accountId + ", balance=" + balance + ", branch=" + branch + ", name=" + name
				+ ", type=" + type + "]";
	}

	
	
    
}
