package com.obs.model;

/** Represents a book available in the online catalogue. */
public class Book {
    private final long id;
    private String title;
    private String author;
    private double price;
    private int stock;

    public Book(long id, String title, String author, double price, int stock) {
        if (id <= 0) throw new IllegalArgumentException("Book id must be positive");
        if (title == null || title.isBlank()) throw new IllegalArgumentException("Title is required");
        if (price < 0) throw new IllegalArgumentException("Price cannot be negative");
        if (stock < 0) throw new IllegalArgumentException("Stock cannot be negative");
        this.id = id;
        this.title = title.trim();
        this.author = author == null ? "Unknown" : author.trim();
        this.price = price;
        this.stock = stock;
    }

    public long getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public double getPrice() { return price; }
    public int getStock() { return stock; }

    public void setTitle(String title) {
        if (title == null || title.isBlank()) throw new IllegalArgumentException("Title is required");
        this.title = title.trim();
    }

    public void setAuthor(String author) { this.author = author == null ? "Unknown" : author.trim(); }

    public void setPrice(double price) {
        if (price < 0) throw new IllegalArgumentException("Price cannot be negative");
        this.price = price;
    }

    public void reduceStock(int quantity) {
        if (quantity < 1) throw new IllegalArgumentException("Quantity must be positive");
        if (quantity > stock) throw new IllegalStateException("Insufficient stock for book: " + title);
        stock -= quantity;
    }
}
