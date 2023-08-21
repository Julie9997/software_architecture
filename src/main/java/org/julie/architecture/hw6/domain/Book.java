package org.julie.architecture.hw6.domain;

// Класс представляющий книгу
public class Book {
    private String id;
    private String title;
    private String author;
    private double price;

    /**
     * Конструктор класса Book.
     *
     * @param id     идентификатор книги.
     * @param title  название книги.
     * @param author автор книги.
     * @param price  цена книги.
     */
    public Book(String id, String title, String author, double price) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.price = price;
    }

    // геттеры и сеттеры
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
