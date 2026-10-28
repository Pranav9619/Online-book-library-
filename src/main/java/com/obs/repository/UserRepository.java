package com.obs.repository;

import com.obs.model.User;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

/** Small in-memory customer repository used by the controller example. */
public class UserRepository {
    private final Map<String, User> usersByEmail = new LinkedHashMap<>();

    public void save(User user) {
        if (user == null) throw new IllegalArgumentException("User is required");
        usersByEmail.put(user.getEmail(), user);
    }

    public Optional<User> findByEmail(String email) {
        if (email == null) return Optional.empty();
        return Optional.ofNullable(usersByEmail.get(email.trim().toLowerCase()));
    }

    /** Abbreviated legacy method retained as an example for naming-convention review. */
    public User getUsr(String email) {
        if (email == null) return null;
        return usersByEmail.get(email.trim().toLowerCase());
    }
}
