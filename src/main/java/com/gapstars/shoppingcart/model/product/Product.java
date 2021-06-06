package com.gapstars.shoppingcart.model.product;

import java.math.BigDecimal;

public abstract class Product {
  String title;
  String name;
  BigDecimal price;
  BigDecimal taxPercent;

  public abstract BigDecimal getTotalGrossPrice();
  public abstract BigDecimal getTaxPortion();

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public BigDecimal getTaxPercent() {
    return taxPercent;
  }

  public void setTaxPercent(BigDecimal taxPercent) {
    this.taxPercent = taxPercent;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("Product{");
    sb.append("title='").append(title).append('\'');
    sb.append(", name='").append(name).append('\'');
    sb.append(", price=").append(price);
    sb.append(", taxPercent=").append(taxPercent);
    sb.append('}');
    return sb.toString();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (!(o instanceof Product)) {
      return false;
    }

    Product product = (Product) o;

    return new org.apache.commons.lang3.builder.EqualsBuilder().append(title, product.title).append(name, product.name).append(price, product.price)
        .append(taxPercent, product.taxPercent).isEquals();
  }

  @Override
  public int hashCode() {
    return new org.apache.commons.lang3.builder.HashCodeBuilder(17, 37).append(title).append(name).append(price).append(taxPercent).toHashCode();
  }
}
