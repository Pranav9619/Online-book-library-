package com.obs.model;

/** A book and requested quantity in a shopping cart. */
public class CartItem {
    private final long bookId;
    private final int quantity;

    public CartItem(long bookId, int quantity) {
        this.bookId = bookId;
        this.quantity = quantity;
    }

    public long getBookId() { return bookId; }
    public int getQuantity() { return quantity; }
}
