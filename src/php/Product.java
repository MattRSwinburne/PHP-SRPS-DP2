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

	public Product()
	{
		productID = -1;
		productCategory = "";
		productDescription = "";
		productName = "";
		productStock = -1;
	}
	
	public Product(Product product)
	{
		this(product.productID, product.productCategory, product.productDescription, product.productName, product.productStock);
	}
}
