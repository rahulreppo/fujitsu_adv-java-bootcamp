package com.fujitsu.accountmgmt;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.fujitsu.accountmgmt.model.Account;
import com.fujitsu.accountmgmt.service.AccountServiceImpl;

public class AccountMgmtMain {
	private static Scanner sc;
	private static AccountServiceImpl accService;

	public static void main(String[] args) {

		sc = new Scanner(System.in);
		accService = new AccountServiceImpl();
		ExecutorService executor = Executors.newCachedThreadPool();

		System.out.print("Welcome to Account Management App!");

		while (true) {

			System.out.println("\n");
			System.out.println("1. Add Account");
			System.out.println("2. View Account");
			System.out.println("3. Update Account");
			System.out.println("4. Delete Account");
			System.out.println("5. View All Account");
			System.out.println("6. Print Statistics");
			System.out.println("7. Import");
			System.out.println("8. Export");
			System.out.println("9. Exit");

			System.out.print("Enter the option: ");
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
				switch (option) {
				case 1:
					Account ac=new Account();
					System.out.println("Please enter Account id");
					ac.setAccountId(sc.nextInt());
					System.out.println("Please enter Account Balance");
					ac.setBalance(sc.nextDouble());
					System.out.println("Please enter Account Branch");
					ac.setBranch(sc.next());
					System.out.println("Please enter Account Name");
					ac.setName(sc.next());
					System.out.println("Please enter Account Type");
					ac.setType(sc.next());
					
					accService.create(ac);
					System.out.println("Employee has been added successfully!");
					break;
				case 2:
					System.out.print("Please enter Account id: ");
					
					Account acc = accService.get(sc.nextInt());
					printHeader();
					printDetail(acc);
					break;
				case 3:
					System.out.print("Please enter employee id: ");
					
					Account ForUpdate = accService.get(sc.nextInt());
					captureAccDetail(ForUpdate);
					accService.update(ForUpdate);
					System.out.println("Employee has been updated successfully!");
					break;
				case 4:
					System.out.print("Please enter Account id: ");
					
					accService.delete(sc.nextInt());
					System.out.println("Employee has been deleted successfully!");
					break;
				case 5:
					List<Account> accounts = accService.getAll();
					printHeader();
					for (Account account : accounts) {
						printDetail(account);
					}
					break;
				case 6:
					printStatistics();
					break;
				case 7:
					Callable<Boolean> importThread = new Callable<Boolean>() {
						@Override
						public Boolean call() throws Exception {
							System.out.println(Thread.currentThread() + " Waiting for 5s to start import process.");
							Thread.sleep(5000);
							accService.bulkImport();
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
							accService.bulkExport();
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
					break;
				}
			} catch (NumberFormatException e) {
				System.out.println("Invalid input. Please enter valid input.");
			}
		}
	}
		private static void printHeader() {
			System.out.format("\n%5s %15s %15s %15s %15s","AccountId","Balance" ,"Branch","Name","Type");
		}

		private static void printDetail(Account acc) {
			if (acc == null) {
				return;
			}

			System.out.format("\n%5d %15f %15s %15s %15s",acc.getAccountId(),acc.getBalance(),acc.getBranch(),acc.getName(),acc.getType());
		}
		
		private static void captureAccDetail(Account account) throws NumberFormatException {
			
			System.out.print("Enter Account Balance: ");
			account.setBalance(sc.nextDouble());
			
			System.out.print("Enter Account Branch: ");
			account.setBranch(sc.next());
			
			System.out.print("Enter Account holder Name: ");
			account.setName(sc.next());

			System.out.print("Enter Account Type: ");
			account.setType(sc.next());
		}

		
		
		private static void printStatistics() {

			System.out.println("No of accounts which has balance more than 1 lac: "
					+ accService.getAccountCountBalanceGreaterThan(e -> e.getBalance() > 100000));
			
			System.out.println("Show no of account by account type: " + accService.getAccountCountByType());
			System.out.println("Show no of accounts by account type with sorting: " + accService.getAccountCountByTypeOdered());
			System.out.println("Show avg balance by account type: " + accService.getAvgBalanceByType());
			
			System.out.println("List account ids whose account name contains given name='rahul':" + accService.getAccountsIdscontainsNames("rahul"));

		}

	}
