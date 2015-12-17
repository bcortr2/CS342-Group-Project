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
public class Bike extends Item implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4512883357126728279L;


	public void printInfo() {
		System.out.println("\nBIKE");
		super.printInfo();
		System.out.println("Type: " + this.type);
		System.out.println("Speed: " + this.speed);
		System.out.println("Color: " + this.color);
	}

	public Bike(String type,int speed,String color){
		super();
		this.type=type;
		this.speed=speed;
		this.color=color;
	}
	//Default constructor
	public Bike(){
		super();
		this.type="";
		this.speed=0;
		this.color="";
	}																												//added
	public Bike(String name,double price,double promotion,String description,double sqftOccupied,double weight,String supplierName, 
			    double supplierPrice,String reorderNumber,String barcode,int stock,String type,int speed,String color, int q) {
		super(name,price,promotion,description,sqftOccupied,weight,supplierName, supplierPrice,reorderNumber,barcode,stock, q);
		this.type=type;
		this.speed=speed;
		this.color=color;
	}
	
	public Item copyItem() {
		Item newI = new Bike(this.getName(), this.getPrice(), this.getPromotion(), this.getDescription(), this.getSqftOccupied(), this.getWeight(), this.getSupplierName(), this.getSupplierPrice(), this.getReorderNumber(), this.getBarcode(), this.getStock(), this.type, this.speed, this.color, this.getQuantity());
		return newI;
	}

	private String type;
	private int speed;
	private String color;
	
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	
	
}
