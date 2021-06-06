package com.gapstars.shoppingcart.service;

import static org.junit.jupiter.api.Assertions.*;

import com.gapstars.shoppingcart.exception.CartException;
import com.gapstars.shoppingcart.exception.ValidationException;
import com.gapstars.shoppingcart.model.cart.AbstractCart;
import com.gapstars.shoppingcart.model.cart.Cart;
import com.gapstars.shoppingcart.model.cart.ShoppingCart;
import com.gapstars.shoppingcart.model.product.GeneralProduct;
import com.gapstars.shoppingcart.model.product.Product;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

@EnableAutoConfiguration
@SpringBootTest
@ContextConfiguration(classes = ShoppingCartService.class)
@ActiveProfiles("test")
class ShoppingCartServiceIntegrationTest {

  @Autowired
  @Qualifier("shoppingCartService")
  private CartService cartService;

  @Test
  void whenAddOneProductWithValidInputs_thenSuccessful() {
    Cart cart = new ShoppingCart("test");
    Product product = new GeneralProduct("product 1", "product 1", BigDecimal.valueOf(10.00), BigDecimal.valueOf(10.00));
    assertDoesNotThrow(() -> cartService.addProduct(cart, product));
  }

  @Test
  void whenAddOneProductWithNullCart_thenThrowsValidationException() {
    Product product = new GeneralProduct("product 1", "product 1", BigDecimal.valueOf(10.00), BigDecimal.valueOf(10.00));
    assertThrows(ValidationException.class, () -> cartService.addProduct(null, product));
  }

  @Test
  void whenAddOneProductWithNullProduct_thenThrowsValidationException() {
    Cart cart = new ShoppingCart("test");
    assertThrows(ValidationException.class, () -> cartService.addProduct(cart, null));
  }

  @Test
  void whenAddMultipleProductsWithValidInputs_thenSuccessful() {
    Cart cart = new ShoppingCart("test");
    Product product = new GeneralProduct("product 1", "product 1", BigDecimal.valueOf(10.00), BigDecimal.valueOf(10.00));
    assertDoesNotThrow(() -> cartService.addProduct(cart, product, 2));
  }

  @Test
  void whenAddMultipleProductsWithNullCart_thenThrowsValidationException() {
    Product product = new GeneralProduct("product 1", "product 1", BigDecimal.valueOf(10.00), BigDecimal.valueOf(10.00));
    assertThrows(ValidationException.class, () -> cartService.addProduct(null, product, 2));
  }

  @Test
  void whenAddMultipleProductsWithNullProduct_thenThrowsValidationException() {
    Cart cart = new ShoppingCart("test");
    assertThrows(ValidationException.class, () -> cartService.addProduct(cart, null, 2));
  }

  @Test
  void whenCreateNewCart_thenShowCorrectValues() {
    Cart cart = assertDoesNotThrow(() -> cartService.createNewCart("test"));
    AbstractCart abstractCart = (AbstractCart) cart;
    assertNotNull(cart);
    assertEquals("test", abstractCart.getCartName());
    assertTrue(abstractCart.getItems().isEmpty());
  }

  @Test
  void whenCreateNewDefaultCart_thenShowCorrectValues() {
    Cart cart = assertDoesNotThrow(() -> cartService.createNewCart());
    AbstractCart abstractCart = (AbstractCart) cart;
    assertNotNull(cart);
    assertEquals("default", abstractCart.getCartName());
    assertTrue(abstractCart.getItems().isEmpty());
  }

  @Test
  void whenClearCart_thenShowCorrectValues() throws CartException {
    AbstractCart cart = new ShoppingCart("test");
    Product product = new GeneralProduct("product 1", "product 1", BigDecimal.valueOf(10.00), BigDecimal.valueOf(10.00));
    cart.add(product);

    assertDoesNotThrow(() -> cartService.clearCart(cart));
    assertTrue(cart.getItems().isEmpty());
  }

  @Test
  void whenCheckout_thenShowCorrectValues() throws CartException {
    AbstractCart cart = new ShoppingCart("test");
    Product product = new GeneralProduct("product 1", "product 1", BigDecimal.valueOf(10.00), BigDecimal.valueOf(10.00));
    cart.add(product);

    assertDoesNotThrow(() -> cartService.checkOut(cart, BigDecimal.ONE));
  }

  @Test
  void whenCheckoutWithNullCart_thenThrowsValidationException() {
    assertThrows(ValidationException.class, () -> cartService.checkOut(null, BigDecimal.ONE));
  }

  @Test
  void whenCheckoutWithNullShippingCost_thenThrowsValidationException() throws CartException {
    AbstractCart cart = new ShoppingCart("test");
    Product product = new GeneralProduct("product 1", "product 1", BigDecimal.valueOf(10.00), BigDecimal.valueOf(10.00));
    cart.add(product);

    assertThrows(ValidationException.class, () -> cartService.checkOut(cart, null));
  }
}