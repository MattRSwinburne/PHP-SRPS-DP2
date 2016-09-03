package php;

import java.sql.*;

public class DatabaseIO {
	
	
	//use this to create a connection to the database, and then return that connection so that we can use it.
	//Will cause problems if you cannot access the database, like from inside Swinburne's Network
	public Connection connect(){
		
		Connection dbcon = null;
		
		//try and connect, throw an exception if the connection fails for whatever reason
		try {
                   
            String dbURL1 = "jdbc:oracle:thin:sys as sysdba/oracle@ec2-52-62-243-187.ap-southeast-2.compute.amazonaws.com:1521:XE";
            dbcon = DriverManager.getConnection(dbURL1);
            if (dbcon != null) {
                System.out.println("Connected with connection #1");
            }
           
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
		return dbcon;
	}
	
	//execute the stored procedure that adds a new product to the database
	public void AddProduct(String productCategory, String productDescription, String productName, int productStock){
		
		//get the connection
		Connection con = connect();
		
		//prepare CallableStatement
		CallableStatement pStmt = null;
		
		try {
			//construct the statement
			pStmt = con.prepareCall("{call SYSTEM.ADDPRODUCT(?,?,?,?)}");
			pStmt.setString("pProductCategory", productCategory);
			pStmt.setString("pProductDescription", productDescription);
			pStmt.setString("pProductName", productName);
			pStmt.setInt("pProductStock", productStock);
			
			//execute the procedure
			pStmt.execute();
			
		} catch (SQLException ex) {
            //check for the specific exceptions we throw in the database
			//at this stage we only have error code 20001
			if (ex.getErrorCode() == 20001) {
            	System.err.println("This product already exists");
            } else {
            	ex.printStackTrace();
            }            
        } finally {
        	
        	//make sure everything is closed
        	if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    System.err.println("SQLException: " + e.getMessage());
                }
            }
            if (pStmt != null) {
                try {
                	pStmt.close();
                } catch (SQLException e) {
                    System.err.println("SQLException: " + e.getMessage());
                }
            }
        }
        
	}
	
	
	
}
