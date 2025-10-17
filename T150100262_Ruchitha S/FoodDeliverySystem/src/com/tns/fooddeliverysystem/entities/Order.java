package com.tns.fooddeliverysystem.entities;

import java.util.HashMap;
import java.util.Map;

public class Order {
    private int orderId;
    private Customer customer;
    private Map<FoodItem, Integer> items = new HashMap<>();
    private String status = "Pending";
    private DeliveryPerson deliveryPerson;
    private String deliveryAddress;

    public Order(int orderId, Customer customer) {
        this.orderId = orderId;
        this.customer = customer;
    }

    public int getOrderId() { return orderId; }
    public Customer getCustomer() { return customer; }
    public Map<FoodItem, Integer> getItems() { return items; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public DeliveryPerson getDeliveryPerson() { return deliveryPerson; }
    public void assignDeliveryPerson(DeliveryPerson dp) { this.deliveryPerson = dp; }
    public void setDeliveryAddress(String addr) { this.deliveryAddress = addr; }
    public String getDeliveryAddress() { return deliveryAddress; }

    public void addItem(FoodItem f, int qty) {
        if (f != null && qty > 0) items.put(f, items.getOrDefault(f, 0) + qty);
    }

    public double getTotal() {
        double total = 0;
        for (Map.Entry<FoodItem, Integer> e : items.entrySet()) {
            total += e.getKey().getPrice() * e.getValue();
        }
        return total;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Order{orderId=").append(orderId)
          .append(", customer=").append(customer.getUsername())
          .append(", items=[");
        boolean first = true;
        for (Map.Entry<FoodItem, Integer> e : items.entrySet()) {
            if (!first) sb.append(", ");
            sb.append(e.getKey().getName()).append(" x ").append(e.getValue());
            first = false;
        }
        sb.append("], total=Rs. ").append(getTotal())
          .append(", status='").append(status).append("'")
          .append(", deliveryPerson=").append(deliveryPerson != null ? deliveryPerson.getName() : "Not Assigned")
          .append(", deliveryAddress='").append(deliveryAddress).append("'}");
        return sb.toString();
    }
}
