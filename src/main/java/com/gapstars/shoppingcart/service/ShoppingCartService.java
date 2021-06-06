package com.gapstars.shoppingcart.service;

import com.gapstars.shoppingcart.exception.CartException;
import com.gapstars.shoppingcart.exception.ValidationException;
import com.gapstars.shoppingcart.model.cart.AbstractCart;
import com.gapstars.shoppingcart.model.cart.Cart;
import com.gapstars.shoppingcart.model.cart.ShoppingCart;
import com.gapstars.shoppingcart.model.product.Product;
import com.gapstars.shoppingcart.util.ValidationUtil;
import java.math.BigDecimal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartService implements CartService {
  private static final Logger logger = LoggerFactory.getLogger(ShoppingCartService.class);
  private static final String CART_INFO_LOG = "cart info: {}";

  @Override
  public void addProduct(Cart cart, Product product) throws ValidationException, CartException {
    validateCartInput(cart, product);
    String cartName = ((AbstractCart) cart).getCartName();
    logger.info("adding product {} to shopping cart {}", product.getTitle(), cartName);
    logger.debug("product info: {}", product);
    cart.add(product);
    logger.info("successfully added product {} to shopping cart {}", product.getTitle(), cartName);
    logger.debug(CART_INFO_LOG, cart);
  }

  @Override
  public void addProduct(Cart cart, Product product, int count) throws ValidationException, CartException {
    validateCartInput(cart, product);
    String cartName = ((AbstractCart) cart).getCartName();
    logger.info("adding product {} to shopping cart {}", product.getTitle(), cartName);
    logger.debug("product info: {}", product);
    cart.add(product, count);
    logger.info("successfully added product {} to shopping cart {}", product.getTitle(), cartName);
    logger.debug(CART_INFO_LOG, cart);
  }

  @Override
  public Cart createNewCart(String name) {
    logger.info("creating new shopping cart {}", name);
    return new ShoppingCart(name);
  }

  @Override
  public Cart createNewCart() {
    logger.info("creating new default shopping cart");
    return new ShoppingCart();
  }

  @Override
  public Cart clearCart(Cart cart) throws ValidationException {
    validateCartInput(cart);
    logger.info("clearing shopping cart");
    logger.debug(CART_INFO_LOG, cart);
    cart.clear();
    logger.info("shopping cart cleared");
    return cart;
  }

  @Override
  public void checkOut(Cart cart, BigDecimal shippingCost) throws ValidationException {
    logger.info("moving cart to checkout");
    validateCartInput(cart, shippingCost);
    cart.checkOut(shippingCost);
  }

  private void validateCartInput(Cart cart, Product product) throws ValidationException {
    ValidationUtil.validateProduct(product);
    ValidationUtil.validateCart(cart);
  }

  private void validateCartInput(Cart cart, BigDecimal amount) throws ValidationException {
    ValidationUtil.validateCart(cart);
    ValidationUtil.validateAmount(amount);
  }

  private void validateCartInput(Cart cart) throws ValidationException {
    ValidationUtil.validateCart(cart);
  }
}
