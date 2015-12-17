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

/**
* Class: Example4
* <p>
* 
* Example of using the TextLayout class to format a text paragraph.
* <p>
* 
* @author Jean-Pierre Dube <jpdube@videotron.ca>
* @version 1.0
* @since 1.0
*/

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.TextAttribute;
import java.awt.font.TextLayout;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterJob;
import java.text.AttributedString;
import java.io.BufferedReader;
import java.io.FileReader;

/**
 THIS CODE HAD BEEN HEAVILY MODIFYED MY MAC CARTER TO ACCOMIDATE MULTIPLE PAGE PRINTING
Original credit to
* @author Jean-Pierre Dube <jpdube@videotron.ca>
* @version 1.0
* @since 1.0
*/

public class PrintManager{
	public static void printRecipt(){
		PrintFile.FileName = "customer.txt";    
		PrintFile recipt  = new PrintFile();
	}

	public static void PrintLow(){
		PrintFile.FileName = "Low_stock.txt";    
		PrintFile stockfile = new PrintFile();
	}
}

