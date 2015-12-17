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

public interface Iterator {
	public boolean hasNext(); //returns true if container has another element
	public Item next(); //return the next element (Item object)
	public boolean remove(Item a); //removes Item from the container 
	public int getCurr();//get the currentIndex
	public void resetCurr();//reset currentIndex
}
