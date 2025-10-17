package com.tns.fooddeliverysystem.services;

import com.tns.fooddeliverysystem.entities.FoodItem;
import com.tns.fooddeliverysystem.entities.Restaurant;

import java.util.ArrayList;
import java.util.List;

public class FoodService {
    private List<Restaurant> restaurants = new ArrayList<>();

    public void addRestaurant(int id, String name) {
        if (findRestaurantById(id) != null) return;
        restaurants.add(new Restaurant(id, name));
    }

    public Restaurant findRestaurantById(int id) {
        for (Restaurant r : restaurants) {
            if (r.getId() == id) return r;
        }
        return null;
    }

    public boolean addFoodItemToRestaurant(int restaurantId, int foodId, String name, double price) {
        Restaurant r = findRestaurantById(restaurantId);
        if (r == null) return false;
        r.addFoodItem(new FoodItem(foodId, name, price));
        return true;
    }

    public boolean removeFoodItemFromRestaurant(int restaurantId, int foodId) {
        Restaurant r = findRestaurantById(restaurantId);
        if (r == null) return false;
        r.removeFoodItem(foodId);
        return true;
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    public List<FoodItem> listAllFoodItems() {
        List<FoodItem> all = new ArrayList<>();
        for (Restaurant r : restaurants) all.addAll(r.getMenu());
        return all;
    }
}

