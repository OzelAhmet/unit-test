package com.github.ozelahmet.approaches;

import com.github.ozelahmet.purchase.Customer;
import com.github.ozelahmet.purchase.Product;
import com.github.ozelahmet.purchase.Store;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ClassicalTest {

    /**
     * In this approach, <code>Store</code> is not mocked because it is a part of behaviour, and it is not shared.
     * A unit can be a couple of classes in this approach. A unit can be thought as a behaviour.
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
}
