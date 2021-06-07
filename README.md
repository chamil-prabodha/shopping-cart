[comment]: <> (Write readme tomorrow)

# Shopping Cart

---
## Requirements

### Design
- A customer can have zero or one shopping carts.
- A customer can add one or more of each different products to each shopping cart.
- Every product must have a title, name, price and tax.
- A shopping cart has a total amount, total VAT and shipment costs.

### Implementation
- Create two customers.
- Have the first customer add one product to his shopping cart.
- Recalculate the different amounts in the shopping cart.
- Have the second customer add two different products, with different quantities to
his shopping cart.
- Recalculate the different amounts in the shopping cart.

---
## Diagrams
![Class Diagram](images/Gapstars-Shopping%20Cart.png)

---
## Assumptions
- The requirements do not specifically say if the application should be a backend API. 
  Therefore, assuming a command line application would suffice the requirements
- There is only one type of customer and the ability to extend the behavior of a customer is not required.
- Negative values for product amounts and tax percentage are possible

---
## Notes
- The cart keeps a map of items added by the customer. 
  Map consists of product title and product, count relationship
- When adding products to the cart, the cart checks if the product is already existing in 
the items using the title of the product.
- Title is unique to a product.
- When a different product(name, amount is different) with the same title of a product already existing in the cart
is added, the cart will identify as the existing product and increments the count.

---
## Executing the application

The project uses gradle and java 11. Logging mode can be changed by changing 
`logging.level.root` property in `src/maing/resources/application.properties` 

### Build
`./gradlew clean build`

### Test
`./gradlew clean test`

### Execute
` java -jar build/libs/shopping-cart-0.0.1-SNAPSHOT.jar`


