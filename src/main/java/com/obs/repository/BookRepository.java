package com.obs.repository;

import com.obs.model.Book;
import java.util.List;
import java.util.Optional;

public interface BookRepository {
    Optional<Book> findById(long id);
    List<Book> findAll();
    void save(Book book);
}
