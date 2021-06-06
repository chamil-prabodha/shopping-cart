package com.gapstars.shoppingcart.model.cart;

import com.gapstars.shoppingcart.model.product.Product;
import java.util.Map;

public abstract class AbstractCart implements Cart {
  private String cartName;
  public abstract Map<String, CartItem> getItems();

  public AbstractCart(String cartName) {
    this.cartName = cartName;
  }

  public String getCartName() {
    return cartName;
  }

  static final class CartItem {
    private Product product;
    private int count;

    CartItem(Product product, int count) {
      this.product = product;
      this.count = count;
    }

    public Product getProduct() {
      return product;
    }

    public int getCount() {
      return count;
    }

    public void incrementCount() {
      this.count++;
    }

    public void incrementCount(int inc) {
      this.count += inc;
    }

    public void decrementCount() {
      this.count--;
    }

    @Override
    public String toString() {
      final StringBuilder sb = new StringBuilder("CartItem{");
      sb.append("product=").append(product.getTitle());
      sb.append(", count=").append(count);
      sb.append('}');
      return sb.toString();
    }
  }
}
