package com.tns.fooddeliverysystem.entities;

public class Customer extends User {
    private Cart cart;

    public Customer(int id, String name, long contactNo) {
        super(id, name, contactNo);
        this.cart = new Cart();
    }

    public Cart getCart() {
        return cart;
    }

    @Override
    public String toString() {
        return "Customer{id=" + getUserId() + ", name='" + getUsername() + "'}";
    }
}

