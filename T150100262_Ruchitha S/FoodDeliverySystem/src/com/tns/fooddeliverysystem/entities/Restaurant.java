package com.tns.fooddeliverysystem.entities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Restaurant {
    private int id;
    private String name;
    private List<FoodItem> menu = new ArrayList<>();

    public Restaurant(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void addFoodItem(FoodItem foodItem) {
        if (foodItem != null) menu.add(foodItem);
    }

    // remove by food id
    public void removeFoodItem(int foodId) {
        Iterator<FoodItem> it = menu.iterator();
        while (it.hasNext()) {
            if (it.next().getId() == foodId) {
                it.remove();
                return;
            }
        }
    }

    public List<FoodItem> getMenu() {
        return menu;
    }

    public int getId() { return id; }
    public String getName() { return name; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Restaurant ID: ").append(id).append(", Name: ").append(name).append("\n");
        for (FoodItem f : menu) {
            sb.append("  - FoodItem ID: ").append(f.getId())
              .append(", Name: ").append(f.getName())
              .append(", Price: Rs. ").append(f.getPrice()).append("\n");
        }
        return sb.toString();
    }
}

