package com.github.ozelahmet.antipatterns.stubverify;

import com.github.ozelahmet.purchase.Customer;
import com.github.ozelahmet.purchase.Product;
import com.github.ozelahmet.purchase.Store;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class StubVerifyTest {

    /**
     * Line that is commented out shouldn't be checked here. It is not an outcome of the system under test. Removing
     * items shows that operation is sucessful and <code>getInventory</code> operation is performed. As a result, it
     * becomes an implemetation detail. See Chapter 5.1.3 from the book for more information.
     */
    @Test
    void purchaseSucceedsWhenEnoughInventory() {
        Customer customer = new Customer();
        Store store = mock(Store.class);
        when(store.getInventory(Product.SHAMPOO)).thenReturn(10);

        boolean success = customer.purchase(store, Product.SHAMPOO, 5);

        assertTrue(success);
        verify(store).removeInventory(Product.SHAMPOO, 5);
        // verify(store).getInventory(Product.SHAMPOO);
    }
}
