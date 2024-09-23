package com.github.ozelahmet.purchase;

import java.util.HashMap;
import java.util.Map;

public class Store {

    private final Map<Product, Integer> inMemoryStore = new HashMap<>();

    public Integer getInventory(Product product) {
        return inMemoryStore.getOrDefault(product, 0);
    }

    public void addInventory(Product product, int quantity) {
        Integer currentAmount = inMemoryStore.getOrDefault(product, 0);
        inMemoryStore.put(product, currentAmount + quantity);
    }

    public void removeInventory(Product product, int quantity) {
        Integer currentAmount = inMemoryStore.getOrDefault(product, 0);

        if (currentAmount - quantity > 0) {
            inMemoryStore.put(product, currentAmount - quantity);
        } else {
            inMemoryStore.remove(product);
        }
    }

}
