package com.github.ozelahmet.antipatterns.privatemethods;

import java.util.List;

public class OrderSecondVersion {
    private Customer customer;
    private List<Product> products;

    /**
     * By this way, we can test complex price calculation separately. There was a missing abstraction.
     * See Chapter 11.1.2 from the book for more information.
     */
    public String generateDescription() {
        PriceCalculator calculator = new PriceCalculator();
        int price = calculator.calculate(customer, products);

        return "Customer name: %s, " +
                "total number of products: %s, " +
                "total price: %s".formatted(customer.getName(), products.size(), price);
    }

}
