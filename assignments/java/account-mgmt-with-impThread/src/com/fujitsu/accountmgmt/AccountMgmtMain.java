package com.fujitsu.accountmgmt;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.fujitsu.accounrmgmt.service.AccountService;
import com.fujitsu.accounrmgmt.service.AccountServiceImpl;
import com.fujitsu.accountmgmt.model.Account;


public class AccountMgmtMain {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		AccountService as = new AccountServiceImpl();
		ExecutorService executor = Executors.newCachedThreadPool();

		System.out.println("Welcome to Employee Management App!");
		
		
		while(true) {
			System.out.println("1] Add Account\r\n"
					+ "2] Update Account\r\n"
					+ "3] Delete Account\r\n"
					+ "4] View Account\r\n"
					+ "5] View All Accounts\r\n"
					+ "6] Print Statistics\r\n"
					+ "7] Import\r\n"
					+ "8] Export\r\n"
					+ "9] Exit");
			System.out.println("Enter your option :");
			int option = 0;
			// Get option from user
			try {
				option = Integer.parseInt(sc.next());
			} catch (NumberFormatException e) {
				System.out.println("Invalid option. Please enter valid option.");
				continue;
			}
			int empId;
			try {
			switch(option) {
			case 1:
				System.out.println("Enter Account id: ");
				int accountId=sc.nextInt();
				System.out.println("Enter Account holder name: ");
			    String name = sc.next();
			    System.out.println("Enter Account type: ");
			    String type=sc.next();
			    System.out.println("Enter Account balance: ");
			    double balance=sc.nextDouble();
			    System.out.println("Enter Account branch: ");
			    String branch=sc.next();
			    as.addAccount(accountId, name, type, balance, branch);
			    break;
			case 2:
				System.out.println("Enter Account id to be updated: ");
				int acc_Id=sc.nextInt();
				System.out.println("Enter Account holder name: ");
			    String acc_name = sc.next();
			    System.out.println("Enter Account type: ");
			    String acc_type=sc.next();
			    System.out.println("Enter Account balance: ");
			    double acc_balance=sc.nextDouble();
			    System.out.println("Enter Account branch: ");
			    String acc_branch=sc.next();
			    as.updateAccount(acc_Id, acc_name, acc_type, acc_balance, acc_branch);
			    System.out.println("Account successfully Updated !!!");
			    break;

			case 3:
				System.out.println("Enter Account id to be deleted: ");
				int acId=sc.nextInt();
				 as.deleteAccount(acId);
				 System.out.println("Account successfully Deleted !!!");
				 break;
				 
			case 4:
				System.out.println("Enter Account id to be view: ");
				int accId=sc.nextInt();
				System.out.println(as.viewAccount(accId));
				break;
				
			case 5:
				List<Account> acc = new ArrayList<Account>(as.getAccounts());
				for(int i=0;i<acc.size();i++) {
					System.out.println(String.format("%10d %10s %10s %10f %10s", acc.get(i).getAccountId(), acc.get(i).getName(), acc.get(i).getType(), acc.get(i).getBalance(), acc.get(i).getBranch()));
				}
				
				break;
				
			case 6:
				as.printStats();
				break;
				
			case 7:
				Callable<Boolean> importThread = new Callable<Boolean>() {
					@Override
					public Boolean call() throws Exception {
						System.out.println(Thread.currentThread() + " Waiting for 5s to start import process.");
//						Thread.sleep(5000);
						as.fileImport();							
						return true;
					}
				};
				
				Future<Boolean> importFuture = executor.submit(importThread);
				System.out.println(Thread.currentThread().getName() + " Import process triggered");				
				
				break;
			case 8:

				Callable<Boolean> exportThread = new Callable<Boolean>() {
					@Override
					public Boolean call() throws Exception {
						System.out.println(Thread.currentThread() + " Waiting for 5s to start export process.");
						Thread.sleep(5000);
						as.fileExport();							
						return true;
					}
				};
				
				Future<Boolean> exportFuture = executor.submit(exportThread);
				System.out.println(Thread.currentThread().getName() + " Export process triggered");						
				
				
				break;	
				
			case 9:
				
				System.out.println("Thank you!!!");
				executor.shutdown();
				sc.close();
				System.exit(0);
				break;

				
			default:
				
				sc.close();
				break;
			  
			}
			
		} catch (NumberFormatException e) {
			System.out.println("Invalid input. Please enter valid input.");
		}
		}

	}

}
