package com.gapstars.shoppingcart.model.product;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class GeneralProduct extends Product {
  public GeneralProduct(String title, String name, BigDecimal price, BigDecimal taxPercent) {
    this.title = title;
    this.name = name;
    this.price = price.setScale(2, RoundingMode.HALF_UP);
    this.taxPercent = taxPercent.setScale(2,RoundingMode.HALF_UP);
  }

  @Override
  public BigDecimal getTotalGrossPrice() {
    BigDecimal taxPortion = getTaxPortion();
    return price.add(taxPortion);
  }

  @Override
  public BigDecimal getTaxPortion() {
    return price.multiply(taxPercent).divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
  }


}
