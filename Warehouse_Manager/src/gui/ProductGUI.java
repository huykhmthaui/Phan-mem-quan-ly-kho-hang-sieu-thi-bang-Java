package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import objects.Account;
import objects.Product;
import utils.ProductManager;
import utils.BaseData;

public class ProductGUI extends JPanel {

	private JList<Product> productList;
	private ProductManager manager;
	private DefaultListModel<Product> productListModel;
	private JTextField txtID, txtName, txtPrice, txtTotal;
	private JComboBox<Integer> cbExp, cbMfg, cbAdd;

	/**
	 * Create the frame.
	 */
	public ProductGUI() {
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// setResizable(true);
		// setBounds(100, 100, 1280, 800);
		// setPreferredSize(new Dimension(1280, 800));
		setLayout(new BorderLayout());
		// setContentPane(contentPane);
		setLayout(new BorderLayout());

		JPanel inputPanel = new JPanel(new BorderLayout());
		JPanel menuPanel = new JPanel(new BorderLayout());
		JPanel topPanel = new JPanel(new FlowLayout());
		JButton btnAdd = new JButton("Thêm");
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 14));
		JButton btnEdit = new JButton("Sửa");
		btnEdit.setFont(new Font("Tahoma", Font.BOLD, 14));
		JButton btnRemove = new JButton("Xóa");
		btnRemove.setFont(new Font("Tahoma", Font.BOLD, 14));
		JButton btnSearch = new JButton("Tìm kiếm");
		btnSearch.setFont(new Font("Tahoma", Font.BOLD, 14));
		JButton btnSort = new JButton("Sắp xếp");
		btnSort.setFont(new Font("Tahoma", Font.BOLD, 14));
		JButton btnShowAll = new JButton("Hiển thị tất cả");
		btnShowAll.setFont(new Font("Tahoma", Font.BOLD, 14));

		topPanel.add(btnAdd);
		topPanel.add(btnEdit);
		topPanel.add(btnRemove);
		topPanel.add(btnSearch);
		topPanel.add(btnSort);
		topPanel.add(btnShowAll);

		JPanel bottomPanel = new JPanel(new FlowLayout());
		inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
		JButton btnPriceSearch = new JButton("Tìm kiếm theo giá");
		btnPriceSearch.setFont(new Font("Tahoma", Font.BOLD, 14));
		JButton btnExpSearch = new JButton("Tìm kiếm theo năm sản xuất");
		btnExpSearch.setFont(new Font("Tahoma", Font.BOLD, 14));
		JButton btnMfgSearch = new JButton("Tìm kiếm theo hạn sử dụng");
		btnMfgSearch.setFont(new Font("Tahoma", Font.BOLD, 14));
		JButton btnAddtimeSearch = new JButton("Tìm kiếm theo năm nhập hàng");
		btnAddtimeSearch.setFont(new Font("Tahoma", Font.BOLD, 14));

		bottomPanel.add(btnPriceSearch);
		bottomPanel.add(btnExpSearch);
		bottomPanel.add(btnMfgSearch);
		bottomPanel.add(btnAddtimeSearch);
		menuPanel.add(topPanel, BorderLayout.NORTH);
		menuPanel.add(bottomPanel, BorderLayout.SOUTH);
		inputPanel.add(new InputDataPanel(), BorderLayout.NORTH);
		inputPanel.add(menuPanel, BorderLayout.SOUTH);
		add(inputPanel, BorderLayout.NORTH);

		// Khởi tạo biến quản lý sản phẩm
		manager = new ProductManager();
		//
		productListModel = new DefaultListModel<>();
		productListModel.addElement(new Product());

		// Thêm dữ liệu từ file vào danh sách
		try {
			BaseData.productInputStream().forEach((product) -> productListModel.addElement(product));
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Tạo và cài đặt danh sách
		productList = new JList<>(productListModel);
		productList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		productList.setSelectedIndex(0);
		productList.setVisibleRowCount(3);
		productList.setFixedCellHeight(32);
		productList.setCellRenderer(new ItemRenderer());
		JScrollPane productScrollPane = new JScrollPane(productList);
		add(productScrollPane, BorderLayout.CENTER);

		// Hành động nút thêm
		btnAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				if (checkDataInput()) {
					// Sinh ngẫu nhiên một sản phẩm
					// Mobile mobile = MyFiles.generateMobile();
					Product product = new Product();
					product.setProduct_id(txtID.getText());
					product.setProduct_name(txtName.getText());
					product.setProduct_price(Double.parseDouble(txtPrice.getText()));
					product.setProduct_total(Integer.parseInt(txtTotal.getText()));
					product.setProduct_exp((int) cbExp.getSelectedItem());
					product.setProduct_mfg((int) cbMfg.getSelectedItem());
					product.setProduct_addtime((int) cbAdd.getSelectedItem());
					manager.addProduct(product);
					// Thêm một sản phẩm vào cuối danh sách;
					productListModel.addElement(product);
					// Xóa dữ liệu ô nhập dữ liệu.
					resetTextField();
					// Xuất dữ liệu
					exportData();
					// Chọn sản phẩm cuối cùng vừa thêm
					productList.setSelectedIndex(productListModel.size() - 1);
					// Cuộn đến vị trí cuối cùng
					productList.ensureIndexIsVisible(productListModel.size() - 1);
					// cập nhật trạng thái
					MenuGUI.statusInfoList.addElement("Thêm một sản phẩm xuống cuối.");
				}
			}
		});

		// Hành động nút chỉnh sửa
		btnEdit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// Lấy vị trí sản phẩm được chọn để sửa
				int index = getItemSelected();
				if (index != -1) {
					// Lấy thông tin sản phẩm được chọn
					Product selectedProduct = productListModel.get(productList.getSelectedIndex());
					// Sinh một sản phẩm mới
					String id = JOptionPane.showInputDialog(ProductGUI.this, "Nhập id sản phẩm", "");
					// Nếu hủy hộp thoại thì không làm gì cả
					if (id == null || id.isEmpty()) {
						return;
					}
					String name = JOptionPane.showInputDialog(ProductGUI.this, "Nhập tên sản phẩm", "");
					// Tương tự với mọi dialog
					if (name == null || name.isEmpty()) {
						return;
					}
					String price = JOptionPane.showInputDialog(ProductGUI.this, "Nhập giá sản phẩm", "");
					if (price == null || price.isEmpty()) {
						return;
					}
					String total = JOptionPane.showInputDialog(ProductGUI.this, "Nhập số lượng sản phẩm", "");
					if (total == null || total.isEmpty()) {
						return;
					}
					String exp = JOptionPane.showInputDialog(ProductGUI.this, "Nhập năm sản xuất", "");
					if (exp == null || exp.isEmpty()) {
						return;
					}
					String mfg = JOptionPane.showInputDialog(ProductGUI.this, "Nhập hạn sử dụng", "");
					if (mfg == null || mfg.isEmpty()) {
						return;
					}
					String add = JOptionPane.showInputDialog(ProductGUI.this, "Nhập năm nhập hàng", "");
					if (add == null || add.isEmpty()) {
						return;
					}
					
					Product product = new Product(id, name, Double.valueOf(price),
							Integer.valueOf(total), Integer.valueOf(exp),
							Integer.valueOf(mfg), Integer.valueOf(add));
					// Gán id sản phẩm cũ vào sản phẩm mới
					product.setProduct_id(id);
					// Sửa thông tin sản phẩm được chọn bằng sản phẩm mới
					manager.editProduct(product);
					// gán thông tin mới cho sản phẩm trong danh sách
					productListModel.set(productList.getSelectedIndex(), product);

					// Xuất dữ liệu
					exportData();
					// cập nhật trạng thái
					MenuGUI.statusInfoList.addElement("Đã sửa sản phẩm được chọn.");
				}

			}
		});

		// Hành động nút xóa
		btnRemove.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// Lấy vị trí sản phẩm được chọn
				int index = getItemSelected();
				// Nếu có một sản phẩm được chọn
				if (index != -1) {
					// Lấy thông tin sản phẩm được chọn
					Product selectedProduct = productListModel.get(index);
					// Xóa sản phẩm được chọn khỏi danh sách sản phẩm trong file
					if (manager.delProduct(selectedProduct)) {
						// Xóa sản phầm được chọn khỏi danh sách
						productListModel.remove(productList.getSelectedIndex());
						// Xuất dữ liệu
						exportData();
						// cập nhật trạng thái
						MenuGUI.statusInfoList.addElement("Đã xóa một sản phẩm được chọn.");
					}
				}
			}
		});

		// hành động nút sắp xếp theo giá tiền
		btnSort.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				// Hiển thị hộp thoại nhập giá sản phẩm đầu vào
				String value = JOptionPane.showInputDialog(ProductGUI.this, "Nhập giá sản phẩm", "1000");

				// Nếu người dùng hủy hộp thoại thì không thực hiện sắp xếp
				if (value == null) {
					return;
				}
				
				// Xóa danh sách sản phẩm cũ trong danh sách
				productListModel.clear();
				productListModel.addElement(new Product());
				
				// Sắp xếp danh sách sản phẩm
				// Gán danh sách sản phẩm sau sắp xếp
				List<Product> temp = manager.sortedProduct(Double.valueOf(value));
				for(Product i: temp) {
					productListModel.addElement(i);
				}
				// Xuất dữ liệu
				exportData();
				// cập nhật trạng thái
				MenuGUI.statusInfoList.addElement("Dữ liệu sản phẩm đã được sắp.");
			}
		});

		// Thêm lắng nghe hành động nút hiển thị tất cả dữ liệu trong file
		btnShowAll.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent er) {
				// TODO Auto-generated method stub
				// Xóa dữ liệu trong file cho danh sách
				productListModel.clear();
				productListModel.addElement(new Product());
				// Thêm dữ liệu từ file vào danh sách
				try {
					BaseData.productInputStream().forEach((product) -> productListModel.addElement(product));
					// Xuất dữ liệu
					exportData();
					// cập nhật trạng thái
					MenuGUI.statusInfoList.addElement("Đã hiển thị tất cả sản phẩm.");
				} catch (ClassNotFoundException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		// Thêm lắng nghe hành động nút tìm kiếm theo tên sản phẩm
		btnSearch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				// Hiển thị hộp thoại nhập tên sản phẩm tìm kiếm
				String value = JOptionPane.showInputDialog(ProductGUI.this, "Nhập tên sản phẩm", "");
				// Kiểm tra nếu hủy hộp thoại thì không làm gì.
				if (value == null || value.isEmpty()) {
					return;
				}
				// Xóa danh sách sản phẩm cũ trong danh sách
				productListModel.clear();
				productListModel.addElement(new Product());
				// Gán danh sách sản phẩm sau khi tìm kiếm theo tên
				List<Product> temp = manager.searchProduct(value);
				for(Product i: temp) {
					productListModel.addElement(i);
				}
				// Xuất dữ liệu
				exportData();
				// cập nhật trạng thái
				MenuGUI.statusInfoList
						.addElement("Đã tìm thất tất cả " + (productListModel.size() - 1) + "sản phẩm theo tên.");
			}
		});

		// Thêm lắng nghe hành động nút tìm kiếm theo giá sản phẩm
		btnPriceSearch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				// Hiển thị hộp thoại nhập tên sản phẩm tìm kiếm
				String value = JOptionPane.showInputDialog(ProductGUI.this, "Nhập giá sản phẩm", "0");
				// Kiểm tra nếu hủy hộp thoại thì không làm gì.
				if (value == null || value.isEmpty()) {
					return;
				}
				// Xóa danh sách sản phẩm cũ trong danh sách
				productListModel.clear();
				productListModel.addElement(new Product());
				// Sắp xếp danh sách sản phẩm
				// Gán danh sách sản phẩm sau khi tìm kiếm theo giá sản phẩm
				List<Product> temp = manager.searchProduct(Double.valueOf(value));
				for(Product i: temp) {
					productListModel.addElement(i);
				}
				// Xuất dữ liệu
				exportData();
				// cập nhật trạng thái
				MenuGUI.statusInfoList
						.addElement("Đã tìm thất tất cả " + (productListModel.size() - 1) + " sản phẩm theo giá.");
			}
		});

		// Thêm lắng nghe hành động nút tìm kiếm theo năm sản xuất
		btnExpSearch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				// Hiển thị hộp thoại nhập năm sản xuất tìm kiếm
				String value = JOptionPane.showInputDialog(ProductGUI.this, "Nhập năm sản xuất", "0");
				// Kiểm tra nếu hủy hộp thoại thì không làm gì.
				if (value == null || value.isEmpty()) {
					return;
				}
				// Xóa danh sách sản phẩm cũ trong danh sách
				productListModel.clear();
				productListModel.addElement(new Product());
				// Sắp xếp danh sách sản phẩm
				// Gán danh sách sản phẩm sau khi tìm kiếm theo năm sản xuất
				List<Product> temp = manager.searchProductByExp((int)Integer.valueOf(value));
				for(Product i: temp) {
					productListModel.addElement(i);
				}
				// Xuất dữ liệu
				exportData();
				// cập nhật trạng thái
				MenuGUI.statusInfoList.addElement(
						"Đã tìm thất tất cả " + (productListModel.size() - 1) + " sản phẩm theo năm sản xuất.");
			}
		});

		// Thêm lắng nghe hành động nút tìm kiếm theo hạn sử dụng
		btnMfgSearch.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				// Hiển thị hộp thoại nhập hạn sử dụng tìm kiếm
				String value = JOptionPane.showInputDialog(ProductGUI.this, "Nhập hạn sử dụng", "0");
				// Kiểm tra nếu hủy hộp thoại thì không làm gì.
				if (value == null || value.isEmpty()) {
					return;
				}
				// Xóa danh sách sản phẩm cũ trong danh sách
				productListModel.clear();
				productListModel.addElement(new Product());
				// Sắp xếp danh sách sản phẩm
				// Gán danh sách sản phẩm sau khi tìm kiếm theo hạn sử dụng
				List<Product> temp = manager.searchProductByMfg(Integer.valueOf(value));
				for(Product i: temp) {
					productListModel.addElement(i);
				}
				// Xuất dữ liệu
				exportData();
				// cập nhật trạng thái
				MenuGUI.statusInfoList.addElement(
						"Đã tìm thất tất cả " + (productListModel.size() - 1) + " sản phẩm theo hạn sử dụng.");
			}
		});

		// Thêm lắng nghe hành động nút tìm kiếm theo năm nhập hàng
		btnAddtimeSearch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				// Hiển thị hộp thoại nhập năm nhập hàng tìm kiếm
				String value = JOptionPane.showInputDialog(ProductGUI.this, "Nhập năm nhập hàng", "0");
				// Kiểm tra nếu hủy hộp thoại thì không làm gì.
				if (value == null || value.isEmpty()) {
					return;
				}
				// Xóa danh sách sản phẩm cũ trong danh sách
				productListModel.clear();
				productListModel.addElement(new Product());
				// Sắp xếp danh sách sản phẩm
				// Gán danh sách sản phẩm sau khi tìm kiếm theo năm nhập hàng
				List<Product> temp = manager.searchProductByAddtime(Integer.valueOf(value));
				for(Product i: temp) {
					productListModel.addElement(i);
				}
				// Xuất dữ liệu
				exportData();
				// cập nhật trạng thái
				MenuGUI.statusInfoList.addElement(
						"Đã tìm thất tất cả " + (productListModel.size() - 1) + " sản phẩm theo ngày nhập hàng.");
			}
		});
	}

	public class InputDataPanel extends JPanel {
		public InputDataPanel() {
			setBorder(BorderFactory.createEmptyBorder(20, 50, 0, 50));
			setLayout(new GridBagLayout());
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.gridx = 0;
			gbc.gridy = 0;
			gbc.insets = new Insets(5, 5, 5, 5);
			gbc.anchor = GridBagConstraints.EAST;
			add(new JLabel("ID:"), gbc);
			gbc.gridx += 2;
			add(new JLabel("Tên sản phẩm:"), gbc);
			gbc.gridx += 2;
			add(new JLabel("Giá sản phẩm:"), gbc);
			gbc.gridx += 2;
			add(new JLabel("Số lượng:"), gbc);
			gbc.gridy++;
			gbc.gridx = 0;
			add(new JLabel("Năm sản xuất:"), gbc);
			gbc.gridx += 2;
			add(new JLabel("Hạn sử dụng:"), gbc);
			gbc.gridx += 2;
			add(new JLabel("Ngày nhập:"), gbc);
			//
			gbc.gridx = 1;
			gbc.gridy = 0;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.anchor = GridBagConstraints.WEST;
			gbc.weightx = 0.5;
			txtID = new JTextField(10);
			txtID.setPreferredSize(new Dimension(100, 30));
			add(txtID, gbc);
			gbc.gridx += 2;
			txtName = new JTextField(10);
			txtName.setPreferredSize(new Dimension(100, 30));
			add(txtName, gbc);
			gbc.gridx += 2;
			txtPrice = new JTextField(10);
			txtPrice.setPreferredSize(new Dimension(100, 30));
			add(txtPrice, gbc);
			gbc.gridx += 2;
			txtTotal = new JTextField(10);
			txtTotal.setPreferredSize(new Dimension(100, 30));
			add(txtTotal, gbc);
			//
			gbc.gridy++;
			gbc.gridx = 1;
			cbExp = new JComboBox<Integer>(new Integer[] { 2020, 2021, 2022, 2023 });
			cbExp.setPreferredSize(new Dimension(50, 30));
			add(cbExp, gbc);
			gbc.gridx += 2;
			
			cbMfg = new JComboBox<Integer>(new Integer[] { 2020, 2021, 2022, 2023, 2024 });
			cbMfg.setPreferredSize(new Dimension(50, 30));
			add(cbMfg, gbc);
			gbc.gridx += 2;
			
			cbAdd = new JComboBox<Integer>(new Integer[] { 2020, 2021, 2022, 2023 });
			cbAdd.setPreferredSize(new Dimension(50, 30));
			add(cbAdd, gbc);
		}
	}

	private boolean checkDataInput() {
		String id = txtID.getText();
		String name = txtName.getText();
		String price = txtPrice.getText();
		String total = txtTotal.getText();
		if (id.isEmpty()) {
			showMessage("Nhập id sản phẩm");
			return false;
		}
		if (isIDExists(id)) {
			showMessage("ID sản phẩm đã tồn tại.");
			return false;
		}
		if (name.isEmpty()) {
			showMessage("Nhập tên sản phẩm.");
			return false;
		}
		if (price.isEmpty()) {
			showMessage("Nhập giá sản phẩm.");
			return false;
		}
		try {
			Double.parseDouble(price);
		} catch (NumberFormatException e) {
			showMessage("Nhập giá sản phẩm là số.");
			return false;
		}
		if (total.isEmpty()) {
			showMessage("Nhập số lượng sản phẩm.");
			return false;
		}
		try {
			Integer.parseInt(total);
		} catch (NumberFormatException e) {
			showMessage("Nhập số lượng sản phẩm là số nguyên.");
			return false;
		}
		return true;
	}

	// Lấy và kiểm tra sản phẩm được chọn
	private int getItemSelected() {
		int index = productList.getSelectedIndex();
		// Nếu chưa có sản phẩm nào được chọn thì hiển thị thông báo
		if (index <= 0) {
			JOptionPane.showMessageDialog(ProductGUI.this, "Vui lòng chọn một dòng sản phẩm.", "Warning",
					JOptionPane.WARNING_MESSAGE);
		}
		return index;
	}

	// xuất báo cáo ra file report.bin
	private void exportData() {
		List<Product> exportList = new ArrayList<Product>();
		for (int index = 1; index < productListModel.size(); index++) {
			exportList.add(productListModel.getElementAt(index));
		}
		try {
			BaseData.exportStream(exportList, "Report.bin");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private boolean isIDExists(String id) {
		for (int index = 0; index < productListModel.size(); index++) {
			if (productListModel.get(index).getProduct_id().equals(id)) {
				return true;
			}
		}
		return false;
	}
	
	private void resetTextField() {
		txtID.setText("");
		txtName.setText("");
		txtPrice.setText("");
		txtTotal.setText("");
	}

	private void showMessage(String message) {
		JOptionPane.showMessageDialog(ProductGUI.this, message, "Thông báo", JOptionPane.WARNING_MESSAGE);
	}
}
