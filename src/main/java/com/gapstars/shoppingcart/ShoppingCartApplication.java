package com.gapstars.shoppingcart;

import com.gapstars.shoppingcart.model.customer.Customer;
import com.gapstars.shoppingcart.model.product.GeneralProduct;
import com.gapstars.shoppingcart.model.product.Product;
import com.gapstars.shoppingcart.service.CartService;
import com.gapstars.shoppingcart.service.CustomerService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
@Profile("!test")
public class ShoppingCartApplication implements CommandLineRunner {
	private static final Logger logger = LoggerFactory.getLogger(ShoppingCartApplication.class);

	@Autowired
	private CartService cartService;
	@Autowired
	private  CustomerService customerService;


	public static void main(String[] args) {
		SpringApplication.run(ShoppingCartApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		try {
			String customerName;

			logger.info("starting the shopping cart...");
			logger.info("creating products...");
			List<Product> products = createProducts();

			customerName = "customer 1";
			Customer customer1 = customerService.createCustomer(customerName, true);
			logger.info("successfully created customer {}", customer1.getName());

			customerName = "customer 2";
			Customer customer2 = customerService.createCustomer(customerName, true);
			logger.info("successfully created customer {}", customer2.getName());

			cartService.addProduct(customer1.getCart(), products.get(0));
			cartService.checkOut(customer1.getCart(), BigDecimal.valueOf(12.999));
			logger.info("customer {} show cart: {}", customer1.getName(), customer1.getCart());
			logger.info("customer {} total price: {}", customer1.getName(), customer1.getCart().getTotalAmount());
			logger.info("customer {} total VAT: {}", customer1.getName(), customer1.getCart().getTotalVAT());
			logger.info("customer {} shipping cost: {}", customer1.getName(), customer1.getCart().getShippingCost());

			cartService.addProduct(customer2.getCart(), products.get(1));
			cartService.addProduct(customer2.getCart(), products.get(2));
			cartService.addProduct(customer2.getCart(), products.get(3), 1);
			cartService.checkOut(customer2.getCart(), BigDecimal.valueOf(20.111));
			logger.info("customer {} show cart: {}", customer2.getName(), customer2.getCart());
			logger.info("customer {} total price: {}", customer2.getName(), customer2.getCart().getTotalAmount());
			logger.info("customer {} total VAT: {}", customer2.getName(), customer2.getCart().getTotalVAT());
			logger.info("customer {} shipping cost: {}", customer2.getName(), customer2.getCart().getShippingCost());
		} catch (Exception e) {
			logger.error("an exception occurred", e);
		}
	}

	private List<Product> createProducts() {
		List<Product> products = new ArrayList<>();
		products.add(new GeneralProduct("prod1", "Product 1", BigDecimal.valueOf(12.21), BigDecimal.valueOf(15.10)));
		products.add(new GeneralProduct("prod2", "Product 2", BigDecimal.valueOf(10), BigDecimal.valueOf(10)));
		products.add(new GeneralProduct("prod3", "Product 3", BigDecimal.valueOf(20), BigDecimal.valueOf(10)));
		products.add(new GeneralProduct("prod2", "Product 4", BigDecimal.valueOf(20), BigDecimal.valueOf(10)));
		return products;
	}
}
