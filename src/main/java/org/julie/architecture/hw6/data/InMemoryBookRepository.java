package org.julie.architecture.hw6.data;

import org.julie.architecture.hw6.domain.Book;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс хранилище книг, с использованием коллекций для хранения данных о книгах.
 */
public class InMemoryBookRepository implements BookRepository {
    private final List<Book> books;

    /**
     * Конструктор класса хранилища книг.
     */
    public InMemoryBookRepository() {
        books = new ArrayList<>();
    }

    /**
     * Добавление новой книги.
     * @param book книга.
     */
    @Override
    public void addBook(Book book) {
        books.add(book);
    }

    /**
     * Удаление книги.
     * @param book книга.
     */
    @Override
    public void removeBook(Book book) {
        books.remove(book);
    }

    /**
     * Получение списка всех книг.
     * @return список книг.
     */
    @Override
    public List<Book> getAllBooks() {
        return books;
    }
}
