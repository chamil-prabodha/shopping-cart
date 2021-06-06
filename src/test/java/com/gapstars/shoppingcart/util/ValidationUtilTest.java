package com.gapstars.shoppingcart.util;

import static org.junit.jupiter.api.Assertions.*;

import com.gapstars.shoppingcart.exception.ValidationException;
import com.gapstars.shoppingcart.model.cart.ShoppingCart;
import com.gapstars.shoppingcart.model.customer.Customer;
import com.gapstars.shoppingcart.model.product.GeneralProduct;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

class ValidationUtilTest {

  @Test
  void whenNonNullCustomer_thenSuccessful() {
    assertDoesNotThrow(() -> ValidationUtil.validateCustomer(new Customer("test")));
  }

  @Test
  void whenNullCustomer_thenThrowsValidationException() {
    assertThrows(ValidationException.class, () -> ValidationUtil.validateCustomer(null));
  }

  @Test
  void whenNonNullCart_thenSuccessful() {
    assertDoesNotThrow(() -> ValidationUtil.validateCart(new ShoppingCart("test")));
  }

  @Test
  void whenNullCart_thenThrowsValidationException() {
    assertThrows(ValidationException.class, () -> ValidationUtil.validateCart(null));
  }

  @Test
  void whenNonNullProduct_thenSuccessful() {
    assertDoesNotThrow(() -> ValidationUtil.validateProduct(new GeneralProduct("test", "test", BigDecimal.ONE, BigDecimal.ONE)));
  }

  @Test
  void whenNullProduct_thenThrowsValidationException() {
    assertThrows(ValidationException.class, () -> ValidationUtil.validateProduct(null));
  }

  @Test
  void whenNonNullAmount_thenSuccessful() {
    assertDoesNotThrow(() -> ValidationUtil.validateAmount(BigDecimal.valueOf(1.0)));
  }

  @Test
  void whenNullAmount_thenThrowsValidationException() {
    assertThrows(ValidationException.class, () -> ValidationUtil.validateAmount(null));
  }
}