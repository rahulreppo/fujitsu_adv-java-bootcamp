package com.fujitsu.accountmgmt.service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

import com.fujitsu.accountmgmt.model.Account;

public interface AccountService {
	public boolean create(Account acc);

	public boolean update(Account acc);

	public boolean delete(int acountid);

	public Account get(int acountid);

	public List<Account> getAll();
	
	

	public void bulkImport();

	public void bulkExport();
	
	public boolean validate(Account acc, String msg, Predicate<Account> condition,
			Function<String, Boolean> operation);
	
//	a] No of accounts which has balance more than 1 lac
	public long getAccountCountBalanceGreaterThan(Predicate<Account> condition);
	
//	b] Show no of account by account type
	public Map<String, Long> getAccountCountByType();

//	c] Show no of accounts by account type with sorting
	public Map<String, Long> getAccountCountByTypeOdered();
	
//	d] Show avg balance by account type
	public Map<String, Double> getAvgBalanceByType();
	
//	e] List account ids whose account name contains given name
	public List<Integer> getAccountsIdscontainsNames(String contains);

	public void clear();

	
}
