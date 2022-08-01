package com.fujitsu.accounrmgmt.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

import com.fujitsu.accountmgmt.model.Account;

public class AccountServiceImpl implements AccountService {

	List<Account> account = new ArrayList<Account>();
	//Map<Integer, Account> account = new HashMap<>();

	@Override
	public List<Account> getAccounts() {
		
		return account;
	}

	@Override
	public boolean addAccount(int accountId, String name, String type, double balance, String branch) {
		Account ac = new Account(accountId, name, type, balance, branch);
		if(account.add(ac)!= false) {
			return false;
			
		}
		return true;
	}

	@Override
	public boolean deleteAccount(int acc_id) {
		for(int i=0;i<account.size();i++) {
			if(account.get(i).getAccountId()==acc_id) {
				account.remove(i);
				return true;
			}
		}
		return false;
	
	}

	@Override
	public boolean updateAccount(int accountId, String name, String type, double balance, String branch) {
		for(int i=0;i<account.size();i++) {
			if(account.get(i).getAccountId()==accountId) {
				if(name!=null) {
					account.get(i).setName(name);
				}
				if(type!=null) {
					account.get(i).setType(type);
				}
				if(balance!=0.0) {
					account.get(i).setBalance(balance);
				}
				if(branch!=null) {
					account.get(i).setBranch(branch);
				}
				
				return true;
			}
		}
		return false;
	}
	@Override
	public Account viewAccount(int acc_id) {
		Account acc = null;
		for(int i=0;i<account.size();i++) {
			if(account.get(i).getAccountId()==acc_id) {
				acc = new Account(account.get(i).getAccountId(),account.get(i).getName(),account.get(i).getType(),account.get(i).getBalance(),account.get(i).getBranch());
				return acc;
			}
		}
		return acc;
	}

	@Override
	public void printStats() {
		Scanner sc = new Scanner(System.in);
		int balgrtthan1lakh=0;
		int[] acc_type = new int[4];
		System.out.println("Enter the account type to get avg. balance : ");
		String acc_typename = sc.next();
		int avgbal=0;
		int noofacc=0;
		System.out.println("Enter the name to show list of accounts with that name : ");
		String acc_name = sc.next();
		List<Integer> acc_withname = new ArrayList<Integer>();
		for(int i=0;i<account.size();i++) {
			if(account.get(i).getBalance()>100000) {
				balgrtthan1lakh ++;
			}
			if(account.get(i).getType()=="saving") {
				acc_type[0]+=1;
			}
			if(account.get(i).getType()=="current") {
				acc_type[1]+=1;
			}
			if(account.get(i).getType()=="loan") {
				acc_type[2]+=1;
			}
			if(account.get(i).getType()=="fd") {
				acc_type[3]+=1;
			}
			if(account.get(i).getType()==acc_typename) {
				avgbal+=account.get(i).getBalance();
				noofacc++;
			}
			if(account.get(i).getName().equals(acc_name)) {
				acc_withname.add(account.get(i).getAccountId());
			}
		}
		System.out.println("No of accounts which has balance more than 1 lac: "+balgrtthan1lakh);
		System.out.println("No of Saving accounts: "+acc_type[0]+"No of Current accounts: "+acc_type[1]+ "No of Loan accounts: "+acc_type[2]+"No of Fixed Deposite accounts: "+acc_type[3]);
		
		System.out.println("Average Balane of "+acc_typename+" account type is :"+(avgbal/noofacc));
		System.out.println("List of id's of account with name : "+acc_name+" is "+acc_withname);
		sc.close();
	}

	@Override
	public synchronized void fileImport() {
		System.out.format("%n%s - Import started %n", Thread.currentThread().getName());
		int counter = 0;
		try (Scanner in = new Scanner(new FileReader("D:\\Training\\fujitsu_adv-java-bootcamp\\assignments\\datainput.txt"))) {
			System.out.println("Implorting file...");
			while (in.hasNextLine()) {
				String acc = in.nextLine();
				System.out.println("Importing employee - " + acc);
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
			System.out.format("%s - %d Accounts are imported successfully.", Thread.currentThread().getName(),
					counter);
		} catch (Exception e) {
			System.out.println("Error occured while importing Account data. " + e.getMessage());
		}
	}

	@Override
	public synchronized void fileExport() {
		System.out.format("%n%s - Export started %n", Thread.currentThread().getName());
		try 
		{
			FileOutputStream fout=new FileOutputStream("D:\\Training\\fujitsu_adv-java-bootcamp\\assignments\\dataoutput.txt");    
                for(int i=0;i<account.size();i++) {
          	String str = account.get(i).toString();
          	fout.write(str.getBytes());
          }
          fout.close();    
          
			System.out.format("%d Accounts are exported successfully.");
		} catch (IOException e) {
			System.out.println("Error occured while exporting Account data. " + e.getMessage());
		}
	}
}