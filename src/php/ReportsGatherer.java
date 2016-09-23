package php;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

/**
 * Responsible for gathering data from the database and formatting it for use
 *
 */
public class ReportsGatherer 
{

	private ArrayList<HashMap<String, Integer>> weeklyData;
	private ArrayList<HashMap<String, Integer>> monthlyData;
	private ArrayList<Sale> saleList;
	private ArrayList<Integer> weeks;
	private ArrayList<Integer> months;
	private ArrayList<HashMap<String, Integer>> qmap;
	
	/**
	 * Initializes the ReportsGatherer
	 */
	public ReportsGatherer()
	{
		
		weeklyData = new ArrayList<HashMap<String, Integer>>();
		monthlyData = new ArrayList<HashMap<String, Integer>>();
		qmap = new ArrayList<HashMap<String, Integer>>();
		weeks = new ArrayList<Integer>();
		months = new ArrayList<Integer>();
		saleList = DatabaseIO.saleList;
		
	}
	
	/**
	 * Checks if the week is in the array list
	 * @param w		week in question
	 * @return		whether or not the week is in the list
	 */
	private boolean newWeek(int w)
	{
		
		for(int i = 0; i < weeks.size(); i++)
		{
			
			if(weeks.get(i).intValue() == w)
				return false;
			
		}
		
		return true;
	}
	
	/**
	 * Checks if the month is in the array list
	 * @param m		month in question
	 * @return		whether or not the week is in the list
	 */
	private boolean newMonth(int m)
	{
		
		for(int i = 0; i < months.size(); i++)
		{
			
			if(months.get(i).intValue() == m)
				return false;
			
		}
		
		return true;
		
	}
	
	/**
	 * Gets index of a week
	 * @param m		week
	 * @return		index
	 */
	private int getWeekIndex(int w)
	{
		
		for(int i = 0; i < weeks.size(); i++)
		{
			
			if(weeks.get(i).intValue() == w)
			{
				
				return i;
				
			}
			
		}
		
		return -1;
		
	}
	
	/**
	 * Gets index of a month
	 * @param m		month
	 * @return		index
	 */
	private int getMonthIndex(int m)
	{
		
		for(int i = 0; i < months.size(); i++)
		{
			
			if(months.get(i).intValue() == m)
			{
				
				return i;
				
			}
			
		}
		
		return -1;
		
	}
	
	/**
	 * Gathers all the weekly data.
	 * (Precondition: Data in database is sorted by date)
	 * @return		Formatted data
	 */
	public ArrayList<HashMap<String, Integer>> gatherWeekly()
	{
		
		Sale s;
		Date d;
		int ci = 0; //current index
		for(int i = 0; i < saleList.size(); i++)
		{
			
			s = saleList.get(i);
			d = s.getSaleDate();
			Calendar cal1 = Calendar.getInstance();
			cal1.setTime(d);
			int w = cal1.get(Calendar.WEEK_OF_YEAR);
			if(newWeek(w))
			{
				
				weeks.add(w);
				weeklyData.add(new HashMap<String, Integer>());
				ci = weeklyData.size()-1;				
				qmap.add(new HashMap<String, Integer>());
			}
			else
				ci = getWeekIndex(w);
			
			if(!qmap.get(ci).containsKey(s.getProductName()))
				qmap.get(ci).put(s.getProductName(), s.getQtySold());
			else
				qmap.get(ci).replace(s.getProductName(), qmap.get(ci).get(s.getProductName()) + s.getQtySold());
			
			if(!weeklyData.get(ci).containsKey(s.getProductName()))
				weeklyData.get(ci).put(s.getProductName(), qmap.get(ci).get(s.getProductName()));
			else
				weeklyData.get(ci).replace(s.getProductName(), qmap.get(ci).get(s.getProductName()));
			
		}
		
		return weeklyData;
		
	}
	
	/**
	 * Gathers all the monthly data.
	 * (Precondition: Data in database is sorted by date)
	 * @return		Formatted data
	 */
	public ArrayList<HashMap<String, Integer>> gatherMonthly()
	{
		
		Sale s;
		Date d;
		int ci = 0; //current index
		for(int i = 0; i < saleList.size(); i++)
		{
			
			s = saleList.get(i);
			d = s.getSaleDate();
			int m = d.getMonth();
			if(newMonth(m))
			{
				
				months.add(m);
				monthlyData.add(new HashMap<String, Integer>());
				ci = monthlyData.size()-1;
				qmap.add(new HashMap<String, Integer>());
				
			}
			else
				ci = getMonthIndex(m);
			
			if(!qmap.get(ci).containsKey(s.getProductName()))
				qmap.get(ci).put(s.getProductName(), s.getQtySold());
			else
				qmap.get(ci).replace(s.getProductName(), qmap.get(ci).get(s.getProductName()) + s.getQtySold());
			if(!monthlyData.get(ci).containsKey(s.getProductName()))
				monthlyData.get(ci).put(s.getProductName(), qmap.get(ci).get(s.getProductName()));
			else
				monthlyData.get(ci).replace(s.getProductName(), qmap.get(ci).get(s.getProductName()));
			
		}
		
		return monthlyData;
	}
	
	
}
