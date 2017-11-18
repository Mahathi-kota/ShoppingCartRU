package au.com.shopping.application;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;

import au.com.shopping.util.ShoppingCartUtil;

/**
 * Shopping cart application
 * 
 * @author Mahathi Kota
 *
 */
public class ShoppingCart {

	private ShoppingCart() {

	}

	public static void main(String[] args) throws Exception {

		ShoppingCartUtil util = new ShoppingCartUtil();

		Map<String, Integer> productsCart = util.getInitialCart();

		ProductCheckout checkOut = new ProductCheckout(util.readProductInfo());
		System.out.println("SKUs Scanned: ");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (String inputItem : br.readLine().split(",")) {
			checkOut.scanItem(productsCart, inputItem.trim());
		}

		System.out.println("Total Expected: $" + checkOut.calculate(productsCart));
		

	}

}
