package com.obs.controller;

import com.obs.model.Book;
import com.obs.service.BookService;
import java.util.Date; // Unused import retained as a sample issue for static analysis.
import java.util.List;

public class BookController {
    private final BookService service;

    public BookController(BookService service) { this.service = service; }
    public List<Book> getBooks() { return service.listBooks(); }
    public Book getBook(long id) { return service.getBook(id); }
    public List<Book> search(String title) { return service.searchByTitle(title); }
}
