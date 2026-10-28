package com.obs.service;

import com.obs.model.Book;
import com.obs.repository.InMemoryBookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BookServiceTest {
    private BookService service;

    @BeforeEach void setUp() {
        InMemoryBookRepository repository = new InMemoryBookRepository();
        repository.save(new Book(1, "Clean Code", "Robert C. Martin", 29.99, 12));
        repository.save(new Book(2, "Effective Java", "Joshua Bloch", 39.99, 7));
        service = new BookService(repository);
    }

    @Test void listsBooks() { assertEquals(2, service.listBooks().size()); }
    @Test void findsBookById() { assertEquals("Clean Code", service.getBook(1).getTitle()); }
    @Test void searchesTitlesIgnoringCase() { assertEquals(1, service.searchByTitle("JAVA").size()); }
    @Test void returnsEmptyListForBlankSearch() { assertTrue(service.searchByTitle(" ").isEmpty()); }
    @Test void throwsWhenBookIsMissing() { assertThrows(IllegalArgumentException.class, () -> service.getBook(88)); }
}
