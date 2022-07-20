package adv_java_labs.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AccountServiceArrayListImpl implements AccountService{
	
	List<Account> list=new ArrayList<Account>();

	@Override
	public boolean addAccount(Account account) {
		
		return list.add(account);
	}

	@Override
	public boolean updateAccount(Account account) {
		int index=list.indexOf(account);
		return list.set(index, account) != null?true:false;
	}

	@Override
	public boolean deleteAccount(Account account) {
		
		return list.remove(account);
	}

	@Override
	public Account getAccount(int accounId) {
		Account acc=null;

		for(Account account:list)
		{
			if(account.getAccountId()==accounId)
				acc=account;
				
		}
		return acc;
	}

	@Override
	public Collection<Account> getAccounts() {
		
		return list;
	}

}
