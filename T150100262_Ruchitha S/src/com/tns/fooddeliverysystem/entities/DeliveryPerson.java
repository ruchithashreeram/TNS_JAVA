package com.tns.fooddeliverysystem.entities;

public class DeliveryPerson {
    private int id;
    private String name;
    private long contactNo;

    public DeliveryPerson(int id, String name, long contactNo) {
        this.id = id;
        this.name = name;
        this.contactNo = contactNo;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public long getContactNo() { return contactNo; }

    @Override
    public String toString() {
        return "DeliveryPerson{id=" + id + ", name='" + name + "'}";
    }
}

