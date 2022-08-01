package com.fujitsu.accountmgmt.service;

import java.util.List;

import com.fujitsu.accountmgmt.model.Account;

public interface AccountService {
	
	public List<Account> getAccounts();
	public boolean addAccount(int accountId, String name, String type, double balance, String branch);
	public boolean deleteAccount(int acc_id);
	public boolean updateAccount(int accountId, String name, String type, double balance, String branch);
	public Account viewAccount(int acc_id);
	public void printStats();
	public boolean fileImport();
	public boolean fileExport();

}
