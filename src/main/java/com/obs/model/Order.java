package com.obs.model;

import java.time.Instant;
import java.util.List;

/** Completed order summary; payment processing is outside this sample's scope. */
public class Order {
    private final String orderId;
    private final String customerEmail;
    private final List<CartItem> items;
    private final double total;
    private final Instant createdAt;

    public Order(String orderId, String customerEmail, List<CartItem> items, double total) {
        this.orderId = orderId;
        this.customerEmail = customerEmail;
        this.items = List.copyOf(items);
        this.total = total;
        this.createdAt = Instant.now();
    }

    public String getOrderId() { return orderId; }
    public String getCustomerEmail() { return customerEmail; }
    public List<CartItem> getItems() { return items; }
    public double getTotal() { return total; }
    public Instant getCreatedAt() { return createdAt; }
}
