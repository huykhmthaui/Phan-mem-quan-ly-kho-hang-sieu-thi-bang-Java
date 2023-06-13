package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import interfaces.IAccountManager;
import objects.Account;
import utils.AccountManager;
import utils.FileAccount;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class LoginGUI extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	private IAccountManager accountmanager;
	private String pathFile = "Account.bin";
	private List<Account> list;
	private MenuGUI menuGUI;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGUI frame = new LoginGUI();
					frame.setVisible(true);
					frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void initAccount() {
		try {
			FileAccount.outputFileAccount(pathFile);
			FileAccount.binaryInputFileAccount(pathFile, list);
			list.forEach(p -> System.out.println(p));
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 */
	public LoginGUI() {
		list = new ArrayList<Account>();
		accountmanager = new AccountManager();
		menuGUI = new MenuGUI();
		initAccount();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 800);
		setBackground(Color.white);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout());
		contentPane.setBackground(Color.white);

		JPanel container = new JPanel();
		container.setLayout(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;

		JPanel contain = new JPanel();
		contain.setLayout(new BoxLayout(contain, BoxLayout.Y_AXIS));

		JLabel lblLogin = new JLabel("ĐĂNG NHẬP");
		lblLogin.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblLogin.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblLogin.setPreferredSize(new Dimension(158, 48));
		lblLogin.setBorder(new EmptyBorder(new Insets(20, 0, 15, 0)));
		contain.add(lblLogin);

		JLabel lblDes = new JLabel("Đăng nhập để quản lí các sản phẩm.");
		lblDes.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblDes.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDes.setPreferredSize(new Dimension(158, 48));
		lblDes.setBorder(new EmptyBorder(new Insets(0, 0, 30, 0)));
		contain.add(lblDes);

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		container.add(contain, gbc);

		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsername.setVerticalAlignment(SwingConstants.CENTER);
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblUsername.setPreferredSize(new Dimension(72, 52));

		gbc.gridx = 0;
		gbc.gridy++;
		gbc.gridwidth = 1;
		container.add(lblUsername, gbc);

		txtUsername = new JTextField();
		txtUsername.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtUsername.setPreferredSize(new Dimension(252, 36));
		gbc.gridx++;
		container.add(txtUsername, gbc);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setVerticalAlignment(SwingConstants.CENTER);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPassword.setPreferredSize(new Dimension(72, 52));
		gbc.gridx = 0;
		gbc.gridy++;
		container.add(lblPassword, gbc);

		txtPassword = new JPasswordField();
		txtPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtPassword.setPreferredSize(new Dimension(252, 37));
		gbc.gridx++;
		container.add(txtPassword, gbc);

		JButton btnRegister = new JButton("Đăng ký");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (checkNaN()) {
					Account account = new Account(txtUsername.getText(), new String(txtPassword.getPassword()));
					if (accountmanager.addAccount(account, list)) {
						JOptionPane.showMessageDialog(contentPane, "Đăng ký thành công");
						System.out.println(account);
						return;
					}
					JOptionPane.showMessageDialog(contentPane, "Đăng ký không thành công. Tài khoản đã tồn tại.");
					return;
				}
				return;

			}
		});
		btnRegister.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnRegister.setPreferredSize(new Dimension(139, 37));
		gbc.gridx = 0;
		gbc.gridy++;
		gbc.insets = new Insets(20, 0, 0, 0);
		container.add(btnRegister, gbc);

		JButton btnLogin = new JButton("Đăng nhập");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (checkNaN()) {
					Account a = new Account(txtUsername.getText().trim(), new String(txtPassword.getPassword()).trim());
					if (accountmanager.login(a, list)) {
						//JOptionPane.showMessageDialog(contentPane, "Đăng nhập thành công");
						dispose();
						menuGUI.setVisible(true);
						return;
					}
					JOptionPane.showMessageDialog(contentPane, "Đăng nhập không thành công");
					return;
				}
				return;
			}
		});
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnLogin.setPreferredSize(new Dimension(139, 37));
		gbc.gridx = 1;
		gbc.insets = new Insets(20, 20, 0, 0);
		container.add(btnLogin, gbc);
		contentPane.add(container, BorderLayout.CENTER);

	}

	public boolean checkNaN() {
		StringBuilder sb = new StringBuilder();
		if (txtUsername.getText().equals("")) {
			sb.append("Không được để trống tên tài khoản\n");
			txtUsername.setBackground(Color.red);
		} else {
			txtUsername.setBackground(Color.white);
			String password = new String(txtPassword.getPassword());
			if (password.equals("")) {
				sb.append("Không được để trống mật khẩu\n");
				txtPassword.setBackground(Color.red);
			} else {
				txtPassword.setBackground(Color.white);
			}
		}
		if (sb.length() > 0) {
			JOptionPane.showMessageDialog(contentPane, sb.toString(), "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}
}