package com.tns.fooddeliverysystem.services;

import com.tns.fooddeliverysystem.entities.Cart;
import com.tns.fooddeliverysystem.entities.Customer;
import com.tns.fooddeliverysystem.entities.DeliveryPerson;
import com.tns.fooddeliverysystem.entities.FoodItem;
import com.tns.fooddeliverysystem.entities.Order;

import java.util.*;

public class OrderService {
    private Map<Integer, Order> orders = new HashMap<>();
    private List<DeliveryPerson> deliveryPersons = new ArrayList<>();
    private int nextOrderId = 1;

    public int placeOrder(Customer customer, String deliveryAddress) {
        if (customer == null) return -1;
        Cart cart = customer.getCart();
        if (cart.isEmpty()) return -1;

        int orderId = nextOrderId++;
        Order order = new Order(orderId, customer);
        // copy items from cart
        for (Map.Entry<FoodItem, Integer> e : cart.getItems().entrySet()) {
            order.addItem(e.getKey(), e.getValue());
        }
        order.setDeliveryAddress(deliveryAddress);
        orders.put(orderId, order);

        // clear customer's cart after placing order
        cart.clear();
        return orderId;
    }

    public Collection<Order> getAllOrders() {
        return orders.values();
    }

    public Order findOrderById(int id) {
        return orders.get(id);
    }

    public void addDeliveryPerson(int id, String name, long contactNo) {
        // avoid duplicate id
        if (findDeliveryPersonById(id) != null) return;
        deliveryPersons.add(new DeliveryPerson(id, name, contactNo));
    }

    public DeliveryPerson findDeliveryPersonById(int id) {
        for (DeliveryPerson dp : deliveryPersons) {
            if (dp.getId() == id) return dp;
        }
        return null;
    }

    public boolean assignDeliveryPersonToOrder(int orderId, int deliveryPersonId) {
        Order order = findOrderById(orderId);
        DeliveryPerson dp = findDeliveryPersonById(deliveryPersonId);
        if (order == null || dp == null) return false;
        order.assignDeliveryPerson(dp);
        order.setStatus("Assigned");
        return true;
    }

    public Collection<Order> getOrdersByCustomerId(int customerId) {
        List<Order> list = new ArrayList<>();
        for (Order o : orders.values()) {
            if (o.getCustomer().getUserId() == customerId) list.add(o);
        }
        return list;
    }

    public List<DeliveryPerson> getDeliveryPersons() {
        return deliveryPersons;
    }
}

