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

public class PreferredCustomer implements FinalPrice {

	//assuming for now that preferred customers get a 15% discount on subtotal with 7% tax
	public double calculatePrice(double subtotal) {
		return ((subtotal * .85) * 1.07);
	}
	
	//print discount + tax info
	public void getInfo() {
		System.out.println("Type: Preferred Customer - Discount: 15% - Tax: 7%");
	}

}
