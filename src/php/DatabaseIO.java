package php;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseIO {
	
	Connection dbcon = null;
	
	public void connect(){
		
		try {
            // registers Oracle JDBC driver - though this is no longer required
            // since JDBC 4.0, but added here for backward compatibility
           // Class.forName("oracle.jdbc.OracleDriver");
 
            // METHOD #1
            String dbURL1 = "jdbc:oracle:thin:sys as sysdba/oracle@ec2-52-62-243-187.ap-southeast-2.compute.amazonaws.com:1521:XE";
            dbcon = DriverManager.getConnection(dbURL1);
            if (dbcon != null) {
                System.out.println("Connected with connection #1");
            }
           
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
	}
	
}
