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

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DecimalFormat;
import java.util.Scanner;



public class BikeStore {
	//implemtation of singleton pattern, make a new instance of bikeStore
	private static BikeStore instance = new BikeStore();
	private BikeStore(){} //private constructor 
	//return the BikeStore singleton object
    public static BikeStore getInstance(){
	      return instance;
	   }
	

	
	public enum saveType { CUSTOMER, INVENTORY, LOWSTOCK } //save type depending on container
	
	public void run() throws IOException, ClassNotFoundException{
		
		//print manager object to print to printer
        PrintManager p = new PrintManager(); 
        //containers for inventory, items purchased from vendor, and items sold to customers
        Container inventory = new Container();
        Container purchaseList = new Container();
        Container salesList = new Container();
        //Item i and string input for I/O manipulation
        Item i;
	    String input;
		
	    System.out.print("Simple Bicycle Store\n"
		    	+ "Group #1 \n\n"
		    	+ "Project Lead: Bunty Patel\n"
		    	+ "Member: Mac Carter, Arkadiusz Pamula, Brad Cortright, Guiquan Liu\n");
	
	    System.out.println("Starting Program");
	    inventory = load (); //load inventory from file when program was last closed
	    PrintCommand(); //print commands
        Scanner s = new Scanner(System.in);
        
        Iterator inventoryIter = inventory.createIterator(); //iterator to iterate through inventory
        
        
        while(s.hasNext())
        {
        	input = takeInput(s);
        	char ch = input.charAt(0);
        	
        	// Quits the program -- saves the inventory to file before quitting
        	if(ch == 'q')
            {
            	save(inventory, saveType.INVENTORY);
            	//Should ask if they want a print out
            	
            	int lowStockValue = 0;
            	Container temp = new Container();
            	Iterator inventIter3 = inventory.createIterator();
            	while (inventIter3.hasNext()) {
            		Item stockItem = inventIter3.next();
            		if (stockItem.isLowStock()) {
            			lowStockValue++;
            			temp.addItem(stockItem);
            		}
            	}
            	
            	if(lowStockValue>0){
            	System.out.println("Would you like to print out low invintory (to a printer)? y/n");
            	input = takeInput(s);
            	ch = input.charAt(0);
            	if(ch=='y')
            	{
            		p.PrintLow();
            	}
            	else{
            		System.out.println("Quitting Program No file printed.");
            	}
            	}
            	
            	System.out.println("--- Good Bye ----");
                s.close();
                
                return;
            }
        	
        	// Add new inventory
        	else if (ch == 'a') {
        		//check if we're adding a new item or updating existing item's stock
        		System.out.println("Are you adding new inventory or restocking existing inventory?");
            	System.out.println("     1. Adding New  ");
            	System.out.println("     2. Restocking  ");
            	System.out.println("     3. Remove Item ");
            	System.out.print("Please choose a number: ");
            	
            	//update type
            	int type = s.nextInt();
           
            	
            	//adding new item, collect basic information for all Item objects
            	if (type == 1) {
            		System.out.println("Enter Name:");
            		String name = s.next();
            		System.out.println("Enter Promotional Discount (EX: 25% is 0.25 ):");
            		double promo = s.nextDouble();
            		System.out.println("Enter Description (new/used):");
            		String description = s.next();
            		System.out.println("Enter Square Footage:");
            		double sqft = s.nextDouble();
            		System.out.println("Enter Weight (lbs):");
            		double weight = s.nextDouble();
            		System.out.println("Enter Supplier Name:");
            		String supp = s.next();
            		System.out.println("Enter Supplier Price:");
            		double sp = s.nextDouble();
            		System.out.println("Enter reorder #:");
            		String ron = s.next();
            		System.out.println("Enter barcode #");
            		String bc = s.next();
            		System.out.println("Enter quantity on hand:");
            		int qty = s.nextInt();
            		
            		//check if it's a bike or a part
            		System.out.println("Bike or Part? B - Bike, P - Part");
            		String ans = s.next();
            		ans.toUpperCase();
            		char an = ans.charAt(0);
            		//if it's a bike, collect bike specific information
            		if (an == 'B' || an == 'b') {
            			System.out.println("Enter type of bike:");
            			String btype = s.next();
            			System.out.println("Enter speed of bike:");
            			int speed = s.nextInt();
            			System.out.println("Enter color of bike:");
            			String color = s.next();
            			
            			//recommend a price for this this bike based on input
                		double recPrice = calcRecommendedPrice(sp, sqft);
                		System.out.println("Recommended Retail Price is: " + recPrice);
                		System.out.println("Accept this price? Y - yes N - no (enter own price)");
                		ans = s.next();
                		ans.toUpperCase();
                		an = ans.charAt(0);
                		
                		//if user doesn't accept recommended price then get their price
                		if (an == 'N' || an == 'n') {
                			System.out.println("Enter your retail sale price");
                			recPrice = s.nextDouble();
                		}
                		else {
                			System.out.println("Accepting price...");	
                		}
                		//create new bike object and add into inventory and purchase list
                		Bike newBike = new Bike(name, recPrice, promo, description, sqft, weight, supp, sp, ron, bc, qty, btype, speed, color, qty);
                		inventory.addItem(newBike);
                		purchaseList.addItem(newBike);
                		System.out.println("Bike added to inventory");
            		}
            		//if object is a part, get part specific information
            		else if (an == 'P' || an == 'p') {
            			System.out.println("Enter bulk price of part:");
            			double bulkPrice = s.nextDouble();
            			
            			//recommend a price based on input
            			double recPrice = calcRecommendedPrice(sp, sqft);
                		System.out.println("Recommended Retail Price is: " + recPrice);
                		System.out.println("Accept this price? Y - yes N - no (enter own price)");
                		ans = s.next();
                		ans.toUpperCase();
                		an = ans.charAt(0);
                		//if user doesn't accept price get theirs
                		if (an == 'N' || an == 'n') {
                			System.out.println("Enter your retail sale price");
                			recPrice = s.nextDouble();
                		}
                		else {
                			System.out.println("Accepting price...");	
                		}
                		//create a new part based on input and add to inventory and purchase list
                		Part newPart = new Part(name, recPrice, promo, description, sqft, weight, supp, sp, ron, bc, qty, bulkPrice, qty);
                		inventory.addItem(newPart);
                		purchaseList.addItem(newPart);
                		System.out.println("Part added to inventory");
            		}
            		//invalid input
            		else {
            			System.out.println("Invalid item type");
            			continue;
            		}
            	}
            	//update a given item's stock
            	else if (type == 2) {
            		//get barcode for item we want to change stock of, and get item
            		System.out.println("Enter barcode of item you want to restock");
            		String bc = s.next();
            		//get item in inventory and purchase list
            		Item restockItem = inventory.getItem(bc);
            		Item updatePurchaseListItem = purchaseList.getItem(bc);
            		//if we don't find item, incorrect barcode
            		if (restockItem == null) {
            			System.out.println("Item not found");
            		}
            		//take new stock and update in both lists
            		else {
            			System.out.println("Enter how many new units to add to stock:");
            			int newStock = s.nextInt();
            			restockItem.setStock(restockItem.getStock() + newStock);
//            			updatePurchaseListItem.setStock(updatePurchaseListItem.getStock() + newStock);
            		}
            	}
            	else if (type == 3) {
            		//get barcode if item being removed
            		System.out.println("Enter barcode of item you want to remove");
            		String bc = s.next();
            		//find the item and then remove from list, returns true if removed
            		if(inventoryIter.remove(inventory.getItem(bc))) {
            			System.out.println("Item removed");
            		}
            		//if not, item was not found
            		else {
            			System.out.println("Item not found");
            		}
            	}
            	else {
            		System.out.println("Invalid selection, try again");
            	}
        	}
        	// Print all inventory items
        	else if (ch == 'u') {
        		inventoryIter.resetCurr(); //reset iterate
        		Item inventoryItm = inventoryIter.next();
        		//iterate through all items and print info
        		while (inventoryItm != null) {
        			(inventoryItm).printInfo();
        			inventoryItm = inventoryIter.next();
        		}
        		
        	}
        	//read file and put into inventory
        	else if (ch == 'r')
        	{
        		System.out.println("\nReading file....\n");
        		inventory = load();
        	}
        	//write file onto disk so that inventory is available next time
        	else if(ch == 'w')
        	{
        		System.out.println("\nWriting file....\n");
        		save(inventory, saveType.INVENTORY);
        	}
            
            // look up items based on barcode
            else if(ch == 'l')
            {
            	System.out.print("Please enter item bar code you want to look up: ");
            	
            	//int bc =  s.nextInt();
            	
                String bc = s.next();
            	
//            	while(!isNumeric(str))
//            	{
//            		System.out.println("You input is not number, please input integer: ");
//            		str = s.next();
//            	}
            	
//            	int bc = Integer.parseInt(str);
            	//get the item and have it print info
            	i = inventory.getItem(bc);
            	
            	if(i == null)
            	{
            		System.out.println("Item could not find.");
            	}
            	else
            	{
            		i.printInfo();
            	}
            }
            
            //choose the type of customer and do checkout
            else if(ch == 'c')
            {
             	System.out.println("Please tell me what type of customer you are?");
            	System.out.println("     1. Normal  ");
            	System.out.println("     2. Veteran  ");
            	System.out.println("     3. Preferred Customer  ");
            	System.out.println("     4. Tax Exempt  ");
            	System.out.print("Please choose a number: ");
            	
            	//save file at the begining
          
        		
            	//customer type
            	int type = 1;
            	Customer cust = new Customer();
            	try
            	{
                	type = s.nextInt();
                	
                	while(type<1 || type>3)
                	{
                		System.out.print("Customer only have 3 types(1,2,3) Please input again: ");
                		type = s.nextInt();
                	}
                	
            	}catch(Exception e){
            		System.out.println("Invail input, normal customer selected.");
            		s.nextLine();
            		type =1;
            	}
            	finally{
            		cust.setPricing(type);
            	}
            	
            	//item barcode
            	String bc;
            	double subtotal = 0.0;
            	
            	// Create new cart and its iterator
            	Container cart = new Container();
            	Iterator cartIter = cart.createIterator();
            	
            	System.out.println("Please enter the item barcode you want to add to cart, -1 to continue check out, -2 to quit");
            	
            	//take item name
            	bc = s.next();
            	
            	while(!bc.equals("-2")&& !bc.equals("-1"))
            	{
            		i = inventory.getItem(bc);
            		
            		if (i == null) {
            			System.out.println("Item not found");
            		}
            		else {
		        		//check if low stock
		        		if(i.isLowStock())
		        		{
		        			System.out.println("Item is low stock, and only "+ i.getStock()+" item left");
		        		}
		        		
		        		//check if out of stock
		        		if (i.isOutOfStock())
		        		{
		        			System.out.println("Item is out of stock");
		        		}
		        		//in stock, get how many they want
		        		else
		        		{
		        			System.out.println("Please enter the amount for the item you just added.");
		                	
		        			//get the amount that we want, 
		                	int amount = s.nextInt();
		                	//check to make sure we have that much in stock
		                	boolean validAmt = amount <= i.getStock();
		                	//if not get new amount such that amount is less or equal to instock amount
		                	while (!validAmt) {
		                		System.out.println("Not Enough Quantity (" + i.getStock() + " remaining), please enter less or select different item");
		                		amount = s.nextInt();
		                		validAmt = amount <= i.getStock();
		                	}
		                	
		                	//copy each item we want into a new item
		                	Item checkOutItem = i.copyItem();
		                	//change quantity and stock elements to amount customer wants
		                	checkOutItem.setQuantity(amount);
		                	checkOutItem.setStock(amount);
		                
                    		
		                	//add this item into cart
		                	cart.addItem(checkOutItem);
		                	
		        		}
		        		
            		}
                	
                	System.out.println("Please enter the item barcode you want to add to cart -1 continue check out, -2 to quit");
                	
                	bc = s.next();
                	
                }
            	if(!bc.equals("-2"))
            	{

            		if(cart.size()>-1)
            		{
            			//print cart contents that's being checked out, price per item, total price, quantity, and order subtotal
                    	DecimalFormat df = new DecimalFormat("#.##");
                    	System.out.println("Items: ");
                    	cartIter.resetCurr();
                    	Item cartItm = cartIter.next();
                    	while (cartItm != null) {
                    		System.out.println(cartItm.getName() + " - $" + df.format(cartItm.getPrice()) + "/ea - QTY: " + cartItm.getQuantity() + " - TOTAL: $" + df.format(cartItm.getPrice() * cartItm.getQuantity()));
                    		subtotal += cartItm.getPrice() * cartItm.getQuantity();
                    		cartItm = cartIter.next();
                    	}
                    	System.out.println("\nSubtotal: $ "+ df.format(subtotal));
                    	
                    	//calculate the final price based on the type of customer it was and print type of customer
                    	System.out.println("Calculating the final price based on customer type:");
                    	System.out.println("\nCustomer Info: ");
                    	cust.getType();
                    	double fp = cust.getPrice(subtotal);
                    	
                    	System.out.println("\nFinal price is $"+ df.format(fp) + "\nConfirm Payment? Y - yes, N - No \n");
                    	Customer.TOTAL = fp; 
                    	Customer.SUBTOTAL = subtotal;
                    	
                    	String cfstring = s.next();
                    	
                    	cfstring.toUpperCase();
                    	char cf = cfstring.charAt(0);
                    	//finish checkout?, yes
                    	if(cf == 'Y' || cf == 'y')
                    	{
                    		
                    		cartIter.resetCurr(); //reset cart iterator's index to 0
                    		cartItm = cartIter.next(); //get first item in cart
                    		
                    		//update inventory by subtracting the amount that the customer bought 
                    		while (cartItm != null) {
                    			salesList.addItem(cartItm);
                    			Item inventoryItem = inventory.getItem(cartItm.getBarcode());
                    			inventoryItem.setStock(inventoryItem.getStock() - (cartItm.getQuantity()));
                    			cartItm = cartIter.next();
                    		}
                        	    
                    		
                    		System.out.println("Inventory updated...LowStock updated");
                    		
                    		//save new inventory
                    		save(inventory, saveType.INVENTORY);
                    		
                    		//save cart that was purchased
                    		save(cart, saveType.CUSTOMER);
                    		
                    		
                    		System.out.println("Would you like to print a Recept? Y - yes, N - No ");
                    		cfstring = s.next();
                        	
                        	cfstring.toUpperCase();
                        	cf = cfstring.charAt(0);
                    		if(cf == 'Y' || cf == 'y')
                        	{
                    			cust.printRecipt();
                        	}
                    		
                    		//save low stock file
                    		Container temp = new Container();
                        	Iterator inventIter3 = inventory.createIterator();
                        	while (inventIter3.hasNext()) {
                        		Item stockItem = inventIter3.next();
                        		if (stockItem.isLowStock()) {
                        			temp.addItem(stockItem);
                        		}
                        	}
                        	save(temp, saveType.LOWSTOCK);
                    	}
                    	//if not, remove cart
                    	else if(cf == 'N' || cf == 'n')
                    	{
                    		System.out.println("Cart removed...");
                    		
                    		
                    	}
            		}
            		else
            		{
            			System.out.println("Nothing in cart.. quiting");
            		}
            	}
            		
            }
            
            //Print purchase list by iterating through purchase list and printing item's info in the list
            else if(ch == 'p')
            {
            	Iterator pListIter = purchaseList.createIterator();
            	pListIter.resetCurr();
            	while (pListIter.hasNext()) {
            		Item pI = pListIter.next();
            		pI.printInfo();
            	}
            }
            
            //print contain in stock by checking inventory for items whose stock is > 0
            else if(ch =='i')
            {
            	Iterator inventIter = inventory.createIterator();
            	while (inventIter.hasNext()) {
            		Item stockItem = inventIter.next();
            		if (stockItem.getStock() > 0) {
            			stockItem.printInfo();
            		}
            	}
            }
        	//print items in inventory whose items are low in stock, by finding it and printing info
            else if(ch =='s')
            {
            	Container temp = new Container();
            	Iterator inventIter = inventory.createIterator();
            	while (inventIter.hasNext()) {
            		Item stockItem = inventIter.next();
            		if (stockItem.isLowStock()) {
            			temp.addItem(stockItem);
            			stockItem.printInfo();
            		}
            		
            	}
            	
            	if(temp.size() ==0)
        		{
        			System.out.println("No item low stock.");
        		}
            	save(temp, saveType.LOWSTOCK);

            }
        	//print items in inventory whose items are out of stock by finding it and printing info
            else if(ch =='o')
            {
            	Iterator inventIter = inventory.createIterator();
            	while (inventIter.hasNext()) {
            		Item outStockItem = inventIter.next();
            		if (outStockItem.getStock() == 0) {
            			outStockItem.printInfo();
            		}
            	}
            }
        	//print items that were sold in the store from the sales list
            else if(ch =='n')
            {
            	Iterator salesIter = salesList.createIterator();
            	while (salesIter.hasNext()) {
            		Item saleItem = salesIter.next();
            		saleItem.printInfo();
            	}
            }
        	
        	//print command list
            else if (ch == 'k') {
            	PrintCommand();
            }
            //invalid command
            else
            {
                System.out.println ("Invalid command input");
                System.out.println ("Clearing until End of Line *" + s.nextLine() + "*");
            }   
            System.out.print ("\nEnter a command (k to print commands): ");

        }
        System.out.println("Quiting Program - EOF reached\n"); 

	}
	//print list of commands
	public static void PrintCommand()
	{
	    System.out.println("r - Read file");
	    System.out.println("w - Write file");
	    System.out.println("l - Look up items");
        System.out.println("c - Customer check out");
        System.out.println("p - Print list of items purchased from vendors");
        System.out.println("n - Print list of all items sold in store");
        System.out.println("u - Print all items in inventory");
        System.out.println("o - Print out of stock items in inventory");
        System.out.println("s - Print low stock (< 5) items in inventory");
        System.out.println("i - Print in stock items in inventory");
        System.out.println("a - Add new inventory/restock");
        System.out.println("k - Print list of commands");
        System.out.println("q - Quits the program\n");

        System.out.print ("Please enter a command: ");
	}
	
