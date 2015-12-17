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
import java.io.Serializable;

public class Container implements Aggregate, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4152898762382538609L;
	//ArrayList holds the items in our container
	private ArrayList<Item> list = new ArrayList<Item>();
	

	@Override
	//create an iterator to iterate through the container
	public Iterator createIterator() {
		Iterator i = new ItemIterator();
		return i;
	}
	
	//Add an item into the container
	public void addItem(Item itm) {
		list.add(itm);
	}
	//get an item given its barcode, if found return Item else return null
	public Item getItem(String bc) {
		for (Item i : list) {
			if (i.getBarcode().equals(bc)) {
				return i;
			}
		}
		return null;
	}
	
	//return the size of the arrayList
	public int size() {
		return list.size();
	}
	


	//Iterator class for the container
	private class ItemIterator implements Iterator {
		private int currentIndex = 0; //start at first element
		
		public int getCurr() { return currentIndex; }//get the current value of the currentIndex
		public void resetCurr(){ currentIndex = 0; }//reset currentIndex to start of list
		
		//returns true if container has more Items in the list from currentIndex
		public boolean hasNext() {
			return currentIndex < list.size();
		}

		//returns next item from the currentIndex, if none left returns null
		public Item next() {
			if (currentIndex < list.size()) {
				Item next = list.get(currentIndex);
				currentIndex++;
				return next;
			}
			else {
				return null;
			}
		}
		
		//remove a given item from the list
		public boolean remove(Item it) {
			return list.remove(it);
		}

	}


}
