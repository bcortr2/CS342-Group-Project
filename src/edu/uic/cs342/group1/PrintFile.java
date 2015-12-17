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

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.TextAttribute;
import java.awt.font.TextLayout;
import java.awt.geom.Point2D;
import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterJob;
import java.io.BufferedReader;
import java.io.FileReader;
import java.text.AttributedString;


/**
THIS CODE HAD BEEN HEAVILY MODIFYED MY MAC CARTER TO ACCOMIDATE MULTIPLE PAGE PRINTING
Original credit to
* @author Jean-Pierre Dube <jpdube@videotron.ca>
* @version 1.0
* @since 1.0
*/

public class PrintFile {
	 public static boolean doneSeeking;
	 public static int SeekNumber;
	 public static int totalLinesInFile;
	 public static String FileName;
	//--- Private instances declarations
	private final static int POINTS_PER_INCH = 72;

	/**
	* Constructor: Example4
	* <p>
	*  
	*/
	public PrintFile() {

	 //--- Create a new PrinterJob object
	 PrinterJob printJob = PrinterJob.getPrinterJob();

	 //--- Create a new book to add pages to
	 Book book = new Book();

	 //--- Add the cover page using the default page format for this print

	 doneSeeking = false;
	 SeekNumber=0;
	 totalLinesInFile=0;
	 try{
	 BufferedReader br = new BufferedReader(new FileReader(FileName));
	 String line;
	 
	 	System.out.println("Now prompting to print the following to a printer.\n");
	     while((line = br.readLine()) != null){
	    	 System.out.println(line);
	    	 totalLinesInFile++;
	     }
	 

	 while (!doneSeeking) {
	     book.append(new page(), printJob.defaultPage());
	 }

	 SeekNumber=0;

	 //--- Add the document page using a landscape page format
	 PageFormat documentPageFormat = new PageFormat();
	 //    portrait PORTRAIT
	 documentPageFormat.setOrientation(PageFormat.PORTRAIT);

	 //--- Tell the printJob to use the book as the pageable object
	 printJob.setPageable(book);

	 //--- Show the print dialog box. If the user click the
	 //--- print button we then proceed to print else we cancel
	 //--- the process.
	 if (printJob.printDialog()) {
	   try {
	     printJob.print();
	   } catch (Exception PrintException) {
	     //happens sometimes whatever.
	   }
	   finally{
		   //continues.
	   }
	 }
	 
	 }catch (Exception e) {
		 System.out.println("No file found.");
	 }
	}


	private class page implements Printable {

	     int lowerLimit=0;
	     int upperLimit=0;
	     
	     int lineNumber = 0;
	     
	     public page()
	     {
	       lowerLimit = PrintFile.SeekNumber;
	         try {
	             BufferedReader br = new BufferedReader(new FileReader(PrintFile.FileName));
	             String line;
	                 while((line = br.readLine()) != null && lineNumber<58){//58){//58 lines seems to work nice
	                     lineNumber++;
	                 }
	         }
	         catch (Exception e) {
	             System.out.println("No file found");
	         }
	         upperLimit=lineNumber+PrintFile.SeekNumber;
	         PrintFile.SeekNumber += lineNumber;
//	         System.out.println("Seek no"+PnintFile.SeekNumber);
	         if (PrintFile.SeekNumber >= PrintFile.totalLinesInFile) {
	        	 PrintFile.doneSeeking = true;
	         }
//	         Debug 
//	         System.out.println("lower lim "+ lowerLimit );
//	         System.out.println("upper lim "+ upperLimit );
//	         System.out.println("    limit "+ PnintFile.totalLinesInFile );
	     }
	     

	     public int print(Graphics g, PageFormat pageFormat, int page) {

	          //--- Create the Graphics2D object
	          Graphics2D g2d = (Graphics2D) g;

	          //--- Translate the origin to 0,0 for the top left corner
	          g2d.translate(pageFormat.getImageableX(), pageFormat
	              .getImageableY());

	          //--- Set the drawing color to black
	          g2d.setPaint(Color.black);


	          //--- Create a point object to set the top left corner of the
	          // TextLayout object
	          Point2D.Double pen = new Point2D.Double(0.25 * POINTS_PER_INCH,
	              0.25 * POINTS_PER_INCH);

	          //--- Set the width of the TextLayout box
	          double width = 6 * POINTS_PER_INCH;

	          //--- Create an attributed string from the text string. We are
	          // creating an
	          //--- attributed string because the LineBreakMeasurer needs an
	          // Iterator as
	          //--- parameter.
	        
	            //HACK
	                lineNumber=0;
	                if (upperLimit==58) {
	                    upperLimit=59;
	                }
	                try {
	                     BufferedReader br = new BufferedReader(new FileReader(PrintFile.FileName));
	                    String line;
	                    
	                    if (lineNumber!=lowerLimit) {

	                        while ((line = br.readLine()) != null && (lineNumber!=lowerLimit) ){
	                            lineNumber++;
	                        }                    
	                    }                    
	                     while ((line = br.readLine()) != null && lineNumber!=upperLimit) {
	                        lineNumber++;
	                      //--- Create a string and assign the text
	                      String text = new String();
//	                    System.out.println(line);
	                    //Read in new things add them to text.
	                    //Run the print call.
	                    text=line;
	                      AttributedString paragraphText = new AttributedString(text);

	                      //--- Set the font for this text
	                      paragraphText.addAttribute(TextAttribute.FONT, new Font("serif",
	                          Font.PLAIN, 12));

	                      //--- Create a LineBreakMeasurer to wrap the text for the
	                      // TextLayout object
	                      //--- Note the second parameter, the FontRendereContext. I have set
	                      // the second
	                      //--- parameter antiAlised to true and the third parameter
	                      // useFractionalMetrics
	                      //--- to true to get the best possible output
	                      LineBreakMeasurer lineBreaker = new LineBreakMeasurer(paragraphText
	                          .getIterator(), new FontRenderContext(null, false, false));

	                      //--- Create the TextLayout object
	                      TextLayout layout;

	                      //--- LineBreakMeasurer will wrap each line to correct length and
	                      //--- return it as a TextLayout object
	                      while ((layout = lineBreaker.nextLayout((float) width)) != null) {

	                        //--- Align the Y pen to the ascend of the font, remember that
	                        //--- the ascend is origin (0, 0) of a font. Refer to figure 1
	                        pen.y += layout.getAscent();

	                        //--- Draw the line of text
	                        layout.draw(g2d, (float) pen.x, (float) pen.y);

	                        //--- Move the pen to the next position adding the descent and
	                        //--- the leading of the font
	                        pen.y += layout.getDescent() + layout.getLeading();
	                      }
//	                 }
	            }
	        }catch (Exception e) {
	                e.printStackTrace();
	        }
	                  //--- Validate the page
	          return (PAGE_EXISTS);
	        }
	  }

	}