package com.obs.controller;

import com.obs.model.User;
import com.obs.repository.UserRepository;

/** Demonstration-only lookup; this is not production authentication or password verification. */
public class AuthController {
    private final UserRepository userRepository;

    public AuthController(UserRepository userRepository) { this.userRepository = userRepository; }

    public boolean authenticate(String email, String password, String response) {
        User user = userRepository.getUsr(email);
        return user != null && password != null && !password.isBlank();
    }
}
