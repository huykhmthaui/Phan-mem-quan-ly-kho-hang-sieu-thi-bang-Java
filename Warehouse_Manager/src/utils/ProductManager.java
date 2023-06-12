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

import interfaces.IProductManager;
import objects.Product;

public class ProductManager implements IProductManager {

	@Override
	public boolean addProduct(Product newProduct) {
		try {
			// đọc danh sách sản phẩm từ file
			List<Product> productList = BaseData.productInputStream();
			// Kiểm tra sản phẩm đã có trong danh sách chưa?
			if (productList.indexOf(newProduct) == -1) {
				// Thêm sản phẩm vào danh sách
				productList.add(newProduct);
				// lưu danh sách vào file
				BaseData.productOutputStream(productList, "Product.bin");
				// Trả về true sau khi thêm sản phẩm
				return true;
			}
		} catch (ClassNotFoundException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// Đã có sẵn sản phẩm, trả về false;
		return false;
	}

	@Override
	public boolean editProduct(Product m) {
		try {
			// đọc danh sách sản phẩm từ file
			List<Product> productList = BaseData.productInputStream();
			// Tìm vị trí phần tử cần sửa trong danh sách
			for (int index = 0; index < productList.size(); index++) {
				if (productList.get(index).getProduct_id().equals(m.getProduct_id())) {
					// Nếu thì thấy, gán giá trị mới
					productList.set(index, m);
					// lưu danh sách vào file
					BaseData.productOutputStream(productList, "Product.bin");
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
	public boolean delProduct(Product m) {
		// biến lưu kết quả
		boolean result = false;
		try {
			// đọc danh sách sản phẩm từ file
			List<Product> productList = BaseData.productInputStream();
			// Xóa phẩn tử trong mảng có cùng id với sản phẩm
			result = productList.removeIf(e -> e.getProduct_id().equals(m.getProduct_id()));
			// Nếu xóa thành công, lưu lại danh sách
			if (result) {
				// lưu danh sách vào file
				BaseData.productOutputStream(productList, "Product.bin");
			}
		} catch (ClassNotFoundException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return result;
	}

	@Override
	public List<Product> searchProduct(String name) {
		// Tạo mảng mới lưu kết quả tìm kiếm
		List<Product> results = new ArrayList<Product>();
		try {
			// đọc danh sách sản phẩm từ file
			List<Product> productList = BaseData.productInputStream();
			// Tìm kiếm các phần tử trong mảng
			productList.forEach((product) -> {
				if (product.getProduct_name().contains(name)) {
					// Thêm sản phẩm vào mảng kết quả nếu tìm thấy
					results.add(product);
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
	public List<Product> searchProduct(double price) {
		// Tạo mảng mới lưu kết quả tìm kiếm
		List<Product> results = new ArrayList<Product>();
		try {
			// đọc danh sách sản phẩm từ file
			List<Product> productList = BaseData.productInputStream();
			// Tìm kiếm các phần tử trong mảng
			productList.forEach((product) -> {
				if (product.getProduct_price() == price) {
					// Thêm sản phẩm vào mảng kết quả nếu tìm thấy
					results.add(product);
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
	public List<Product> searchProductByExp(int exp) {
		// Tạo mảng mới lưu kết quả tìm kiếm
		List<Product> results = new ArrayList<Product>();
		try {
			// đọc danh sách sản phẩm từ file
			List<Product> productList = BaseData.productInputStream();
			// Tìm kiếm các phần tử trong mảng
			productList.forEach((product) -> {
				if (product.getProduct_exp() == exp) {
					// Thêm sản phẩm vào mảng kết quả nếu tìm thấy
					results.add(product);
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
	public List<Product> searchProductByMfg(int mfg) {
		// Tạo mảng mới lưu kết quả tìm kiếm
		List<Product> results = new ArrayList<Product>();
		try {
			// đọc danh sách sản phẩm từ file
			List<Product> productList = BaseData.productInputStream();
			// Tìm kiếm các phần tử trong mảng
			productList.forEach((product) -> {
				if (product.getProduct_mfg() == mfg) {
					// Thêm sản phẩm vào mảng kết quả nếu tìm thấy
					results.add(product);
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
	public List<Product> searchProductByAddtime(int addTime) {
		// Tạo mảng mới lưu kết quả tìm kiếm
		List<Product> results = new ArrayList<Product>();
		try {
			// đọc danh sách sản phẩm từ file
			List<Product> productList = BaseData.productInputStream();
			// Tìm kiếm các phần tử trong mảng
			productList.forEach((product) -> {
				if (product.getProduct_addtime() == addTime) {
					// Thêm sản phẩm vào mảng kết quả nếu tìm thấy
					results.add(product);
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
	public List<Product> sortedProduct(double price) {
		// Tạo mảng lưu kết quả
		List<Product> results = new ArrayList<Product>();
		try {
			// đọc danh sách sản phẩm từ file
			List<Product> productList = BaseData.productInputStream();
			// Tìm các sản phẩm có giá nhỏ hơn price và lưu vào mảng
			productList.forEach((product) -> {
				if (product.getProduct_price() <= price) {
					results.add(product);
				}
			});
		} catch (ClassNotFoundException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// khai báo đối tượng thực hiện sắp xếp
		Comparator<Product> comparator = Comparator.comparing(Product::getProduct_price);

		// Sắp xếp mảng kết quả
		results.sort(comparator);

		return results;
	}

	

	public static void main(String[] args) {
		ProductManager manager = new ProductManager();

		// Mảng lưu trữ kết quả
		List<Product> results = new ArrayList<Product>();
		System.out.printf("%-10s%-25s%8s%10s%10s%10s%16s\n", "ID", "Tên sản phẩm", "Giá sản phẩm", "Số lượng",
				"Năm sản xuất", "Hạn sử dụng", "Năm nhập hàng");

		// Danh sách sinh ngẫu nhiên sản phẩm
		// Thêm dữ liệu vào kho
		BaseData.generateProducts(10).forEach((p) -> manager.addProduct(p));
		try {
			BaseData.productInputStream().forEach((pr) -> System.out.println(pr));
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println();
	}

}
