package org.julie.architecture.SOLID.singleResponsibility;

public class Save {
    public void save(Book book) {
        System.out.println("Сохранение информации о книге '" + book.getTitle() + "' в базе данных.");
    }
}