package com.obs;

import com.obs.controller.BookController;
import com.obs.model.Book;
import com.obs.repository.InMemoryBookRepository;
import com.obs.service.BookService;

/** Tiny console entry point showing how the catalogue service is wired. */
public class OnlineBookStoreApp {
    public static void main(String[] args) {
        InMemoryBookRepository repository = new InMemoryBookRepository();
        repository.save(new Book(1, "Clean Code", "Robert C. Martin", 29.99, 12));
        repository.save(new Book(2, "Effective Java", "Joshua Bloch", 39.99, 7));
        BookController controller = new BookController(new BookService(repository));
        controller.getBooks().forEach(book -> System.out.println(book.getTitle() + " - GBP " + book.getPrice()));
    }
}
