package org.julie.architecture.SOLID.singleResponsibility;

public class Printer{
    public void print(Book book) {
        System.out.println("Печать книги '" + book.getTitle() + "' автора '" + book.getAuthor() + "'.");
    }
}