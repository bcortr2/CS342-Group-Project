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
import java.util.*;

public class Customer {
	public static double TOTAL;
	public static double SUBTOTAL;
	
	Random r = new Random();
	private int id;
	private String type;
	private FinalPrice calcPrice;
	
	public ArrayList<Item> list = new ArrayList<Item>();
	//public ArrayList<Integer> list = new ArrayList<Integer>();
	
	// an instance of Container class, used to store the items the customer intends to purchase
	public Container cart = new Container();
	
	// an instance of PrintManager class, used to print receipt at checkout
	PrintManager p = new PrintManager();
	
	public Customer() 
	{
		this.id = 0;
		this.type = "Normal";
	}

	public int getId(){
		return id;
	}
	
	public void setId(){
		
		while(true){
			
			// set random number to id
			this.id = r.nextInt(999999);
			
			// do not allow for duplicates
			if (list.contains(id))
				continue;
			else
				break;
		}
	}
	
	public void getType(){
		calcPrice.getInfo();
	}
	
	public void setType(String type){
		this.type = type;
	}
	
	public void setPricing(int i) {
		switch (i) {
		case 1: this.calcPrice = new Normal(); this.type = "Normal"; break;
		case 2: this.calcPrice = new Veteran(); this.type = "Veteran"; break;
		case 3: this.calcPrice = new PreferredCustomer(); this.type = "Preferred Customer"; break;
		case 4: this.calcPrice = new TaxExempt(); this.type = "Tax Exempt"; break;
		}
	}
	
	public double getPrice(double subtotal) {
		
		return calcPrice.calculatePrice(subtotal);
	}
	
	public void printRecipt(){
		p.printRecipt();
	}
	
	// used to retrieve an item, returns item instance
	public Item ItemLookup(String name){
		
		if (list.contains(name))
			return list.get(1);
		else
			return null;
	}
	
}