	public static double calcRecommendedPrice(double purchasePrice, double sqft) {
		//assuming it costs $0.01/sqft to store + $5 to hold the item in the store
		return (purchasePrice + 5.0) + (0.01*sqft);
	}
	
	public static String takeInput(Scanner s)
	{
		String command = s.next();
		return command;
	}
	
	//load function to load container
	
	public static Container load() throws IOException, ClassNotFoundException{
		Container tempCont = new Container();
		try{
		FileInputStream loadFile = new FileInputStream("Inventory.sav");
		ObjectInputStream loadCont = new ObjectInputStream(loadFile);
		tempCont = (Container) loadCont.readObject();
		loadCont.close();
		}
		catch(Exception e){
			System.out.println("File does not exist for the first time users, please save a file.");
		}
		finally{
			return tempCont;
		}
	}
	
	//save function to save container
	public static void save(Container items, saveType typeOfSave) throws IOException{
		switch (typeOfSave){
		
			case INVENTORY: 
				FileOutputStream saveFile = new FileOutputStream("Inventory.sav");
				ObjectOutputStream saveObj = new ObjectOutputStream(saveFile);
				saveObj.writeObject(items);
				saveObj.close();
				break;
				
			case CUSTOMER: 
				String printStrCust;
				Iterator custIter = items.createIterator();
				Item tmpCust;
				FileWriter saveCust = new FileWriter("customer.txt");
				
				while (custIter.hasNext()){
					 tmpCust = (Item)custIter.next();
						 printStrCust = "Name: " + tmpCust.getName();
					 saveCust.write(printStrCust);
					 
					 if (tmpCust.getName() == "Bike"){
						 printStrCust = "\nColor: " + ((Bike)tmpCust).getColor() + "\nSpeed: " + ((Bike)tmpCust).getSpeed();
					 saveCust.write(printStrCust);
					 }
					printStrCust = "\nPrice: $" + tmpCust.getPrice() + 
				    "\nQty: " + tmpCust.getQuantity() + "\n";
					saveCust.write(printStrCust);
					saveCust.write(" \n");
				}
				DecimalFormat df = new DecimalFormat("#.##"); 
				saveCust.write("SubTotal: $"+df.format(Customer.SUBTOTAL));				
				saveCust.write("\n TOTAL: $"+df.format(Customer.TOTAL));
				saveCust.write(" \n");
				saveCust.close();
				break;
				
			case LOWSTOCK: 
				String printStr;
				Iterator stockIter = items.createIterator();
				Item tmpStock;
				FileWriter saveStock = new FileWriter("Low_stock.txt");
				
				while (stockIter.hasNext()){
					 tmpStock = (Item)stockIter.next();
					printStr = "Name: " + tmpStock.getName() + "\nNumber in stock: " + tmpStock.getStock() + "\n********************" + 
				    "\nPlease order " + tmpStock.getStock() * 2 + "\nSold by: " + tmpStock.getSupplierName() + 
				    "\nSupplier price: $" + tmpStock.getSupplierPrice() + "\nSupplier's ID for item: " + 
				    tmpStock.getReorderNumber() + "\n--------------------\n";
					saveStock.write(printStr);
					saveStock.write(" \n");
				}
				saveStock.close();
				break;
				
			default: break;
		}
	
	}
	
	public static boolean isNumeric(String str)  
	{  
	  try  
	  {  
	    double d = Double.parseDouble(str);  
	  }  
	  catch(NumberFormatException nfe)  
	  {  
	    return false;  
	  }  
	  return true;  
	}

}
