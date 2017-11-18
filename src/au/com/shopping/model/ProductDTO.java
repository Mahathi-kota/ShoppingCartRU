package au.com.shopping.model;

/**
 * This model class stores product information
 *
 */
public class ProductDTO {

	private String sku;
	private String name;
	private String freeItem;
	private int minimumProducts;
	private int itemDiscount;
	private double price;
	private double discountAmount;

	public String getSKU() {
		return sku;
	}

	public void setSKU(String sku) {
		this.sku = sku;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFreeItem() {
		return freeItem;
	}

	public void setFreeItem(String freeItem) {
		this.freeItem = freeItem;
	}

	public int getMinimumProducts() {
		return minimumProducts;
	}

	public void setMinimumProducts(int minimumProducts) {
		this.minimumProducts = minimumProducts;
	}

	public int getItemDiscount() {
		return itemDiscount;
	}

	public void setItemDiscount(int itemDiscount) {
		this.itemDiscount = itemDiscount;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(double discountAmount) {
		this.discountAmount = discountAmount;
	}

	@Override
	public String toString() {
		return "ProductDto [SKU=" + sku + ", name=" + name + ", freeItem=" + freeItem + ", minimumProducts="
				+ minimumProducts + ", itemDiscount=" + itemDiscount + ", price=" + price + ", discountAmount="
				+ discountAmount + "]";
	}

}
