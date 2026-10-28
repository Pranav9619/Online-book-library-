package com.obs.repository;

import com.obs.model.Book;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/** In-memory repository so the sample can run without a database. */
public class InMemoryBookRepository implements BookRepository {
    private final Map<Long, Book> books = new LinkedHashMap<>();

    @Override
    public Optional<Book> findById(long id) { return Optional.ofNullable(books.get(id)); }

    @Override
    public List<Book> findAll() { return new ArrayList<>(books.values()); }

    @Override
    public void save(Book book) {
        if (book == null) throw new IllegalArgumentException("Book is required");
        books.put(book.getId(), book);
    }
}
