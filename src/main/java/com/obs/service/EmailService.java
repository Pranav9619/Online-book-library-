package com.obs.service;

/** Returns message text instead of sending live email in this offline sample. */
public class EmailService {
    public String orderConfirmation(String email, String orderId) {
        if (email == null || email.isBlank()) throw new IllegalArgumentException("Email is required");
        return "Thank you for shopping with us. Order " + orderId + " has been received.";
    }

    public String passwordResetNotice(String email) {
        if (email == null || email.isBlank()) throw new IllegalArgumentException("Email is required");
        return "Thank you for shopping with us. A password reset request was received.";
    }
}
