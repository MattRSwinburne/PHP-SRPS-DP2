package php;

import java.sql.Date;

public class Sale {
	
	Integer saleID;
	public Integer getSaleID() {
		return saleID;
	}

	public void setSaleID(Integer saleID) {
		this.saleID = saleID;
	}

	public Integer getProductID() {
		return productID;
	}

	public void setProductID(Integer productID) {
		this.productID = productID;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Date getSaleDate() {
		return saleDate;
	}

	public void setSaleDate(Date saleDate) {
		this.saleDate = saleDate;
	}

	public Integer getQtySold() {
		return qtySold;
	}

	public void setQtySold(Integer qtySold) {
		this.qtySold = qtySold;
	}

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
