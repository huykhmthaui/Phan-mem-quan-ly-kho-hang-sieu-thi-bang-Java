package utils;

import java.io.IOException;
import java.util.List;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

import interfaces.IAccountManager;
import objects.Account;

public class AccountManager implements IAccountManager {

	public String partFile = "Account.bin";
	boolean isCheck = false;
	
	@Override
	public boolean addAccount(Account a, List<Account> list) {
		for (int index = 0; index < list.size(); index++) {
			if (list.get(index).getUsername().equals(a.getUsername())) {
				isCheck = true;
				break;
			}
		}
		if (isCheck) {
			return false;
		}
		list.add(a);
		try {
			FileAccount.addAccountToFile(partFile, list);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public boolean login(Account a, List<Account> list) {
		if (list.contains(a)) {
			return true;
		}
		return false;
	}

}
