package com.gapstars.shoppingcart.service;

import com.gapstars.shoppingcart.model.customer.Customer;

public interface CustomerService {
  Customer createCustomer(String name);
  Customer createCustomer(String name, boolean cartRequired);
}
