package com.gapstars.shoppingcart.model.customer;

import com.gapstars.shoppingcart.model.cart.Cart;

public class Customer {
  private final String name;
  private Cart cart;

  public Customer(String name) {
    this.name = name;
  }

  public Customer(String name, Cart cart) {
    this.name = name;
    this.cart = cart;
  }

  public String getName() {
    return name;
  }

  public Cart getCart() {
    return cart;
  }

  public void setCart(Cart cart) {
    this.cart = cart;
  }
}
