package com.obs.service;

import com.obs.model.Book;
import com.obs.model.CartItem;
import com.obs.model.Order;
import com.obs.repository.InMemoryBookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {
    private BookService books;
    private OrderService orders;

    @BeforeEach void setUp() {
        InMemoryBookRepository repository = new InMemoryBookRepository();
        repository.save(new Book(1, "Clean Code", "Robert C. Martin", 29.99, 12));
        repository.save(new Book(2, "Effective Java", "Joshua Bloch", 39.99, 7));
        books = new BookService(repository);
        orders = new OrderService(books, new EmailService());
    }

    @Test void createsOrderAndReducesStock() {
        Order order = orders.processOrder("reader@example.com", List.of(new CartItem(1, 2)), true, false, true);
        assertEquals("reader@example.com", order.getCustomerEmail());
        assertEquals(59.98 * 0.95, order.getTotal(), 0.02);
        assertEquals(10, books.getBook(1).getStock());
    }

    @Test void rejectsEmptyCart() {
        assertThrows(IllegalArgumentException.class,
                () -> orders.processOrder("reader@example.com", List.of(), false, false, false));
    }

    @Test void rejectsInsufficientStock() {
        assertThrows(IllegalStateException.class,
                () -> orders.processOrder("reader@example.com", List.of(new CartItem(1, 99)), false, false, false));
    }
}
