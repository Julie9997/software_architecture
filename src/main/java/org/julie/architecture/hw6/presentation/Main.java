package org.julie.architecture.hw6.presentation;

import org.julie.architecture.hw6.data.BookRepository;
import org.julie.architecture.hw6.data.InMemoryBookRepository;
import org.julie.architecture.hw6.domain.Book;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        BookRepository bookRepository = new InMemoryBookRepository();

        // Добавляем книги в репозиторий
        Book book1 = new Book("1", "Чистый код", "Роберт Мартин", 34.99);
        Book book2 = new Book("2", "Java. Эффективное программирование", "Джошуа Блох", 29.99);
        bookRepository.addBook(book1);
        bookRepository.addBook(book2);

        // Получаем список всех книг из репозитория
        List<Book> allBooks = bookRepository.getAllBooks();
        for (Book book : allBooks) {
            System.out.println("Книга: " + book.getTitle() + ", Автор: " + book.getAuthor() + ", Цена: $" + book.getPrice());
        }
    }
}
