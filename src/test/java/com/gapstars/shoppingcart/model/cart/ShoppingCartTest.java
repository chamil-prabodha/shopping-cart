package com.gapstars.shoppingcart.model.cart;

import static org.junit.jupiter.api.Assertions.*;

import com.gapstars.shoppingcart.exception.CartException;
import com.gapstars.shoppingcart.model.product.GeneralProduct;
import com.gapstars.shoppingcart.model.product.Product;
import java.math.BigDecimal;
import java.math.RoundingMode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ShoppingCartTest {
  private Cart cart;

  @BeforeEach
  public void init() {
    cart = new ShoppingCart("test");
  }

  @Test
  void whenOneProductAdded_thenShowCorrectValues() {
    Product product = new GeneralProduct("product 1", "product 1", BigDecimal.valueOf(10.00), BigDecimal.valueOf(10.00));
    assertDoesNotThrow(() -> cart.add(product));
    AbstractCart abstractCart = (AbstractCart) cart;
    assertFalse(abstractCart.getItems().isEmpty());
    assertEquals(1, abstractCart.getItems().get("product 1").getCount());
    assertEquals(product, abstractCart.getItems().get("product 1").getProduct());
  }

  @Test
  void whenMultipleProductsAdded_thenShowCorrectValues() {
    Product product = new GeneralProduct("product 1", "product 1", BigDecimal.valueOf(10.00), BigDecimal.valueOf(10.00));
    assertDoesNotThrow(() -> cart.add(product, 2));
    AbstractCart abstractCart = (AbstractCart) cart;
    assertFalse(abstractCart.getItems().isEmpty());
    assertEquals(2, abstractCart.getItems().get("product 1").getCount());
    assertEquals(product, abstractCart.getItems().get("product 1").getProduct());
  }

  @Test
  void whenOneProductAddedToNonEmptyCart_thenShowCorrectValues() {
    Product product1 = new GeneralProduct("product 1", "product 1", BigDecimal.valueOf(10.00), BigDecimal.valueOf(10.00));
    assertDoesNotThrow(() -> cart.add(product1, 2));

    Product product2 = new GeneralProduct("product 1", "product 1", BigDecimal.valueOf(10.00), BigDecimal.valueOf(10.00));
    assertDoesNotThrow(() -> cart.add(product2));

    AbstractCart abstractCart = (AbstractCart) cart;
    assertFalse(abstractCart.getItems().isEmpty());
    assertEquals(3, abstractCart.getItems().get("product 1").getCount());
    assertEquals(product1, abstractCart.getItems().get("product 1").getProduct());
  }

  @Test
  void whenProductsAddedToNonEmptyCart_thenShowCorrectValues() {
    Product product1 = new GeneralProduct("product 1", "product 1", BigDecimal.valueOf(10.00), BigDecimal.valueOf(10.00));
    assertDoesNotThrow(() -> cart.add(product1, 2));

    Product product2 = new GeneralProduct("product 1", "product 1", BigDecimal.valueOf(10.00), BigDecimal.valueOf(10.00));
    assertDoesNotThrow(() -> cart.add(product2, 2));

    AbstractCart abstractCart = (AbstractCart) cart;
    assertFalse(abstractCart.getItems().isEmpty());
    assertEquals(4, abstractCart.getItems().get("product 1").getCount());
    assertEquals(product1, abstractCart.getItems().get("product 1").getProduct());
  }

  @Test
  void whenNullProductAdded_thenThrowsCartException() {
    assertThrows(CartException.class, () -> cart.add(null));
  }

  @Test
  void whenMultipleNullProductsAdded_thenThrowsCartException() {
    assertThrows(CartException.class, () -> cart.add(null, 2));
  }

  @Test
  void whenProductRemovedFromSingleItemCart_thenShowCorrectValues() throws CartException {
    Product product = new GeneralProduct("product 1", "product 1", BigDecimal.valueOf(10.00), BigDecimal.valueOf(10.00));
    cart.add(product);
    assertDoesNotThrow(() -> cart.remove("product 1"));
    AbstractCart abstractCart = (AbstractCart) cart;
    assertTrue(abstractCart.getItems().isEmpty());
  }

  @Test
  void whenProductRemoved_thenShowCorrectValues() throws CartException {
    Product product = new GeneralProduct("product 1", "product 1", BigDecimal.valueOf(10.00), BigDecimal.valueOf(10.00));
    cart.add(product, 2);
    assertDoesNotThrow(() -> cart.remove("product 1"));
    AbstractCart abstractCart = (AbstractCart) cart;
    assertFalse(abstractCart.getItems().isEmpty());
    assertEquals(1, abstractCart.getItems().get("product 1").getCount());
    assertEquals(product, abstractCart.getItems().get("product 1").getProduct());
  }

  @Test
  void whenProductRemovedFromEmptyCart_thenThrowsCartException() {
    assertThrows(CartException.class, () -> cart.remove("product 1"));
  }

  @Test
  void whenCheckout_thenShowCorrectValues() throws CartException {
    Product product1 = new GeneralProduct("product 1", "product 1", BigDecimal.valueOf(10.00), BigDecimal.valueOf(10.00));
    cart.add(product1, 2);

    Product product2 = new GeneralProduct("product 2", "product 2", BigDecimal.valueOf(10.00), BigDecimal.valueOf(10.00));
    cart.add(product2, 1);

    cart.checkOut(BigDecimal.valueOf(10.00));
    assertEquals(BigDecimal.valueOf(30.00).setScale(2, RoundingMode.HALF_UP), cart.getTotalAmount());
    assertEquals(BigDecimal.valueOf(3.00).setScale(2, RoundingMode.HALF_UP), cart.getTotalVAT());
    assertEquals(BigDecimal.valueOf(10.00).setScale(2, RoundingMode.HALF_UP), cart.getShippingCost());
  }

  @Test
  void whenCartIsCleared_thenShowCorrectValues() throws CartException {
    Product product1 = new GeneralProduct("product 1", "product 1", BigDecimal.valueOf(10.00), BigDecimal.valueOf(10.00));
    cart.add(product1, 2);

    Product product2 = new GeneralProduct("product 2", "product 2", BigDecimal.valueOf(10.00), BigDecimal.valueOf(10.00));
    cart.add(product2, 1);

    cart.clear();
    AbstractCart abstractCart = (AbstractCart) cart;
    assertTrue(abstractCart.getItems().isEmpty());
  }
}