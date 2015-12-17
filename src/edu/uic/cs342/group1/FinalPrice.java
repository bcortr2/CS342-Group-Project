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

//Final Price Interface for Strategy Pattern to select between pricing algorithms
public interface FinalPrice {
	//calculates final price given subtotal based on type of customer (and discounts + tax they would be given/charged)
	public double calculatePrice(double subtotal);
	//prints the tax and discount info for a given pricing algorithm 
	public void getInfo();
}
