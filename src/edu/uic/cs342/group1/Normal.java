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

public class Normal implements FinalPrice {
	
	//assuming 7% tax for now
	public double calculatePrice(double subtotal) {
		return (subtotal * 1.07);
	}
	
	//print tax + discount info
	public void getInfo() {
		System.out.println("Type: Normal - Discount: 0% - Tax: 7%");
	}

}
