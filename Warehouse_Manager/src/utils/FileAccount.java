package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

import objects.Account;

public class FileAccount {
	public static int count = 10;

	public static int getPos(RandomAccessFile raf) throws IOException {
		return (int) raf.length();
	}

	public static List<Account> genarateAccount(int count) {
		List<Account> listAccount = new ArrayList<>();
		String[] usernames = { "nguyenquanghuy", "nguyenchibao", "lethientuanvu", "nam" };
		String[] passwords = { "abc123", "123@abc", "abc@123", "123456", "abc@123456" };
		Account account;
		int index;
		for (int i = 0; i < count; i++) {
			account = new Account();
			index = (int) (Math.random() * usernames.length);
			account.setUsername(usernames[index]);
			index = (int) (Math.random() * passwords.length);
			account.setPassword(passwords[index]);
			listAccount.add(account);
		}
		return listAccount;
	}

	public static void outputFileAccount(String fileAccount) throws IOException {
		File file = new File("Account.bin");
		if (!file.exists()) {
			List<Account> listAccount = genarateAccount(count);
			addAccountToFile(fileAccount, listAccount);
		}
	}

	public static void addAccountToFile(String fileAccount, List<Account> list) throws IOException {
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileAccount));
		out.write(list.size());
		for (int index = 0; index < list.size(); index++) {
			out.writeObject(list.get(index));
			;
		}
		out.close();
	}

	public static void binaryInputFileAccount(String fileAccount, List<Account> list)
			throws IOException, ClassNotFoundException {
		ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileAccount));
		int n = in.read();
		Account account;
		for (int i = 0; i < n; i++) {
			account = (Account) in.readObject();
			list.add(account);
		}
		in.close();
	}
}
