package com.github.ozelahmet.antipatterns.multilineact;

import com.github.ozelahmet.purchase.Customer;
import com.github.ozelahmet.purchase.Product;
import com.github.ozelahmet.purchase.Store;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PurchaseTest {


    /**
     * Commented out lines of code shouldn't be in this test. <code>removeInventory</code> operation must be done in
     * <code>purchase</code> operation. It is a natural outcome of purchase operation. Multiple act statements, usually,
     * indicates a design problem in the public API of the <code>Customer</code> class.
     */
    @Test
    void purchaseSucceedsWhenEnoughInventory() {
        // Arrange
        Store store = new Store();
        store.addInventory(Product.SHAMPOO, 10);
        Customer customer = new Customer();

        // Act
        boolean success = customer.purchase(store, Product.SHAMPOO, 5);
        // if (success) {
        //     store.removeInventory(Product.SHAMPOO, 5);
        // }

        // Assert
        assertTrue(success);
        assertEquals(5, store.getInventory(Product.SHAMPOO));
    }


}