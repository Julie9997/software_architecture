package org.julie.architecture.hw6.data;

import org.julie.architecture.hw6.domain.Book;

import java.util.List;

/**
 * Интерфейс, определяющий методы для управления книгами в интернет-магазине.
 */
public interface BookRepository {
    void addBook(Book book);

    void removeBook(Book book);

    List<Book> getAllBooks();
}