package com.github.ozelahmet.antipatterns.exposingprivate;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    /**
     * Because we cannot check private state of customer, we check the <code>promote</code> operation over public API.
     * <code>getDiscount</code> uses private state. Notice that test structure is a little bit different here.
     */
    @Test
    void promotingIncreasesDiscount() {
        Customer customer = new Customer();

        assertEquals(0, customer.getDiscount());

        customer.promote();

        assertEquals(0.05, customer.getDiscount());
    }
}