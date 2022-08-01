package com.fujitsu.accountmgmt.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.fujitsu.accountmgmt.model.Account;
import com.mysql.cj.jdbc.MysqlDataSource;

public class AccountDaoJdbcImpl implements AccountDao {
	MysqlDataSource datasource = null;
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	
	public AccountDaoJdbcImpl() {
		datasource = new MysqlDataSource();
		datasource.setServerName("localhost");
		datasource.setDatabaseName("jdbctraining");
		datasource.setUser("training");
		datasource.setPassword("training");

		try {
			conn = datasource.getConnection();
			System.out.println("Connection created successfully. " + conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean create(Account account) {
		boolean status = false;
		try {
			stmt = conn.createStatement();

			String query = "INSERT INTO Account(accountid, balance, branch, name, type) values(\""
					+ account.getAccountId() + "\"," + account.getBalance() + ",\"" + account.getBranch() + "\",\""
					+ account.getName() + "\",\"" + account.getType() + "\")";

			status = stmt.execute(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}


	@Override
	public boolean update(Account account) {
		boolean status = false;
		try {
			stmt = conn.createStatement();

			String query ="UPDATE account SET accountid = \"" + account.getAccountId() + "\", balance = " + account.getBalance()
			+ ",branch = \"" + account.getBranch() + "\",name = \"" + account.getName()
			+ "\", type = \"" + account.getType() + "\" WHERE accountid = " + account.getAccountId();

			status = stmt.execute(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}
	@Override
	public boolean delete(int id) {
		boolean status = false;
		try {
			stmt = conn.createStatement();

			String query = "DELETE FROM account WHERE accountid = " + id;

			status = stmt.execute(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}


	@Override
	public Account get(int accountId) {
		Account acc = null;
		String query = "SELECT * FROM account WHERE accountid = " + accountId;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);

			while (rs.next()) {
				int accountid = rs.getInt("accountid");
				double balance = rs.getDouble("balance");
				String branch = rs.getString("branch");
				String name = rs.getString("name");
				String type = rs.getString("type");
				
				
				acc = new Account(accountid,balance,branch,name,type);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return acc;
	}

	@Override
	public List<Account> getAll() {
		List<Account> account = new ArrayList<>();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM account");

			while (rs.next()) {
				int accountid = rs.getInt("accountid");
				String name = rs.getString("name");
				Double balance = rs.getDouble("balance");
				String type = rs.getString("type");				
				String branch = rs.getString("branch");
				
				account.add(new Account(accountid,balance,branch,name,type));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return account;
	}
}	
