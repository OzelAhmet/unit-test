package com.github.ozelahmet.approaches;

import com.github.ozelahmet.purchase.Customer;
import com.github.ozelahmet.purchase.Product;
import com.github.ozelahmet.purchase.Store;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class LondonTest {

    /**
     * In this approach, all dependency classes are mocked. A unit is a class according to this approach. In this
     * approach, tests are more tend to check implementation details.
     */
    @Test
    void purchaseSucceedsWhenEnoughInventory() {
        Customer customer = new Customer();
        Store store = mock(Store.class);
        when(store.getInventory(Product.SHAMPOO)).thenReturn(10);

        boolean success = customer.purchase(store, Product.SHAMPOO, 5);

        assertTrue(success);
        verify(store).removeInventory(Product.SHAMPOO, 5);
    }
}
