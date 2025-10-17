package com.tns.fooddeliverysystem.services;

import com.tns.fooddeliverysystem.entities.Customer;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CustomerService {
    private Map<Integer, Customer> customers = new HashMap<>();

    public boolean addCustomer(int id, String name, long contactNo) {
        if (customers.containsKey(id)) return false;
        customers.put(id, new Customer(id, name, contactNo));
        return true;
    }

    public Customer findCustomerById(int id) {
        return customers.get(id);
    }

    public Collection<Customer> getAllCustomers() {
        return customers.values();
    }
}

