package php;

import java.sql.Date;

public class Sale {
	
	Integer saleID;
	Integer productID;
	String productName;
	Date saleDate;
	Integer qtySold;
	
	Sale(Integer saleID, Integer productID, String productName, Date saleDate, Integer qtySold){
		this.saleID = saleID;
		this.productID = productID;
		this.productName = productName;
		this.saleDate = saleDate;
		this.qtySold = qtySold;
	}

}
