package com.fujitsu.accounrmgmt.service;

import java.util.List;
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

}
