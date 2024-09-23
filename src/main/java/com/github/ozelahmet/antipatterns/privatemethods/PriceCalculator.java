package com.github.ozelahmet.antipatterns.privatemethods;

import java.util.List;

public class PriceCalculator {

    public int calculate(Customer customer, List<Product> products) {
        int basePrice = 0; // Calculate based on products
        int discounts = 0; // Calculate based on customer
        int taxes = 0;     // Calculate based on products
        return basePrice - discounts + taxes;
    }
}
