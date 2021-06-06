package com.gapstars.shoppingcart.service;

import com.gapstars.shoppingcart.exception.CartException;
import com.gapstars.shoppingcart.exception.ValidationException;
import com.gapstars.shoppingcart.model.cart.Cart;
import com.gapstars.shoppingcart.model.product.Product;
import java.math.BigDecimal;

public interface CartService {

  void addProduct(Cart cart, Product product) throws ValidationException, CartException;
  void addProduct(Cart cart, Product product, int count) throws ValidationException, CartException;
  Cart createNewCart(String name);
  Cart createNewCart();
  Cart clearCart(Cart cart) throws ValidationException;
  void checkOut(Cart cart, BigDecimal shippingCost) throws ValidationException;

}
