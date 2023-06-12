package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import interfaces.IConsignmentManager;
import objects.Consignment;

public class ConsignmentManager implements IConsignmentManager {

	@Override
	public boolean addConsignment(Consignment newConsignment) {
		try {
			// đọc danh sách lô hàng từ file
			List<Consignment> consignmentList = BaseData.consignmentInputStream();
			// Kiểm tra lô hàng đã có trong danh sách chưa?
			if (consignmentList.indexOf(newConsignment) == -1) {
				// Thêm lô hàng vào danh sách
				consignmentList.add(newConsignment);
				// lưu danh sách vào file
				BaseData.consignmentOutputStream(consignmentList, "Consignment.bin");
				// Trả về true sau khi thêm lô hàng
				return true;
			}
		} catch (ClassNotFoundException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// Đã có sẵn lô hàng, trả về false;
		return false;
	}

	@Override
	public boolean editConsignment(Consignment m) {
		try {
			// đọc danh sách lô hàng từ file
			List<Consignment> consignmentList = BaseData.consignmentInputStream();
			// Tìm vị trí phần tử cần sửa trong danh sách
			for (int index = 0; index < consignmentList.size(); index++) {
				if (consignmentList.get(index).getConsignment_id().equals(m.getConsignment_id())) {
					// Nếu thì thấy, gán giá trị mới
					consignmentList.set(index, m);
					// lưu danh sách vào file
					BaseData.consignmentOutputStream(consignmentList, "Consignment.bin");
					// Trả về kết quả true;
					return true;
				}
			}
		} catch (ClassNotFoundException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// Nếu không tìm thấy, trả về false;
		return false;
	}

	@Override
	public boolean delConsignment(Consignment m) {
		// biến lưu kết quả
		boolean result = false;
		try {
			// đọc danh sách lô hàng từ file
			List<Consignment> consignmentList = BaseData.consignmentInputStream();
			// Xóa phẩn tử trong mảng có cùng id với lô hàng
			result = consignmentList.removeIf(e -> e.getConsignment_id().equals(m.getConsignment_id()));
			// Nếu xóa thành công, lưu lại danh sách
			if (result) {
				// lưu danh sách vào file
				BaseData.consignmentOutputStream(consignmentList, "Consignment.bin");
			}
		} catch (ClassNotFoundException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return result;
	}

	@Override
	public List<Consignment> searchConsignment(String name) {
		// Tạo mảng mới lưu kết quả tìm kiếm
		List<Consignment> results = new ArrayList<Consignment>();
		try {
			// đọc danh sách lô hàng từ file
			List<Consignment> consignmentList = BaseData.consignmentInputStream();
			// Tìm kiếm các phần tử trong mảng
			consignmentList.forEach((consignment) -> {
				if (consignment.getConsignment_name().contains(name)) {
					// Thêm sản phẩm vào mảng kết quả nếu tìm thấy
					results.add(consignment);
				}
			});
		} catch (ClassNotFoundException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// Trả về mảng kết quả
		return results;
	}

	@Override
	public List<Consignment> searchConsignmentByPrice(double price) {
		// Tạo mảng mới lưu kết quả tìm kiếm
		List<Consignment> results = new ArrayList<Consignment>();
		try {
			// đọc danh sách lô hàng từ file
			List<Consignment> consignmentList = BaseData.consignmentInputStream();
			// Tìm kiếm các phần tử trong mảng
			consignmentList.forEach((consignment) -> {
				if (consignment.getConsignment_price() == price) {
					// Thêm lô hàng vào mảng kết quả nếu tìm thấy
					results.add(consignment);
				}
			});
		} catch (ClassNotFoundException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// Trả về mảng kết quả
		return results;
	}

	@Override
	public List<Consignment> searchConsignmentByExp(int exp) {
		// Tạo mảng mới lưu kết quả tìm kiếm
		List<Consignment> results = new ArrayList<Consignment>();
		try {
			// đọc danh sách lô hàng từ file
			List<Consignment> consignmentList = BaseData.consignmentInputStream();
			// Tìm kiếm các phần tử trong mảng
			consignmentList.forEach((consignment) -> {
				if (consignment.getConsignment_exp() == exp) {
					// Thêm lô hàng vào mảng kết quả nếu tìm thấy
					results.add(consignment);
				}
			});
		} catch (ClassNotFoundException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// Trả về mảng kết quả
		return results;
	}

	@Override
	public List<Consignment> searchConsignmentByMfg(int mfg) {
		// Tạo mảng mới lưu kết quả tìm kiếm
		List<Consignment> results = new ArrayList<Consignment>();
		try {
			// đọc danh sách lô hàng từ file
			List<Consignment> consignmentList = BaseData.consignmentInputStream();
			// Tìm kiếm các phần tử trong mảng
			consignmentList.forEach((consignment) -> {
				if (consignment.getConsignment_mfg() == mfg) {
					// Thêm sản phẩm vào mảng kết quả nếu tìm thấy
					results.add(consignment);
				}
			});
		} catch (ClassNotFoundException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// Trả về mảng kết quả
		return results;
	}

	@Override
	public List<Consignment> searchConsignmentByAddtime(int addTime) {
		// Tạo mảng mới lưu kết quả tìm kiếm
		List<Consignment> results = new ArrayList<Consignment>();
		try {
			// đọc danh lô hàng phẩm từ file
			List<Consignment> consignmentList = BaseData.consignmentInputStream();
			// Tìm kiếm các phần tử trong mảng
			consignmentList.forEach((consignment) -> {
				if (consignment.getConsignment_addtime() == addTime) {
					// Thêm sản phẩm vào mảng kết quả nếu tìm thấy
					results.add(consignment);
				}
			});
		} catch (ClassNotFoundException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// Trả về mảng kết quả
		return results;
	}

	@Override
	public List<Consignment> sortedConsignment(double price) {
		// Tạo mảng lưu kết quả
		List<Consignment> results = new ArrayList<Consignment>();
		try {
			// đọc danh sách sản phẩm từ file
			List<Consignment> consignmentList = BaseData.consignmentInputStream();
			// Tìm các lô có giá nhỏ hơn price và lưu vào mảng
			consignmentList.forEach((consignment) -> {
				if (consignment.getConsignment_price() <= price) {
					results.add(consignment);
				}
			});
		} catch (ClassNotFoundException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// khai báo đối tượng thực hiện sắp xếp
		Comparator<Consignment> comparator = Comparator.comparing(Consignment::getConsignment_price);

		// Sắp xếp mảng kết quả
		results.sort(comparator);

		return results;
	}

	

	public static void main(String[] args) {
		ConsignmentManager manager = new ConsignmentManager();

		// Mảng lưu trữ kết quả
		List<Consignment> results = new ArrayList<Consignment>();
		System.out.printf("%-10s%-25s%8s%10s%10s%10s%16s\n", "ID", "Tên lô", "Giá lô", "Số lượng",
				"Năm sản xuất", "Hạn sử dụng", "Năm nhập hàng");

		// Danh sách sinh ngẫu nhiên sản phẩm
		// Thêm dữ liệu vào kho
		BaseData.generateConsignment(10).forEach((p) -> manager.addConsignment(p));
		try {
			BaseData.consignmentInputStream().forEach((pr) -> System.out.println(pr));
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println();
	}

}
