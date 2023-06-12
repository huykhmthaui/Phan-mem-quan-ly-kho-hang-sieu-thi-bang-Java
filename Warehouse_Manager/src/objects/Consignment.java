package objects;

import java.io.Serializable;

public class Consignment implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public static String CONSIGNMENT_ID = "No id";
	public static String CONSIGNMENT_NAME = "No name";
	public static double CONSIGNMENT_PRICE = 0.0;
	public static int CONSIGNMENT_TOTAL = 0;
	public static int CONSIGNMENT_EXP = 0;
	public static int CONSIGNMENT_MFG = 0;
	public static int CONSIGNMENT_ADDTIME = 0;
	
	private String consignment_id;
	private String consignment_name;
	private double consignment_price;
	private int consignment_total;
	private int consignment_exp;
	private int consignment_mfg;
	private int consignment_addtime;
	
	public Consignment() {
		this(Consignment.CONSIGNMENT_ID, Consignment.CONSIGNMENT_NAME, Consignment.CONSIGNMENT_PRICE, Consignment.CONSIGNMENT_EXP, Consignment.CONSIGNMENT_MFG,
				Consignment.CONSIGNMENT_TOTAL, Consignment.CONSIGNMENT_ADDTIME);
	}
	
	public Consignment(String consignment_id, String consignment_name, double consignment_price, int consignment_total,
			int consignment_exp, int consignment_mfg, int consignment_addtime) {
		super();
		this.consignment_id = consignment_id;
		this.consignment_name = consignment_name;
		this.consignment_price = consignment_price;
		this.consignment_total = consignment_total;
		this.consignment_exp = consignment_exp;
		this.consignment_mfg = consignment_mfg;
		this.consignment_addtime = consignment_addtime;
	}


	public String getConsignment_id() {
		return consignment_id;
	}


	public void setConsignment_id(String consignment_id) {
		this.consignment_id = consignment_id;
	}


	public String getConsignment_name() {
		return consignment_name;
	}


	public void setConsignment_name(String consignment_name) {
		this.consignment_name = consignment_name;
	}


	public double getConsignment_price() {
		return consignment_price;
	}


	public void setConsignment_price(double consignment_price) {
		this.consignment_price = consignment_price;
	}


	public int getConsignment_total() {
		return consignment_total;
	}


	public void setConsignment_total(int consignment_total) {
		this.consignment_total = consignment_total;
	}
	
	public int getConsignment_exp() {
		return consignment_exp;
	}
	
	public void setConsignment_exp(int consignment_exp) {
		this.consignment_exp = consignment_exp;
	}
	
	public int getConsignment_mfg() {
		return consignment_mfg;
	}
	
	public void setConsignment_mfg(int consignment_mfg) {
		this.consignment_mfg = consignment_mfg;
	}
	
	public int getConsignment_addtime() {
		return consignment_addtime;
	}
	
	public void setConsignment_addtime(int consignment_addtime) {
		this.consignment_addtime = consignment_addtime;
	}

	@Override
	public String toString() {
		return String.format("%-10s%-25s%8.2f%10d%10s%10s%10s", consignment_id,
		consignment_name,
		consignment_price,
		consignment_total,
		consignment_exp,
		consignment_mfg,
		consignment_addtime);
	}
	
}
