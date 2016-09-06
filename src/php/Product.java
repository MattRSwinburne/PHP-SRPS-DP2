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

	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}

	public String getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getProductStock() {
		return productStock;
	}

	public void setProductStock(int productStock) {
		this.productStock = productStock;
	}

}
