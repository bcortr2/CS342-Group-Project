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

public class TaxExempt implements FinalPrice {
	
	//no calculation needed, total is the subtotal
	public double calculatePrice(double subtotal) {
		return subtotal;
	}
	
	//print discount + tax info
	public void getInfo() {
		System.out.println("Type: Tax Exempt - Discount: 0% - Tax: 0% Tax exempt do not get discounts.");
	}

}
