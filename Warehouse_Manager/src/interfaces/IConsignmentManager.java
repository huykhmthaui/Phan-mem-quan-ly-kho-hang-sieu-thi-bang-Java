package interfaces;

import java.util.List;

import objects.Consignment;

public interface IConsignmentManager {

	/**
	 * Add a consignment to product list.<br>
	 * @param m
	 * @return
	 */
	public boolean addConsignment(Consignment m);
	
	/**
	 * Edit a consignment by product_id in product list.<br>
	 * @param m
	 * @return
	 */
	public boolean editConsignment(Consignment m);
	
	/**
	 * Delete a consignment in product list.<br>
	 * @param m
	 * @return
	 */
	public boolean delConsignment(Consignment m);
	
	/**
	 * Search consignment list by name.<br>
	 * @param name
	 * @return
	 */
	public List<Consignment> searchConsignment(String name);
	
	/**
	 * Search consignment list by price.<br>
	 * @param name
	 * @return
	 */
	public List<Consignment> searchConsignmentByPrice(double price);
	
	/**
	 * Search consignment list by exp.<br>
	 * @param name
	 * @return
	 */
	public List<Consignment> searchConsignmentByExp(int exp);
	
	/**
	 * Search consignment list by Mfg.<br>
	 * @param name
	 * @return
	 */
	public List<Consignment> searchConsignmentByMfg(int mfg);
	
	/**
	 * Search consignment list by add time.<br>
	 * @param name
	 * @return
	 */
	public List<Consignment> searchConsignmentByAddtime(int addTime);
	
	/**
	 * Sort consignment list by price
	 * @param price
	 * @return
	 */
	public List<Consignment> sortedConsignment(double price);
}
