package com.tns.fooddeliverysystem.application;

import com.tns.fooddeliverysystem.entities.FoodItem;
import com.tns.fooddeliverysystem.entities.Restaurant;
import com.tns.fooddeliverysystem.entities.Customer;
import com.tns.fooddeliverysystem.entities.Order;
import com.tns.fooddeliverysystem.services.CustomerService;
import com.tns.fooddeliverysystem.services.FoodService;
import com.tns.fooddeliverysystem.services.OrderService;

import java.util.List;
import java.util.Scanner;

public class FoodDeliverySystem {
    private static final Scanner scanner = new Scanner(System.in);
    private static final FoodService foodService = new FoodService();
    private static final CustomerService customerService = new CustomerService();
    private static final OrderService orderService = new OrderService();

    public static void main(String[] args) {
        seedSampleData();

        while (true) {
            System.out.println("1. Admin Menu");
            System.out.println("2. Customer Menu");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = readInt();
            if (choice == 1) adminMenu();
            else if (choice == 2) customerMenu();
            else if (choice == 3) {
                System.out.println("Exiting..."); break;
            } else System.out.println("Invalid option.");
        }
        scanner.close();
    }

    private static void adminMenu() {
        while (true) {
            System.out.println("\nAdmin Menu:");
            System.out.println("1. Add Restaurant");
            System.out.println("2. Add Food Item to Restaurant");
            System.out.println("3. Remove Food Item from Restaurant");
            System.out.println("4. View Restaurants and Menus");
            System.out.println("5. View Orders");
            System.out.println("6. Add Delivery Person");
            System.out.println("7. Assign Delivery Person to Order");
            System.out.println("8. Back");
            System.out.print("Choose an option: ");
            int choice = readInt();
            switch (choice) {
                case 1 -> {
                    System.out.print("Enter Restaurant ID: "); int rid = readInt();
                    System.out.print("Enter Restaurant Name: "); String rname = readLine();
                    foodService.addRestaurant(rid, rname);
                    System.out.println("Restaurant added successfully!");
                }
                case 2 -> {
                    System.out.print("Enter Restaurant ID: "); int rid = readInt();
                    System.out.print("Enter Food Item ID: "); int fid = readInt();
                    System.out.print("Enter Food Item Name: "); String fname = readLine();
                    System.out.print("Enter Food Item Price: "); double price = readDouble();
                    boolean ok = foodService.addFoodItemToRestaurant(rid, fid, fname, price);
                    System.out.println(ok ? "Food item added successfully!" : "Restaurant not found.");
                }
                case 3 -> {
                    System.out.print("Enter Restaurant ID: "); int rid = readInt();
                    System.out.print("Enter Food Item ID to remove: "); int fid = readInt();
                    boolean ok = foodService.removeFoodItemFromRestaurant(rid, fid);
                    System.out.println(ok ? "Removed (if existed)!" : "Restaurant not found.");
                }
                case 4 -> {
                    System.out.println("\nRestaurants and Menus:");
                    for (Restaurant r : foodService.getRestaurants()) {
                        System.out.println(r);
                    }
                }
                case 5 -> {
                    System.out.println("\nOrders:");
                    for (Order o : orderService.getAllOrders()) {
                        System.out.println(o);
                    }
                }
                case 6 -> {
                    System.out.print("Enter Delivery Person ID: "); int id = readInt();
                    System.out.print("Enter Delivery Person Name: "); String name = readLine();
                    System.out.print("Enter Contact No.: "); long contact = readLong();
                    orderService.addDeliveryPerson(id, name, contact);
                    System.out.println("Delivery person added successfully!");
                }
                case 7 -> {
                    System.out.print("Enter Order ID: "); int oid = readInt();
                    System.out.print("Enter Delivery Person ID: "); int dpId = readInt();
                    boolean ok = orderService.assignDeliveryPersonToOrder(oid, dpId);
                    System.out.println(ok ? "Delivery person assigned to order successfully!" : "Order or delivery person not found.");
                }
                case 8 -> {
                    return;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    private static void customerMenu() {
        while (true) {
            System.out.println("\nCustomer Menu:");
            System.out.println("1. Add Customer");
            System.out.println("2. View Food Items");
            System.out.println("3. Add Food to Cart");
            System.out.println("4. View Cart");
            System.out.println("5. Place Order");
            System.out.println("6. View Orders");
            System.out.println("7. Back");
            System.out.print("Choose an option: ");
            int choice = readInt();
            switch (choice) {
                case 1 -> {
                    System.out.print("Enter User ID: "); int uid = readInt();
                    System.out.print("Enter Username: "); String uname = readLine();
                    System.out.print("Enter Contact No.: "); long contact = readLong();
                    boolean ok = customerService.addCustomer(uid, uname, contact);
                    System.out.println(ok ? "Customer created successfully!" : "Customer id already exists!");
                }
                case 2 -> {
                    System.out.println("\nRestaurants and Menus:");
                    for (Restaurant r : foodService.getRestaurants()) {
                        System.out.println(r);
                    }
                }
                case 3 -> {
                    System.out.print("Enter Customer ID: "); int cid = readInt();
                    Customer c = customerService.findCustomerById(cid);
                    if (c == null) { System.out.println("Customer not found."); break; }
                    System.out.print("Enter Restaurant ID: "); int rid = readInt();
                    Restaurant r = foodService.findRestaurantById(rid);
                    if (r == null) { System.out.println("Restaurant not found."); break; }
                    System.out.print("Enter Food Item ID: "); int fid = readInt();
                    FoodItem chosen = null;
                    for (FoodItem f : r.getMenu()) {
                        if (f.getId() == fid) { chosen = f; break; }
                    }
                    if (chosen == null) { System.out.println("Food item not found in that restaurant."); break; }
                    System.out.print("Enter Quantity: "); int qty = readInt();
                    c.getCart().addItem(chosen, qty);
                    System.out.println("Food item added to cart!");
                }
                case 4 -> {
                    System.out.print("Enter Customer ID: "); int cid = readInt();
                    Customer c = customerService.findCustomerById(cid);
                    if (c == null) { System.out.println("Customer not found."); break; }
                    System.out.println(c.getCart());
                }
                case 5 -> {
                    System.out.print("Enter Customer ID: "); int cid = readInt();
                    Customer c = customerService.findCustomerById(cid);
                    if (c == null) { System.out.println("Customer not found."); break; }
                    if (c.getCart().isEmpty()) { System.out.println("Cart is empty."); break; }
                    System.out.print("Enter Delivery Address: "); String addr = readLine();
                    int orderId = orderService.placeOrder(c, addr);
                    if (orderId == -1) System.out.println("Order failed.");
                    else System.out.println("Order placed successfully! Your order ID is: " + orderId);
                }
                case 6 -> {
                    System.out.print("Enter Customer ID: "); int cid = readInt();
                    Customer c = customerService.findCustomerById(cid);
                    if (c == null) { System.out.println("Customer not found."); break; }
                    List<Order> orders = List.copyOf(orderService.getOrdersByCustomerId(cid));
                    if (orders.isEmpty()) {
                        System.out.println("No orders found.");
                    } else {
                        System.out.println("Orders:");
                        for (Order o : orders) System.out.println(o);
                    }
                }
                case 7 -> {
                    return;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    // small helpers

    private static int readInt() {
        while (!scanner.hasNextInt()) {
            scanner.next(); System.out.print("Please enter a number: ");
        }
        int v = scanner.nextInt();
        scanner.nextLine(); // consume newline
        return v;
    }

    private static long readLong() {
        while (!scanner.hasNextLong()) {
            scanner.next(); System.out.print("Please enter a number: ");
        }
        long v = scanner.nextLong();
        scanner.nextLine(); // consume newline
        return v;
    }

    private static double readDouble() {
        while (!scanner.hasNextDouble()) {
            scanner.next(); System.out.print("Please enter a decimal number: ");
        }
        double v = scanner.nextDouble();
        scanner.nextLine();
        return v;
    }

    private static String readLine() {
        return scanner.nextLine().trim();
    }

    private static void seedSampleData() {
        // sample restaurants and items
        foodService.addRestaurant(101, "HariOmDhaba");
        foodService.addRestaurant(102, "ExpressInn");
        foodService.addRestaurant(103, "GreenBowl");

        foodService.addFoodItemToRestaurant(101, 1, "PanjabiThali", 340);
        foodService.addFoodItemToRestaurant(101, 2, "PavBhaji", 140);
        foodService.addFoodItemToRestaurant(102, 10, "Veg Biryani", 180);
        foodService.addFoodItemToRestaurant(103, 20, "Caesar Salad", 120);

        // sample customer (optional)
        customerService.addCustomer(1001, "Alpana", 7720092235L);
    }
}
