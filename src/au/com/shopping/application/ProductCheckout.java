package au.com.shopping.application;

import java.util.HashMap;
import java.util.Map;

import au.com.shopping.model.ProductDTO;

/**
 * This class is used to scan products and calculate
 * 
 * @author Mahathi Kota
 *
 */
public class ProductCheckout {

	private Map<String, ProductDTO> productAndPricingRule = new HashMap<>();
	
	
	public ProductCheckout(Map<String, ProductDTO> productAndPricingRule) {
		this.productAndPricingRule = productAndPricingRule;
	}

	/**
	 * Scans items and updates the product cart
	 * 
	 * @param productsCart
	 * @param item
	 */
	public void scanItem(Map<String, Integer> productsCart, String item) {
		
		//Check if scanned product is valid and increment the quantity
		if(null != productsCart.get(item)){
			
			int cartItemCount = productsCart.get(item) + 1;
			
			//Update cart with new quantity
			productsCart.put(item, cartItemCount);
		}
		
	}

	/**
	 * This method is used to calculate the items present in the cart
	 * 
	 * @param productsCart
	 */
	public double calculate(Map<String, Integer> productsCart) {
		
		//Initial amount
		double totalAmount = 0.0;
		
		//Copy products cart to new map and use it for calculation
		Map<String, Integer> productCartForCalculation = productsCart;

		//Check if there are any free items in the product cart and remove from the final product cart 
		for (Map.Entry<String, Integer> entry : productsCart.entrySet()) {
			removeFreeItem(entry.getKey(), entry.getValue(), productCartForCalculation);
		}

		//Calculate product cart for each item
		for (Map.Entry<String, Integer> product : productCartForCalculation.entrySet()) {
			totalAmount = totalAmount + calulateProductAmount(product.getValue(), product.getKey());
		}

		//System.out.println("Total Expected: $" + totalAmount);
		return totalAmount;
	}

	/**
	 * 
	 * Helper method to calculate final amount for each product
	 * @param quantity
	 * @param productName
	 * @return
	 */
	private double calulateProductAmount(int quantity, String productName) {
		
		ProductDTO product = productAndPricingRule.get(productName);
		
		int finalItemCountForCalculation = quantity;
		
		//Check if minimun products are available in cart 
		if (quantity >= product.getMinimumProducts()) {
			
			//Apply discount to product from pricing rule
			product.setPrice(product.getPrice() - product.getDiscountAmount());
			
			//Check if there is any deal for selected product and apply discount/remove items for calculation
			if (0 != product.getItemDiscount()) {
				finalItemCountForCalculation = quantity - quantity / product.getMinimumProducts();
			}
		}
		
		//Calculate amount after applying all discounts/deal and return total price for the product
		return finalItemCountForCalculation * product.getPrice();
	}

	/**
	 * Helper method to remove free items from the cart if present
	 * 
	 * @param productName
	 * @param cartCount
	 * @param finalProductCartMap
	 */
	private void removeFreeItem(String productName, int cartCount, Map<String, Integer> finalProductCartMap) {

		//Retrieve product information from pricing rule map
		ProductDTO product = productAndPricingRule.get(productName);

		//Check if free items exists in the products list and also check if that product is added to cart or not
		if (null != finalProductCartMap.get(product.getFreeItem()) && cartCount > 0) {

			String freeItem = product.getFreeItem();
			Integer productCount = finalProductCartMap.get(freeItem);
			
			//Checks if the free product item is present in the final carts list and if presents removes item from the final cart map
			if (productCount > 0) {
				//if exists remove from the cart
				productCount--;
				
				//upadte final product cart with new quantity 
				finalProductCartMap.put(freeItem, productCount);
			}
		}
	}

}
