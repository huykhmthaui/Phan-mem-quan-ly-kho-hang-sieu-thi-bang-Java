package interfaces;

import java.util.List;

import objects.Account;

public interface IAccountManager {
	boolean addAccount(Account a, List<Account> list);
	boolean login(Account a, List<Account> list);
}