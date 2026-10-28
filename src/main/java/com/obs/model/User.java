package com.obs.model;

/** Minimal customer record used by this teaching example. */
public class User {
    private final long id;
    private final String name;
    private final String email;
    // Retained as a sample quality finding: no discount policy currently consumes this field.
    private double discountRate;

    public User(long id, String name, String email) {
        if (id <= 0) throw new IllegalArgumentException("User id must be positive");
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Name is required");
        if (email == null || !email.contains("@")) throw new IllegalArgumentException("Valid email is required");
        this.id = id;
        this.name = name.trim();
        this.email = email.trim().toLowerCase();
    }

    public long getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
}
