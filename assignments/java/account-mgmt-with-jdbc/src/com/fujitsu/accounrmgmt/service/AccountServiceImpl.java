package com.fujitsu.accounrmgmt.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.function.Function;
import java.util.function.Predicate;

import com.fujitsu.accountmgmt.dao.AccountDao;
import com.fujitsu.accountmgmt.dao.AccountDaoJdbcImpl;
import com.fujitsu.accountmgmt.model.Account;

public class AccountServiceImpl implements AccountService {
	
	AccountDao accountDao;

	Comparator<Account> NAME_ASC_SORT = new Comparator<Account>() {
		@Override
		public int compare(Account o1, Account o2) {
			return o1.getName().compareTo(o2.getName());

		}
	};

	public AccountServiceImpl() {
		accountDao = new AccountDaoJdbcImpl();
	}

	@Override
	public boolean create(Account acc) {
		// TODO Auto-generated method stub
		return accountDao.create(acc);
	}

	@Override
	public boolean update(Account acc) {
		// TODO Auto-generated method stub
		return accountDao.update(acc);
	}

	@Override
	public boolean delete(int acountid) {
		// TODO Auto-generated method stub
		return accountDao.delete(acountid);
	}

	@Override
	public Account get(int acountid) {
		// TODO Auto-generated method stub
		return accountDao.get(acountid);
	}

	@Override
	public List<Account> getAll() {
		// TODO Auto-generated method stub
		return accountDao.getAll();
	}

	@Override
	public void bulkImport() {
		int counter = 0;
		try (Scanner in = new Scanner(new FileReader(".\\input\\Account-input.txt"))) {
			while (in.hasNextLine()) {
				String acc = in.nextLine();
				Account account = new Account();
				StringTokenizer tokenizer = new StringTokenizer(acc, ",");
				//Id
				account.setAccountId(Integer.parseInt(tokenizer.nextToken()));
				// Balance
				account.setBalance(Integer.parseInt(tokenizer.nextToken()));
				// Branch
				account.setBranch(tokenizer.nextToken());
				// Name
				account.setName(tokenizer.nextToken());
				// type
				account.setType(tokenizer.nextToken());


				counter++;
			}
			System.out.format("%d Accounts are imported successfully.", counter);
		} catch (IOException e) {
			System.out.println("Error occured while importing Account data. " + e.getMessage());
		}
	}

	@Override
	public void bulkExport() {
		try (FileWriter out = new FileWriter(".\\input\\Account-output.txt")) {
			accountDao
					.getAll()
					.stream()
					.map(emp -> emp.getAccountId() + "," + emp.getName() + "," + emp.getBalance() + ","
							+ emp.getBranch() + "," + emp.getType() + "," + "\n")
					.forEach(rec -> {
						try {
							out.write(rec);
						} catch (IOException e) {
							System.out
									.println("Error occured while writing employee data into file. " + e.getMessage());
							e.printStackTrace();
						}
					});
			System.out.format("%d Employees are exported successfully.", accountDao.getAll().size());			
		} catch (IOException e) {
			System.out.println("Error occured while exporting employee data. " + e.getMessage());
		}		
	}

	
}	
