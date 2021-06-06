package com.gapstars.shoppingcart.service;

import com.gapstars.shoppingcart.model.cart.Cart;
import com.gapstars.shoppingcart.model.customer.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopCustomerService implements CustomerService {
  private static final Logger logger = LoggerFactory.getLogger(ShopCustomerService.class);

  private final CartService cartService;

  @Autowired
  public ShopCustomerService(CartService cartService) {
    this.cartService = cartService;
  }

  @Override
  public Customer createCustomer(String name) {
    logger.info("creating customer {}", name);
    return new Customer(name);
  }

  public Customer createCustomer(String name, boolean cartRequired) {
    if (cartRequired) {
      Cart cart = cartService.createNewCart();
      return new Customer(name, cart);
    }
    return createCustomer(name);
  }
}
