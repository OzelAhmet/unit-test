package com.github.ozelahmet.antipatterns.privatemethods;

import java.util.List;

public class Order {
    private Customer customer;
    private List<Product> products;

    public String generateDescription() {
        return "Customer name: %s, " +
                "total number of products: %s, " +
                "total price: %s".formatted(customer.getName(), products.size(), this.getPrice());
    }

    //Complex private method
    private int getPrice() {
        int basePrice = 0; // Calculate based on products
        int discounts = 0; // Calculate based on customer
        int taxes = 0;     // Calculate based on products
        return basePrice - discounts + taxes;
    }
}
