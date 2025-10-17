package com.tns.fooddeliverysystem.entities;

import java.util.Objects;

public class FoodItem {
    private int id;
    private String name;
    private double price;

    public FoodItem(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    @Override
    public String toString() {
        return "FoodItem{id=" + id + ", name='" + name + "', price=" + price + "}";
    }

    // Important: so Map/Set keys behave correctly
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FoodItem)) return false;
        FoodItem foodItem = (FoodItem) o;
        return id == foodItem.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

