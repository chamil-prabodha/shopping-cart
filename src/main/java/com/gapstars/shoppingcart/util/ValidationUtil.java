package com.gapstars.shoppingcart.util;

import com.gapstars.shoppingcart.exception.ValidationException;
import com.gapstars.shoppingcart.model.cart.Cart;
import com.gapstars.shoppingcart.model.customer.Customer;
import com.gapstars.shoppingcart.model.product.Product;
import java.math.BigDecimal;

public class ValidationUtil {
  private ValidationUtil() {}

  public static void validateCustomer(Customer customer) throws ValidationException {
    if (customer == null) {
      throw new ValidationException("customer should not be null");
    }
  }

  public static void validateProduct(Product product) throws ValidationException {
    if (product == null) {
      throw new ValidationException("product should not be null");
    }
  }

  public static void validateCart(Cart cart) throws ValidationException {
    if (cart == null) {
      throw new ValidationException("cart should not be null");
    }
  }

  public static void validateAmount(BigDecimal amount) throws ValidationException {
    if (amount == null) {
      throw new ValidationException("amount should not be null");
    }
  }
}
