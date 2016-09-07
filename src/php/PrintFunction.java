package php;

import java.io.*;
import java.lang.*;
import java.util.*;

// This is just to test the print button
public class PrintFunction {
	
	private Formatter output;
	
	public void openFile(){
		try{
			output = new Formatter("Report.doc");
		}catch(Exception e){
			
		}
	}
	
	public void addRecords(){
		
		output.format("%s%s%s%s", "testing ", "1", " 2", " 3");
	}
	
	public void closeFile(){
		output.close();
	}

}
