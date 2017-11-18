package au.com.shopping.application;


import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import au.com.shopping.application.ProductCheckout;
import au.com.shopping.util.ShoppingCartUtil;

public class ShoppingCartTest {
    private String input1;
    private String input2;
    private String input3;
    
    ProductCheckout checkOut=null;
    Map<String, Integer> productsCart=null;

	// Local variables become instance variables
 
    @Before
    public void setUp() {
		ShoppingCartUtil util = new ShoppingCartUtil();
		productsCart = util.getInitialCart();
		try {
			checkOut = new ProductCheckout(util.readProductInfo());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		input1= "atv, ipd, ipd, atv, ipd, ipd, ipd";
		input2= "atv, atv, atv, vga";
		input3= "mbp, vga, ipd";
		
    }
    
    @Test
    public void testCondition1() {
    	for (String inputItem : input1.split(",")) {
			checkOut.scanItem(productsCart, inputItem.trim());
		}
        assertTrue("For Input atv, ipd, ipd, atv, ipd, ipd, ipd is success"  , 2718.95==checkOut.calculate(productsCart));
    }
 
    @Test
    public void testCondition2() {
    	
    	for (String inputItem : input2.split(",")) {
			checkOut.scanItem(productsCart, inputItem.trim());
		}
        assertTrue("For Input atv, atv, atv, vga is success"  , 249.00==checkOut.calculate(productsCart));
    }
 
    @Test
    public void testCondition3() {
    	for (String inputItem : input3.split(",")) {
			checkOut.scanItem(productsCart, inputItem.trim());
		}
        assertTrue("For Input mbp, vga, ipd is success"  , 1949.98==checkOut.calculate(productsCart));    }
}

