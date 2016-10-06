package php;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseIO {

	public static ArrayList<Product> productList = new ArrayList<Product>();
	public static ArrayList<Sale> saleList = new ArrayList<Sale>();

	//use this to create a connection to the database, and then return that connection so that we can use it.
	//Will cause problems if you cannot access the database, like from inside Swinburne's Network
	public static Connection connect(){

		Connection dbcon = null;

		//try and connect, throw an exception if the connection fails for whatever reason
		try {

			String dbURL1 = "jdbc:oracle:thin:sys as sysdba/oracle@ec2-52-62-243-187.ap-southeast-2.compute.amazonaws.com:1521:XE";
			dbcon = DriverManager.getConnection(dbURL1);

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return dbcon;
	}

	//execute the stored procedure that adds a new product to the database
	public static void addProduct(String productCategory, String productDescription, String productName, int productStock){

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

			//refresh the product arraylist
			DatabaseIO.productList.clear();
			DatabaseIO.getProducts();
		}   
	}

	//execute the stored procedure that adds a new sale record
	//when calling this method, make sure you send a java.sql.date into saleDate. This should get you there if you're using java.util.date:
	//https://stackoverflow.com/questions/25351760/jcalendar-to-sql-date-not-working
	public static void addSale(int productID, Date saleDate, int qtySold){

		//get the connection
		Connection con = connect();

		//prepare CallableStatement
		CallableStatement pStmt = null;

		try {
			//construct the statement
			pStmt = con.prepareCall("{call SYSTEM.ADDSALE(?,?,?)}");
			pStmt.setInt("pProductID", productID);
			pStmt.setDate("pSaleDate", saleDate);
			pStmt.setInt("pQtySold", qtySold);

			//execute the procedure
			pStmt.execute();

		} catch (SQLException ex) {
			//check for the specific exceptions we throw in the database
			//at this stage we only have error code 20001 and 20002
			if (ex.getErrorCode() == 20002) {
				System.err.println("Product stock level cannot go below 0");
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

			// refresh the sales list
			DatabaseIO.saleList.clear();
			DatabaseIO.getSales();
		}
	}

	//updates the database product record with the provided product object.
	//be careful, this doesn't really do any validation yet
	public static void updateProduct(Product updateProduct){

		//get the connection
		Connection con = connect();
		Statement stmt = null;

		try {				

			//construct the statement
			stmt = con.createStatement();
			String sql = "UPDATE SYSTEM.PRODUCT " +
					"SET \"Product_Category\"='" + updateProduct.productCategory + "'," +
					"PRODUCT_DESCRIPTION='" + updateProduct.productDescription + "'," +
					"PRODUCT_NAME='" + updateProduct.productName + "'," +
					"PRODUCT_STOCK=" + updateProduct.productStock + " " +
					"WHERE PRODUCT_ID=" + updateProduct.productID;
			System.out.println(sql);
			//execute the statement
			stmt.execute(sql);

		} catch (SQLException ex) {
			ex.printStackTrace();

		} finally {

			//make sure everything is closed
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					System.err.println("SQLException: " + e.getMessage());
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					System.err.println("SQLException: " + e.getMessage());
				}
			}
		}

	}

	//gets the products from the database, and populates the DatabaseIO.productList arraylist with product objects
	public static void getProducts(){

		productList.clear();
		Connection con = connect();
		Statement stmt = null;
		String query = "select *" + "from SYSTEM.PRODUCT";
		//fetch the records from database
		try {
			stmt = con.createStatement();
			//creates a result set containing all of the records we fetches
			ResultSet rs = stmt.executeQuery(query);
			//page through the result set 
			while (rs.next()) {
				//result set points to one line at a time, containing one record.
				//make variables with each column from the result set
				int productID = rs.getInt("PRODUCT_ID");
				String productCategory = rs.getString("Product_Category");
				String productDescription = rs.getString("PRODUCT_DESCRIPTION");
				String productName = rs.getString("PRODUCT_NAME");
				int productStock = rs.getInt("PRODUCT_STOCK");

				//make a new product object and add it to the databaseIO product arraylist
				DatabaseIO.productList.add(new Product(productID, productCategory, productDescription, productName, productStock));
			}
		} catch (SQLException ex) {

			ex.printStackTrace();

		} finally {

			//make sure everything is closed
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					System.err.println("SQLException: " + e.getMessage());
				}
			}
		}
	}

	//gets the sales records from the database and populates the DatabaseIO.salesList with sale objects
	public static void getSales(){
		Connection con = connect();
		Statement stmt = null;
		String query = "SELECT SALES.SALEID, SALES.PRODUCT_ID, PRODUCT.PRODUCT_NAME, SALES.SALE_DATE, SALES.QTYSOLD " +
				"FROM SYSTEM.SALES " +
				"JOIN SYSTEM.PRODUCT " +
				"ON SALES.PRODUCT_ID = PRODUCT.PRODUCT_ID";
		//fetch the records from database
		try {
			stmt = con.createStatement();
			//creates a result set containing all of the records we fetches
			ResultSet rs = stmt.executeQuery(query);
			//page through the result set 
			while (rs.next()) {
				//result set points to one line at a time, containing one record.
				//make variables with each column from the result set
				Integer saleID = rs.getInt("SALEID");
				Integer productID = rs.getInt("PRODUCT_ID");
				String productName = rs.getString("PRODUCT_NAME");
				Date saleDate = rs.getDate("SALE_DATE");
				Integer qtySold = rs.getInt("QTYSOLD");

				//make a new sale object and add it to the databaseIO sale arraylist
				DatabaseIO.saleList.add(new Sale(saleID, productID, productName, saleDate, qtySold));
			}
		} catch (SQLException ex) {

			ex.printStackTrace();

		} finally {

			//make sure everything is closed
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					System.err.println("SQLException: " + e.getMessage());
				}
			}
		}
	}	

	public static String[] getCategories() {
		if(DatabaseIO.productList.size() == 0){
			DatabaseIO.getProducts();
		}	

		ArrayList<String> categories = new ArrayList<String>();
		for(Product p : DatabaseIO.productList){
			if(!categories.contains(p.productCategory))
				categories.add(p.productCategory);
		}
		String[] categoryArray = categories.toArray(new String[categories.size()]);
		return categoryArray;
	}

	public static String[] getProductByCategory(String category)
	{
		ArrayList<String> products = new ArrayList<String>();
		Boolean found = false;
		for (String cat : getCategories())
		{
			if (category.equals(cat))
			{
				found = true;
				break;
			}
		}
		if (found)
		{
			for (Product product : productList)
			{
				if (product.productCategory.equals(category))
				{
					products.add(product.productName);
				}
			}
		}

		String[] productArray = products.toArray(new String[products.size()]);
		return productArray;
	}

	public static Product getProduct(String name)
	{
		for (Product product : productList)
		{
			if (product.productName.equals(name))
			{
				return product;
			}
		}
		return new Product();
	}
}
