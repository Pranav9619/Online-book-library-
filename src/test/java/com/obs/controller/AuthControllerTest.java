package com.obs.controller;

import com.obs.model.User;
import com.obs.repository.UserRepository;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AuthControllerTest {
    @Test void acceptsKnownUserWithNonBlankInputInDemoStub() {
        UserRepository repository = new UserRepository();
        repository.save(new User(1, "Alex Reader", "alex@example.com"));
        AuthController controller = new AuthController(repository);
        assertTrue(controller.authenticate("alex@example.com", "not-a-real-password-check", "unused"));
    }

    @Test void rejectsUnknownUser() {
        AuthController controller = new AuthController(new UserRepository());
        assertFalse(controller.authenticate("missing@example.com", "x", "unused"));
    }
}
