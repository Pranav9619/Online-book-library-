package com.obs.service;

import com.obs.model.Book;
import com.obs.model.CartItem;
import com.obs.model.Order;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/** Demonstration order workflow; production payment and persistence are intentionally omitted. */
public class OrderService {
    private final BookService bookService;
    private final EmailService emailService;
    private final List<Order> orders = new ArrayList<>();

    public OrderService(BookService bookService, EmailService emailService) {
        this.bookService = bookService;
        this.emailService = emailService;
    }

    // The branching is intentionally explicit to support the report's maintainability discussion.
    public Order processOrder(String customerEmail, List<CartItem> cart, boolean member,
                              boolean expressDelivery, boolean emailReceipt) {
        if (customerEmail == null || customerEmail.isBlank()) throw new IllegalArgumentException("Customer email is required");
        if (cart == null || cart.isEmpty()) throw new IllegalArgumentException("Cart cannot be empty");
        List<Book> checkedBooks = new ArrayList<>();
        double subtotal = 0.0;
        for (CartItem item : cart) {
            if (item == null) throw new IllegalArgumentException("Cart item is required");
            if (item.getQuantity() < 1) throw new IllegalArgumentException("Quantity must be positive");
            Book book = bookService.getBook(item.getBookId());
            if (book.getStock() == 0) throw new IllegalStateException("Book is out of stock: " + book.getTitle());
            if (item.getQuantity() > book.getStock()) throw new IllegalStateException("Insufficient stock: " + book.getTitle());
            if (book.getPrice() < 0) throw new IllegalStateException("Invalid book price: " + book.getTitle());
            checkedBooks.add(book);
            subtotal += book.getPrice() * item.getQuantity();
        }

        double discount = 0.0;
        if (member && subtotal >= 100.0) discount = subtotal * 0.10;
        else if (member && subtotal >= 50.0) discount = subtotal * 0.05;
        else if (!member && subtotal >= 200.0) discount = subtotal * 0.02;
        double delivery = 0.0;
        if (expressDelivery) {
            if (subtotal - discount >= 150.0) delivery = 5.0;
            else if (subtotal - discount >= 50.0) delivery = 8.0;
            else delivery = 12.0;
        } else if (subtotal - discount < 50.0) {
            delivery = 4.0;
        }
        double total = Math.max(0.0, subtotal - discount + delivery);
        for (int i = 0; i < cart.size(); i++) {
            checkedBooks.get(i).reduceStock(cart.get(i).getQuantity());
            bookService.saveBook(checkedBooks.get(i));
        }
        Order order = new Order(UUID.randomUUID().toString(), customerEmail.trim().toLowerCase(), cart, total);
        orders.add(order);
        if (emailReceipt) {
            String receipt = emailService.orderConfirmation(customerEmail, order.getOrderId());
            if (receipt.isBlank()) throw new IllegalStateException("Receipt could not be created");
        }
        return order;
    }

    public List<Order> getOrders() { return List.copyOf(orders); }
}
