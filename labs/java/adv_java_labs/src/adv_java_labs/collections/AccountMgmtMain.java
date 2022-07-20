package adv_java_labs.collections;

import java.util.Collection;

public class AccountMgmtMain {

	public static void main(String[] args) {


AccountService service=new AccountServiceArrayListImpl();
		
		// Creating Two Accounts
		Account acc1=new Account(100, "Rahul", "saving", 10000.00, "pune");
		Account acc2=new Account(101, "Radha", "csalary", 20000.00, "mumbai");
		 service.addAccount(acc1);
		 service.addAccount(acc2);
		 
		 //List All Account
//		 Collection<Account> account=service.getAccounts();
//		 for(Account acc:account)
//		 {
//			System.out.println(acc); 
//		 }
		 
		 listAccounts(service.getAccounts());
		
		 //Update First Account
		 acc1.setBalance(750000);
		 boolean isUpdate=service.updateAccount(acc1);
		 
		 if(isUpdate) System.out.println("Updatation Successfully");
		 else System.out.println("Updation failed !!");
		 
		 System.out.println(service.getAccount(100));
		 
		 boolean isDeleted= service.deleteAccount(acc2);
		 if(isUpdate) System.out.println("Deletion Successfully");
		 else System.out.println("Deletion failed !!");
		 
		 listAccounts(service.getAccounts());
		 
		
	}

	 private static void listAccounts(Collection<Account> accounts)
	 {
		 for(Account acc:accounts)
		 {
			System.out.println(acc); 
		 }
	 }
}
