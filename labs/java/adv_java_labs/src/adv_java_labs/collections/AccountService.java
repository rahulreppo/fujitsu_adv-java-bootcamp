package adv_java_labs.collections;

import java.util.Collection;

public interface AccountService {
	
	public boolean addAccount(Account account);
	public boolean updateAccount(Account account);
	public boolean deleteAccount(Account account);
	public Account getAccount(int accounId);
	public Collection<Account> getAccounts();
	
}
