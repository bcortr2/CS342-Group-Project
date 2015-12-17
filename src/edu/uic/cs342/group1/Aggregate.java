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

public interface Aggregate {
	//aggregate class that will be the container
	//has to create an iterator to iterate through the aggregate
	public Iterator createIterator();
}
