package au.com.shopping.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import au.com.shopping.model.ProductDTO;

/**
 * Utility class for Shopping cart
 * @author Mahathi Kota
 *
 */
public class ShoppingCartUtil {

	private Map<String, Integer> shoppingCart = new HashMap<>();

	/**
	 * This utility method is used to read product information from the CSV configuration file
	 * @return
	 * @throws IOException
	 */
	public Map<String, ProductDTO> readProductInfo() throws IOException {
		
		String line = "";
		
		Map<String, ProductDTO> productMap = new HashMap<>();

		try (BufferedReader br = new BufferedReader(
				new InputStreamReader(getClass().getClassLoader().getResourceAsStream("PricingRule.csv")))) {
			line = br.readLine();

			while ((line = br.readLine()) != null) {

				String[] productInfo = line.split(",");

				ProductDTO product = new ProductDTO();
				product.setSKU(productInfo[0].trim());
				product.setName(productInfo[1].trim());
				product.setPrice(Double.parseDouble(productInfo[2].trim()));
				product.setMinimumProducts(Integer.parseInt(productInfo[3].trim()));
				product.setDiscountAmount(Double.parseDouble(productInfo[4].trim()));
				product.setFreeItem(productInfo[5].trim());
				product.setItemDiscount(Integer.parseInt(productInfo[6].trim()));
				productMap.put(product.getSKU(), product);
				shoppingCart.put(product.getSKU(), 0);
			}
		} catch (IOException e) {
			throw e;
		}
		return productMap;

	}
	
	

	/**
	 * Returns initial product cart
	 * @return
	 */
	public Map<String, Integer> getInitialCart() {
		return shoppingCart;
	}
}
