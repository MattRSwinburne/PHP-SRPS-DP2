package php;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Generates a CSV File
 */
public class CSVGenerator 
{
	
	private String filename;
	private int weekmonth;
	
	/**
	 * Initializes the generator
	 * @param fn	name of output file
	 * @param wm	(0 for weekly, 1 for monthly)
	 */
	public CSVGenerator(String fn, int wm)
	{
		
		filename = fn;
		weekmonth = wm;
		
	}
	
	/**
	 * Generates the CSV File
	 * @param alhm		the data
	 * @throws IOException	if file cannot be found or cannot be accessed
	 */
	public void generate(ArrayList<HashMap<String, Integer>> alhm) throws IOException
	{
		
		PrintWriter output = new PrintWriter(new FileWriter(filename));
		if(weekmonth == 0)
			output.println("Week,Product,Qty Sold");
		else if(weekmonth == 1)
			output.println("Month,Product,Qty Sold");
		
		Set set;
		Iterator iterator;
		
		for(int i = 0; i < alhm.size(); i++)
		{
			
			set = alhm.get(i).entrySet();
			iterator = set.iterator();
			while(iterator.hasNext())
			{
				
				Map.Entry mentry = (Map.Entry) iterator.next();
				output.printf("%d,%s,%d\n", i + 1, mentry.getKey(), ((Integer)mentry.getValue()).intValue());
				
			}
			
		}
		output.close();
		
		
	}

}
