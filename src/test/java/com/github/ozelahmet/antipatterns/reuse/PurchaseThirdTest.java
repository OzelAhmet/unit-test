package com.github.ozelahmet.antipatterns.reuse;

import com.github.ozelahmet.purchase.Customer;
import com.github.ozelahmet.purchase.Product;
import com.github.ozelahmet.purchase.Store;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PurchaseThirdTest {

    /**
     * In this class, common arrangements are done by private factory methods. Therefore, tests are isolated from each
     * other.
     */
    @Test
    void purchaseSucceedsWhenEnoughInventory() {
        Store store = createStoreWithInventory(Product.SHAMPOO, 10);
        Customer customer = createCustomer();

        boolean success = customer.purchase(store, Product.SHAMPOO, 5);

        assertTrue(success);
        assertEquals(5, store.getInventory(Product.SHAMPOO));
    }

    @Test
    void purchaseFailsWhenNotEnoughInventory() {
        Store store = createStoreWithInventory(Product.SHAMPOO, 10);
        Customer customer = createCustomer();

        boolean success = customer.purchase(store, Product.SHAMPOO, 15);

        assertFalse(success);
        assertEquals(10, store.getInventory(Product.SHAMPOO));
    }

    private Store createStoreWithInventory(Product product, int quantity) {
        Store store = new Store();
        store.addInventory(product, quantity);
        return store;
    }

    private Customer createCustomer() {
        return new Customer();
    }

}
