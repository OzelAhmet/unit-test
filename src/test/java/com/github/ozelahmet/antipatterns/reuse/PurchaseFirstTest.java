package com.github.ozelahmet.antipatterns.reuse;

import com.github.ozelahmet.purchase.Customer;
import com.github.ozelahmet.purchase.Product;
import com.github.ozelahmet.purchase.Store;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PurchaseFirstTest {

    /**
     * Arrange sections for both tests are same. They can be defined once and reused.
     */
    @Test
    void purchaseSucceedsWhenEnoughInventory() {
        Store store = new Store();
        store.addInventory(Product.SHAMPOO, 10);
        Customer customer = new Customer();

        boolean success = customer.purchase(store, Product.SHAMPOO, 5);

        assertTrue(success);
        assertEquals(5, store.getInventory(Product.SHAMPOO));
    }

    @Test
    void purchaseFailsWhenNotEnoughInventory() {
        Store store = new Store();
        store.addInventory(Product.SHAMPOO, 10);
        Customer customer = new Customer();

        boolean success = customer.purchase(store, Product.SHAMPOO, 15);

        assertFalse(success);
        assertEquals(10, store.getInventory(Product.SHAMPOO));
    }

}
