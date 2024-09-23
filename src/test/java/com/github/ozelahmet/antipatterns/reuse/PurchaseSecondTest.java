package com.github.ozelahmet.antipatterns.reuse;

import com.github.ozelahmet.purchase.Customer;
import com.github.ozelahmet.purchase.Product;
import com.github.ozelahmet.purchase.Store;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PurchaseSecondTest {

    private Customer customer;
    private Store store;

    /**
     * Arrange sections for both test are defined once by using <code>@BeforeEach</code>.
     * However, this couses copling between tests. When we change the quantity added, this will affect both test cases.
     * This violates the isolation between tests.
     * Also, when we look at a single test, we cannot see the whole picture. We need to look at different places to
     * understand arrangements. This can be valid for assertions if the <code>@AfterEach</code> is used.
     */
    @BeforeEach
    void setUp() {
        store = new Store();
        store.addInventory(Product.SHAMPOO, 10);
        customer = new Customer();
    }

    @Test
    void purchaseSucceedsWhenEnoughInventory() {
        boolean success = customer.purchase(store, Product.SHAMPOO, 5);

        assertTrue(success);
        assertEquals(5, store.getInventory(Product.SHAMPOO));
    }

    @Test
    void purchaseFailsWhenNotEnoughInventory() {
        boolean success = customer.purchase(store, Product.SHAMPOO, 15);

        assertFalse(success);
        assertEquals(10, store.getInventory(Product.SHAMPOO));
    }

}
