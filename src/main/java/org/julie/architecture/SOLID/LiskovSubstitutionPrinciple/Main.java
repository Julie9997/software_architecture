package org.julie.architecture.SOLID.LiskovSubstitutionPrinciple;

public class Main {
    public static void main(String[] args) {
        Figure rect = new Rectangle(5, 10);
        Figure sq = new Square(12);

        System.out.println(rect.getArea());
        System.out.println(sq.getArea());
    }
}
