package com.tns.fooddeliverysystem.entities;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Cart {
    private Map<FoodItem, Integer> items = new HashMap<>();

    public void addItem(FoodItem item, int qty) {
        if (item == null || qty <= 0) return;
        items.put(item, items.getOrDefault(item, 0) + qty);
    }

    public void removeItem(FoodItem item) {
        items.remove(item);
    }

    public Map<FoodItem, Integer> getItems() {
        return Collections.unmodifiableMap(items);
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public double getTotalCost() {
        double total = 0;
        for (Map.Entry<FoodItem, Integer> e : items.entrySet()) {
            total += e.getKey().getPrice() * e.getValue();
        }
        return total;
    }

    public void clear() {
        items.clear();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Cart:\n");
        for (Map.Entry<FoodItem, Integer> e : items.entrySet()) {
            sb.append("- ").append(e.getKey().getName())
              .append(" × ").append(e.getValue())
              .append(" = Rs. ").append(e.getKey().getPrice() * e.getValue())
              .append("\n");
        }
        sb.append("Total = Rs. ").append(getTotalCost()).append("\n");
        return sb.toString();
    }
}

