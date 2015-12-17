/**************************************************************************/
/**																		 **/
/**						    Simple Bicycle Store					     **/
/**							      Group #1							     **/
/**				   	     Project Lead: Bunty Patel					     **/
/**  Members: Mac Carter, Arkadiusz Pamula, Brad Cortright, Guiquan Liu  **/
/**																	     **/
/**																		 **/
/**************************************************************************/

package edu.uic.cs342.group1;

import java.io.Serializable;
public class Part extends Item implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1505135537500462685L;
	private double bulkPrice;

	//constructor
	public Part(int bulkPrice){
		super();
		this.bulkPrice = bulkPrice;
	}
	//default constructor
	public Part(){
		super();
		this.bulkPrice=1;//one item per the price.
	}
	
	public void printInfo() {
		System.out.println("\nPART");
		super.printInfo();
		System.out.println("Bulk Price: " + this.bulkPrice);
	}
	
	public Item copyItem() {
		Item newI = new Part(this.getName(), this.getPrice(), this.getPromotion(), this.getDescription(), this.getSqftOccupied(), this.getWeight(), this.getSupplierName(), this.getSupplierPrice(), this.getReorderNumber(), this.getBarcode(), this.getStock(), this.getBulkPrice(), this.getQuantity());
		return newI;
		
	}
																														//added
	public Part(String name, double price, double promotion, String description, double sqftOccupied, double weight, String supplierName, 
			    double supplierPrice, String reorderNumber, String barcode, int stock, double bulkPrice, int q){
		super(name, price, promotion, description, sqftOccupied, weight, supplierName, supplierPrice, reorderNumber, barcode, stock, q);

		this.bulkPrice = bulkPrice;
	}
	
	
	//getters and setters
	public double getBulkPrice() {
		return bulkPrice;
	}
	public void setBulkPrice(double bulkPrice) {
		this.bulkPrice = bulkPrice;
	}

	
}
