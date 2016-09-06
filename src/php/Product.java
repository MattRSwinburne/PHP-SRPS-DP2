package php;

public class Product {

	public int productID;
	public String productCategory;
	public String productDescription;
	public String productName;
	public int productStock;

	Product(int productID, String productCategory, String productDescription, String productName, int productStock){
		this.productID = productID;
		this.productCategory = productCategory;
		this.productDescription = productDescription;
		this.productName = productName;
		this.productStock = productStock;
	}

}
