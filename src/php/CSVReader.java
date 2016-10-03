package php;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.JOptionPane;

/**
 * Reads a CSV File
 * 
 * Assumes the line format is:
 * Date(YYYYMMDD),Product Name,Quantity, Product Category 
 *
 */
public class CSVReader 
{
	
	/**
	 * Error for if the product category of the product
	 * doesn't match the one in the CSV File
	 *
	 */
	public class ProductCategoryMismatchedException extends Exception
	{
		
		public ProductCategoryMismatchedException()
		{
			
			super();
			
		}
		
	}

	private String filename;
	private HashMap<String, Integer> nameToID;
	private ArrayList<Product> pl;
	
	/**
	 * Initializes the reader
	 * @param fn	the csv file name
	 */
	public CSVReader(String fn)
	{

		filename = fn;
		nameToID = new HashMap<String, Integer>();
		pl = DatabaseIO.productList;
		Product p;
		for(int i = 0; i < pl.size(); i++)
		{
			
			p = pl.get(i);
			nameToID.put(p.productName, p.productID);
			
		}
		
	}
	
	/**
	 * Reads and divides a line,
	 * delimited by commas.
	 * @param line		the input line
	 * @throws Exception 
	 */
	private void readLine(String line) throws ProductCategoryMismatchedException
	{
		
		String[] values = line.split(",");
		int year = Integer.parseInt(values[0].substring(0,4));
		int month = Integer.parseInt(values[0].substring(4,6));
		int day = Integer.parseInt(values[0].substring(6));
		java.sql.Date sqlDate = new java.sql.Date(year-1900,month-1,day);
		Product p = DatabaseIO.getProduct(values[1]);
		int quantity = Integer.parseInt(values[3]);
		System.out.println(p.getProductCategory());
		if(p.getProductCategory().equalsIgnoreCase(values[2]))
		{
			p.setProductStock(p.getProductStock() + quantity);
			DatabaseIO.updateProduct(p);
			DatabaseIO.addSale(nameToID.get(values[1]), sqlDate, quantity);
		}
		else
		{
			
			throw new ProductCategoryMismatchedException();
			
		}
		
	}
	
	/**
	 * Reads the CSV line by line
	 * @throws FileNotFoundException 
	 */
	public void readCSV() throws FileNotFoundException
	{
		
		Scanner scan = new Scanner(new File(filename));
		scan.nextLine();
		int i = 1;
		while(scan.hasNextLine())
		{
			i++;
			try {
				readLine(scan.nextLine());
			} catch (ProductCategoryMismatchedException e) {
				JOptionPane.showMessageDialog(null, "Product Category Mismatched on line " + Integer.toString(i));
			}
		}
		
		scan.close();
		
	}
	
	
}
