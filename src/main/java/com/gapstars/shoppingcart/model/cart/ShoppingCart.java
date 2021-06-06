package com.gapstars.shoppingcart.model.cart;

import com.gapstars.shoppingcart.exception.CartException;
import com.gapstars.shoppingcart.model.product.Product;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

public class ShoppingCart extends AbstractCart {
  private final Map<String, CartItem> items = new HashMap<>();
  private BigDecimal shippingCost;
  private BigDecimal totalAmount;
  private BigDecimal totalVAT;

  public ShoppingCart() {
    super("default");
  }

  public ShoppingCart(String cartName) {
    super(cartName);
  }

  @Override
  public synchronized void add(Product product) throws CartException {
    if (product == null) {
      throw new CartException("product should not be null");
    }

    CartItem item = items.get(product.getTitle());
    if (item == null) {
      item = new CartItem(product, 1);
      items.put(product.getTitle(), item);
      return;
    }
    item.incrementCount();
  }

  @Override
  public synchronized void add(Product product, int count) throws CartException {
    if (product == null) {
      throw new CartException("product should not be null");
    }

    CartItem item = items.get(product.getTitle());
    if (item == null) {
      item = new CartItem(product, count);
      items.put(product.getTitle(), item);
      return;
    }
    item.incrementCount(count);
  }

  @Override
  public synchronized Product remove(String title) throws CartException {
    CartItem item = items.get(title);
    if (item == null) {
      throw new CartException(MessageFormat.format("cannot find product {0} in cart", title));
    }

    if (item.getCount() == 1) {
      items.remove(title);
    } else {
      item.decrementCount();
    }
    return item.getProduct();
  }

  @Override
  public BigDecimal getTotalAmount() {
    return this.totalAmount;
  }

  @Override
  public BigDecimal getTotalVAT() {
    return this.totalVAT;
  }

  public BigDecimal calculateTotalAmount() {
    return items.values().stream()
        .reduce(BigDecimal.ZERO, (total, next) -> total.add(next.getProduct().getPrice().multiply(BigDecimal.valueOf(next.getCount()))),
            BigDecimal::add);
  }

  public BigDecimal calculateTotalVAT() {
    return items.values().stream()
        .reduce(BigDecimal.ZERO, (total, next) -> total.add(next.getProduct().getTaxPortion().multiply(BigDecimal.valueOf(next.getCount()))),
            BigDecimal::add);
  }

  @Override
  public BigDecimal getShippingCost() {
    return this.shippingCost;
  }

  @Override
  public void clear() {
    items.clear();
  }

  @Override
  public void checkOut(BigDecimal shippingCost) {
    this.shippingCost = shippingCost.setScale(2, RoundingMode.HALF_UP);
    this.totalAmount = calculateTotalAmount();
    this.totalVAT = calculateTotalVAT();
  }

  @Override
  public Map<String, CartItem> getItems() {
    return items;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("ShoppingCart{");
    sb.append("items=").append(items);
    sb.append('}');
    return sb.toString();
  }
}
