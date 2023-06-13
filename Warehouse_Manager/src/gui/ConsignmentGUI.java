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
import objects.Consignment;
import utils.BaseData;
import utils.ConsignmentManager;

public class ConsignmentGUI extends JPanel {

	private JList<Consignment> consignmentList;
	private ConsignmentManager manager;
	private DefaultListModel<Consignment> consignmentListModel;
	private JTextField txtID, txtName, txtPrice, txtTotal;
	private JComboBox<Integer> cbExp, cbMfg, cbAdd;

	/**
	 * Create the frame.
	 */
	public ConsignmentGUI() {
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// setResizable(true);
		// setBounds(100, 100, 1280, 800);
		// setPreferredSize(new Dimension(1280, 800));
		setLayout(new BorderLayout());
		// setContentPane(contentPane);
		// setTitle("Quan ly dien thoai");
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
		JButton btnPriceSearch = new JButton("Tìm theo giá");
		btnPriceSearch.setFont(new Font("Tahoma", Font.BOLD, 14));
		JButton btnExpSearch = new JButton("Tìm theo năm sản xuất");
		btnExpSearch.setFont(new Font("Tahoma", Font.BOLD, 14));
		JButton btnMfgSearch = new JButton("Tìm theo hạn sử dụng");
		btnMfgSearch.setFont(new Font("Tahoma", Font.BOLD, 14));
		JButton btnAddtimeSearch = new JButton("Tìm theo năm nhập hàng");
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
		manager = new ConsignmentManager();
		//
		consignmentListModel = new DefaultListModel<>();
		consignmentListModel.addElement(new Consignment());

		// Thêm dữ liệu từ file vào danh sách
		try {
			BaseData.consignmentInputStream().forEach((consignment) -> consignmentListModel.addElement(consignment));
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Tạo và cài đặt danh sách
		consignmentList = new JList<>(consignmentListModel);
		consignmentList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		consignmentList.setSelectedIndex(0);
		consignmentList.setVisibleRowCount(3);
		consignmentList.setFixedCellHeight(32);
		consignmentList.setCellRenderer(new ItemRenderer1());
		JScrollPane productScrollPane = new JScrollPane(consignmentList);
		add(productScrollPane, BorderLayout.CENTER);

		// Hành động nút thêm
		btnAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				if (checkDataInput()) {
					// Sinh ngẫu nhiên một lô hàng
					// Mobile mobile = MyFiles.generateMobile();
					Consignment consignment = new Consignment();
					consignment.setConsignment_id(txtID.getText());
					consignment.setConsignment_name(txtName.getText());
					consignment.setConsignment_price(Double.parseDouble(txtPrice.getText()));
					consignment.setConsignment_total(Integer.parseInt(txtTotal.getText()));
					consignment.setConsignment_exp((int) cbExp.getSelectedItem());
					consignment.setConsignment_mfg((int) cbMfg.getSelectedItem());
					consignment.setConsignment_addtime((int) cbAdd.getSelectedItem());
					manager.addConsignment(consignment);
					// Thêm một lô hàng vào cuối danh sách;
					consignmentListModel.addElement(consignment);
					// Xóa dữ liệu ô nhập dữ liệu.
					resetTextField();
					// Xuất dữ liệu
					exportData();
					// Chọn sản phẩm cuối cùng vừa thêm
					consignmentList.setSelectedIndex(consignmentListModel.size() - 1);
					// Cuộn đến vị trí cuối cùng
					consignmentList.ensureIndexIsVisible(consignmentListModel.size() - 1);
					// cập nhật trạng thái
					MenuGUI.statusInfoList.addElement("Thêm một lô hàng xuống cuối.");
				}
			}
		});

		// Hành động nút chỉnh sửa
		btnEdit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// Lấy vị trí lô hàng được chọn để sửa
				int index = getItemSelected();
				if (index != -1) {
					// Lấy thông tin lô hàng được chọn
					Consignment selectedConsignment = consignmentListModel.get(consignmentList.getSelectedIndex());
					// Sinh một lô hàng mới
					String id = JOptionPane.showInputDialog(ConsignmentGUI.this, "Mời nhập id lô hàng", "");
					// Nếu hủy hộp thoại thì không làm gì cả
					if (id == null || id.isEmpty()) {
						return;
					}
					String name = JOptionPane.showInputDialog(ConsignmentGUI.this, "Mời bạn nhập tên lô hàng", "");
					// Tương tự với mọi dialog
					if (name == null || name.isEmpty()) {
						return;
					}
					String price = JOptionPane.showInputDialog(ConsignmentGUI.this, "Mời bạn nhập giá lô hàng", "");
					if (price == null || price.isEmpty()) {
						return;
					}
					String total = JOptionPane.showInputDialog(ConsignmentGUI.this, "Nhập số lượng lô hàng đó", "");
					if (total == null || total.isEmpty()) {
						return;
					}
					String exp = JOptionPane.showInputDialog(ConsignmentGUI.this, "Nhập năm sản xuất", "");
					if (exp == null || exp.isEmpty()) {
						return;
					}
					String mfg = JOptionPane.showInputDialog(ConsignmentGUI.this, "Nhập hạn sử dụng", "");
					if (mfg == null || mfg.isEmpty()) {
						return;
					}
					String add = JOptionPane.showInputDialog(ConsignmentGUI.this, "Nhập năm nhập", "");
					if (add == null || add.isEmpty()) {
						return;
					}
					
					Consignment consignment = new Consignment(id, name, Double.valueOf(price),
							Integer.valueOf(total), Integer.valueOf(exp),
							Integer.valueOf(mfg), Integer.valueOf(add));
					// Gán id lô  cũ vào sản lô mới
					consignment.setConsignment_id(id);
					// Sửa thông tin lô được chọn bằng lô mới
					manager.editConsignment(consignment);
					// gán thông tin mới cho lô trong danh sách
					consignmentListModel.set(consignmentList.getSelectedIndex(), consignment);

					// Xuất dữ liệu
					exportData();
					// cập nhật trạng thái
					MenuGUI.statusInfoList.addElement("Đã sửa lô hàng được chọn.");
				}

			}
		});

		// Hành động nút xóa
		btnRemove.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// Lấy vị trí lô hàng được chọn
				int index = getItemSelected();
				// Nếu có một lô hàng được chọn
				if (index != -1) {
					// Lấy thông tin lô hàng được chọn
					Consignment selectedConsignment = consignmentListModel.get(index);
					// Xóa sản phẩm được chọn khỏi danh sách sản phẩm trong file
					if (manager.delConsignment(selectedConsignment)) {
						// Xóa sản phầm được chọn khỏi danh sách
						consignmentListModel.remove(consignmentList.getSelectedIndex());
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
				
				// Hiển thị hộp thoại nhập giá lô hàng đầu vào
				String value = JOptionPane.showInputDialog(ConsignmentGUI.this, "Nhập giá của lô hàng", "1000");

				// Nếu người dùng hủy hộp thoại thì không thực hiện sắp xếp
				if (value == null) {
					return;
				}
				
				// Xóa danh sách sản phẩm cũ trong danh sách
				consignmentListModel.clear();
				consignmentListModel.addElement(new Consignment());
				
				// Sắp xếp danh sách lô
				// Gán danh sách lô hàng sau sắp xếp
				List<Consignment> temp = manager.sortedConsignment(Double.valueOf(value));
				for(Consignment i: temp) {
					consignmentListModel.addElement(i);
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
				consignmentListModel.clear();
				consignmentListModel.addElement(new Consignment());
				// Thêm dữ liệu từ file vào danh sách
				try {
					BaseData.consignmentInputStream().forEach((consignment) -> consignmentListModel.addElement(consignment));
					// Xuất dữ liệu
					exportData();
					// cập nhật trạng thái
					MenuGUI.statusInfoList.addElement("Đã hiển thị tất cả các lô hàng hiện có.");
				} catch (ClassNotFoundException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		// Thêm lắng nghe hành động nút tìm kiếm theo lô hàng
		btnSearch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				// Hiển thị hộp thoại nhập tên lô hàng muốn tìm kiếm
				String value = JOptionPane.showInputDialog(ConsignmentGUI.this, "Nhập tên lô hàng", "");
				// Kiểm tra nếu hủy hộp thoại thì không làm gì.
				if (value == null || value.isEmpty()) {
					return;
				}
				// Xóa danh sách lô hàng cũ trong danh sách
				consignmentListModel.clear();
				consignmentListModel.addElement(new Consignment());
				// Gán danh sách sản phẩm sau khi tìm kiếm theo tên
				List<Consignment> temp = manager.searchConsignment(value);
				for(Consignment i: temp) {
					consignmentListModel.addElement(i);
				}
				// Xuất dữ liệu
				exportData();
				// cập nhật trạng thái
				MenuGUI.statusInfoList
						.addElement("Đã tìm thất tất cả " + (consignmentListModel.size() - 1) + "lô hàng theo tên.");
			}
		});

		// Thêm lắng nghe hành động nút tìm kiếm theo giá sản phẩm
		btnPriceSearch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				// Hiển thị hộp thoại nhập tên sản phẩm tìm kiếm
				String value = JOptionPane.showInputDialog(ConsignmentGUI.this, "Nhập giá lô hàng", "0");
				// Kiểm tra nếu hủy hộp thoại thì không làm gì.
				if (value == null || value.isEmpty()) {
					return;
				}
				// Xóa danh sách lô hàng cũ trong danh sách
				consignmentListModel.clear();
				consignmentListModel.addElement(new Consignment());
				// Sắp xếp danh sách lô hàng
				// Gán danh sách lô hàng sau khi tìm kiếm theo giá 
				List<Consignment> temp = manager.searchConsignmentByPrice(Double.valueOf(value));
				for(Consignment i: temp) {
					consignmentListModel.addElement(i);
				}
				// Xuất dữ liệu
				exportData();
				// cập nhật trạng thái
				MenuGUI.statusInfoList
						.addElement("Đã tìm thất tất cả " + (consignmentListModel.size() - 1) + " sản phẩm theo giá.");
			}
		});

		// Thêm lắng nghe hành động nút tìm kiếm theo năm sản xuất
		btnExpSearch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				// Hiển thị hộp thoại nhập năm sản xuất tìm kiếm
				String value = JOptionPane.showInputDialog(ConsignmentGUI.this, "Nhập năm sản xuất", "0");
				// Kiểm tra nếu hủy hộp thoại thì không làm gì.
				if (value == null || value.isEmpty()) {
					return;
				}
				// Xóa danh sách lô hàng cũ trong danh sách
				consignmentListModel.clear();
				consignmentListModel.addElement(new Consignment());
				// Sắp xếp danh sách lô hàng
				// Gán danh sách lô hàng sau khi tìm kiếm theo năm sản xuất
				List<Consignment> temp = manager.searchConsignmentByExp((int)Integer.valueOf(value));
				for(Consignment i: temp) {
					consignmentListModel.addElement(i);
				}
				// Xuất dữ liệu
				exportData();
				// cập nhật trạng thái
				MenuGUI.statusInfoList.addElement(
						"Đã tìm thất tất cả " + (consignmentListModel.size() - 1) + " sản phẩm theo năm sản xuất.");
			}
		});

		// Thêm lắng nghe hành động nút tìm kiếm theo hạn sử dụng
		btnMfgSearch.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				// Hiển thị hộp thoại nhập hạn sử dụng tìm kiếm
				String value = JOptionPane.showInputDialog(ConsignmentGUI.this, "Nhập hạn sử dụng", "0");
				// Kiểm tra nếu hủy hộp thoại thì không làm gì.
				if (value == null || value.isEmpty()) {
					return;
				}
				// Xóa danh sách sản phẩm cũ trong danh sách
				consignmentListModel.clear();
				consignmentListModel.addElement(new Consignment());
				// Sắp xếp danh sách lô hàng
				// Gán danh sách lô hàng sau khi tìm kiếm theo hạn sử dụng
				List<Consignment> temp = manager.searchConsignmentByMfg(Integer.valueOf(value));
				for(Consignment i: temp) {
					consignmentListModel.addElement(i);
				}
				// Xuất dữ liệu
				exportData();
				// cập nhật trạng thái
				MenuGUI.statusInfoList.addElement(
						"Đã tìm thất tất cả " + (consignmentListModel.size() - 1) + " lô hàng theo hạn sử dụng.");
			}
		});

		// Thêm lắng nghe hành động nút tìm kiếm theo năm nhập hàng
		btnAddtimeSearch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				// Hiển thị hộp thoại nhập năm nhập hàng tìm kiếm
				String value = JOptionPane.showInputDialog(ConsignmentGUI.this, "Nhập năm nhập lô", "0");
				// Kiểm tra nếu hủy hộp thoại thì không làm gì.
				if (value == null || value.isEmpty()) {
					return;
				}
				// Xóa danh sách sản phẩm cũ trong danh sách
				consignmentListModel.clear();
				consignmentListModel.addElement(new Consignment());
				// Sắp xếp danh sách lô hàng
				// Gán danh sách lô hàng sau khi tìm kiếm theo năm nhập
				List<Consignment> temp = manager.searchConsignmentByAddtime(Integer.valueOf(value));
				for(Consignment i: temp) {
					consignmentListModel.addElement(i);
				}
				// Xuất dữ liệu
				exportData();
				// cập nhật trạng thái
				MenuGUI.statusInfoList.addElement(
						"Đã tìm thất tất cả " + (consignmentListModel.size() - 1) + " lô hàng theo ngày nhập hàng.");
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
			add(new JLabel("Tên lô hàng:"), gbc);
			gbc.gridx += 2;
			add(new JLabel("Giá lô hàng:"), gbc);
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
			showMessage("Nhập id lô hàng");
			return false;
		}
		if (isIDExists(id)) {
			showMessage("ID lô hàng đã tồn tại.");
			return false;
		}
		if (name.isEmpty()) {
			showMessage("Nhập tên lô hàng.");
			return false;
		}
		if (price.isEmpty()) {
			showMessage("Nhập giá lô.");
			return false;
		}
		try {
			Double.parseDouble(price);
		} catch (NumberFormatException e) {
			showMessage("Giá lô phải là số.");
			return false;
		}
		if (total.isEmpty()) {
			showMessage("Nhập số lượng lô hiện có.");
			return false;
		}
		try {
			Integer.parseInt(total);
		} catch (NumberFormatException e) {
			showMessage("Nhập số lượng lô phải là số nguyên.");
			return false;
		}
		return true;
	}

	// Lấy và kiểm tra lô hàng được chọn
	private int getItemSelected() {
		int index = consignmentList.getSelectedIndex();
		// Nếu chưa có lô hàng nào được chọn thì hiển thị thông báo
		if (index <= 0) {
			JOptionPane.showMessageDialog(ConsignmentGUI.this, "Vui lòng chọn một lô hàng.", "Warning",
					JOptionPane.WARNING_MESSAGE);
		}
		return index;
	}

	// xuất báo cáo ra file report.bin
	private void exportData() {
		List<Consignment> exportList = new ArrayList<Consignment>();
		for (int index = 1; index < consignmentListModel.size(); index++) {
			exportList.add(consignmentListModel.getElementAt(index));
		}
		try {
			BaseData.exportStreamConsignment(exportList, "Report.bin");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private boolean isIDExists(String id) {
		for (int index = 0; index < consignmentListModel.size(); index++) {
			if (consignmentListModel.get(index).getConsignment_id().equals(id)) {
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
		JOptionPane.showMessageDialog(ConsignmentGUI.this, message, "Thông báo", JOptionPane.WARNING_MESSAGE);
	}
}

