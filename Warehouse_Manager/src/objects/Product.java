package objects;

import java.io.Serializable;
import java.util.Comparator;

public class Product implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public static String PRODUCT_ID = "No id";
	public static String PRODUCT_NAME = "No name";
	public static double PRODUCT_PRICE = 0.0;
	public static int PRODUCT_TOTAL = 0;
	public static int PRODUCT_EXP = 0;
	public static int PRODUCT_MFG = 0;
	public static int PRODUCT_ADDTIME = 0;
	
	private String product_id;
	private String product_name;
	private double product_price;
	private int product_total;
	private int product_exp;
	private int product_mfg;
	private int product_addtime;
	
	public Product() {
		this(Product.PRODUCT_ID, Product.PRODUCT_NAME, Product.PRODUCT_PRICE, Product.PRODUCT_EXP, Product.PRODUCT_MFG,
				Product.PRODUCT_TOTAL, Product.PRODUCT_ADDTIME);
	}
	
	public Product(String product_id, String product_name, double product_price, int product_total,
			int product_exp, int product_mfg, int product_addtime) {
		super();
		this.product_id = product_id;
		this.product_name = product_name;
		this.product_price = product_price;
		this.product_total = product_total;
		this.product_exp = product_exp;
		this.product_mfg = product_mfg;
		this.product_addtime = product_addtime;
	}


	public String getProduct_id() {
		return product_id;
	}


	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}


	public String getProduct_name() {
		return product_name;
	}


	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}


	public double getProduct_price() {
		return product_price;
	}


	public void setProduct_price(double product_price) {
		this.product_price = product_price;
	}


	public int getProduct_total() {
		return product_total;
	}


	public void setProduct_total(int product_total) {
		this.product_total = product_total;
	}
	
	public int getProduct_exp() {
		return product_exp;
	}
	
	public void setProduct_exp(int product_exp) {
		this.product_exp = product_exp;
	}
	
	public int getProduct_mfg() {
		return product_mfg;
	}
	
	public void setProduct_mfg(int product_mfg) {
		this.product_mfg = product_mfg;
	}
	
	public int getProduct_addtime() {
		return product_addtime;
	}
	
	public void setProduct_addtime(int product_addtime) {
		this.product_addtime = product_addtime;
	}

	@Override
	public String toString() {
		return String.format("%-10s%-25s%8.2f%10d%10s%10s%10s", product_id, product_name, product_price,
				product_total, product_exp, product_mfg, product_addtime);
	}
	
}
