package objects;

import java.io.Serializable;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Account implements Serializable{
	private static final long serialVersionUID = 1L;
	
	public static final String USERNAME = "None";
	public static final String PASSWORD = "None";

	private String username;
	private String password;

	public Account() {
		this(USERNAME, PASSWORD);
	}

	public Account(String username, String password) {

		this.username = username;
		this.password = password;
	}

	public Account(JTextField txtUsername, JPasswordField txtPassword) {
		this.username = txtUsername.getText();
		this.password = new String(txtPassword.getPassword());
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return this.username + "," + this.password + "\n";
	}

	@Override
	public boolean equals(Object obj) {
		Account other = (Account) obj;
		if (this.username.equals(other.getUsername())) {
			return this.password.equals(other.getPassword());
		}
		return false;
	}
}