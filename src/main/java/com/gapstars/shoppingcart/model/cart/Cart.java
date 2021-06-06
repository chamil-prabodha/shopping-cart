package com.gapstars.shoppingcart.model.cart;

import com.gapstars.shoppingcart.exception.CartException;
import com.gapstars.shoppingcart.model.product.Product;
import java.math.BigDecimal;

public interface Cart {
  void add(Product product) throws CartException;
  void add(Product product, int count) throws CartException;
  Product remove(String title) throws CartException;
  BigDecimal getTotalAmount();
  BigDecimal getTotalVAT();
  BigDecimal getShippingCost();
  void checkOut(BigDecimal shippingCost);
  void clear();
}
