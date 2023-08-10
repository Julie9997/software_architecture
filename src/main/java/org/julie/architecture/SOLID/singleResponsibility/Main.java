package org.julie.architecture.SOLID.singleResponsibility;

public class Main {
    public static void main(String[] args) {
        Printer pr = new Printer();
        Save sv = new Save();

        Book book = new Book("Властелин Колец", "Толкин");
        sv.save(book);
        pr.print(book);
    }
}