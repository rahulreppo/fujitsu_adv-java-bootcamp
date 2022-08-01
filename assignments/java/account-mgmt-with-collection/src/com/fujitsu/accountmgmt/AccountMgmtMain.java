package com.fujitsu.accountmgmt;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.fujitsu.accountmgmt.model.Account;
import com.fujitsu.accountmgmt.service.AccountService;
import com.fujitsu.accountmgmt.service.AccountServiceImpl;

public class AccountMgmtMain {

	public static void main(String[] args) {
		
				Scanner sc = new Scanner(System.in);
				AccountService as = new AccountServiceImpl();
				System.out.println("Welcome to Account Management App!");
				
				int choice =0;
				while(choice!=9) {
					
					System.out.println(
							  "1] Add Account\r\n"
							+ "2] Update Account\r\n"
							+ "3] Delete Account\r\n"
							+ "4] View Account\r\n"
							+ "5] View All Accounts\r\n"
							+ "6] Print Statistics\r\n"
							+ "7] Import\r\n"
							+ "8] Export\r\n"
							+ "9] Exit");
					System.out.print("Enter the option: ");
					
					// Get option from user
					try {
						choice = Integer.parseInt(sc.next());
					} catch (NumberFormatException e) {
						System.out.println("Invalid option. Please enter valid option.");
						continue;
					}
					
		
					switch(choice) {
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
					    System.out.println("Employee has been added successfully!");
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
					    boolean update = as.updateAccount(acc_Id, acc_name, acc_type, acc_balance, acc_branch);
					    System.out.println("Account successfully Updated !!!");
					    break;

					case 3:
						System.out.println("Enter Account id to be deleted: ");
						int acId=sc.nextInt();
						 boolean delete = as.deleteAccount(acId);
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
						as.fileImport();
						break;
						
					case 8:
						as.fileExport();
						break;
						
					default:
						System.out.println("Please choose correct option");
						sc.close();
						break;
						
					   
					  
					}
				}

			}

		}
