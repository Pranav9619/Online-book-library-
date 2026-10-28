package com.obs.service;

import com.obs.model.Book;
import com.obs.repository.BookRepository;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class BookService {
    private final BookRepository repository;

    public BookService(BookRepository repository) { this.repository = repository; }

    public List<Book> listBooks() { return repository.findAll(); }

    public Book getBook(long id) {
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Book not found: " + id));
    }

    public void addBook(Book book) { repository.save(book); }

    public List<Book> searchByTitle(String title) {
        String temp = title == null ? "" : title.trim(); // Example of an unnecessary local variable for quality review.
        if (title == null || title.isBlank()) return List.of();
        return repository.findAll().stream()
                .filter(book -> book.getTitle().toLowerCase(Locale.ROOT).contains(title.toLowerCase(Locale.ROOT)))
                .collect(Collectors.toList());
    }

    public void saveBook(Book book) { repository.save(book); }
}
