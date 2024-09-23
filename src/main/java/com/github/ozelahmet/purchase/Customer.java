package com.github.ozelahmet.purchase;

public class Customer {

    /**
     * <code>removeInventory</code> line should exist in this method to have better encapsulation. If purchase is
     * successful, removing inventory operation should be done here. Otherwise, this operation must be done manually
     * in every place purchase method called.
     */
    public boolean purchase(Store store, Product product, int amount) {
        if (amount > store.getInventory(product)) {
            return false;
        }

        // operations on customer

        store.removeInventory(product, amount);
        return true;
    }

}
