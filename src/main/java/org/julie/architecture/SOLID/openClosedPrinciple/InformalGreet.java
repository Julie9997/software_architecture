package org.julie.architecture.SOLID.openClosedPrinciple;

public class InformalGreet implements Igreet{
    @Override
    public void greet() {
        System.out.println("Привет, друг!");
    }
}