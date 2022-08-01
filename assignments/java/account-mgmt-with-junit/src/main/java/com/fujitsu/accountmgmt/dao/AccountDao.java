package com.fujitsu.accountmgmt.dao;

import java.util.List;

import com.fujitsu.accountmgmt.model.Account;

public interface AccountDao {
	public boolean create(Account account);

	public boolean update(Account account);

	public boolean delete(int id);

	public Account get(int id);

	public List<Account> getAll();
}
