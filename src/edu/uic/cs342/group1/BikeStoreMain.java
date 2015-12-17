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

import java.io.IOException;

public class BikeStoreMain {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		
		BikeStore bs = BikeStore.getInstance();
		bs.run();

	}

}
