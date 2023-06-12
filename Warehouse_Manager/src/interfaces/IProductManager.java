package interfaces;

import java.util.Comparator;
import java.util.List;

import objects.Product;

public interface IProductManager {

	/**
	 * Add a product to product list.<br>
	 * @param m
	 * @return
	 */
	public boolean addProduct(Product m);
	
	/**
	 * Edit a product by product_id in product list.<br>
	 * @param m
	 * @return
	 */
	public boolean editProduct(Product m);
	
	/**
	 * Delete a product in product list.<br>
	 * @param m
	 * @return
	 */
	public boolean delProduct(Product m);
	
	/**
	 * Search product list by name.<br>
	 * @param name
	 * @return
	 */
	public List<Product> searchProduct(String name);
	
	/**
	 * Search product list by price.<br>
	 * @param name
	 * @return
	 */
	public List<Product> searchProduct(double price);
	
	/**
	 * Search mobile list by exp.<br>
	 * @param name
	 * @return
	 */
	public List<Product> searchProductByExp(int exp);
	
	/**
	 * Search mobile list by Mfg.<br>
	 * @param name
	 * @return
	 */
	public List<Product> searchProductByMfg(int mfg);
	
	/**
	 * Search mobile list by add time.<br>
	 * @param name
	 * @return
	 */
	public List<Product> searchProductByAddtime(int addTime);
	
	/**
	 * Sort mobile list by price
	 * @param price
	 * @return
	 */
	public List<Product> sortedProduct(double price);
}