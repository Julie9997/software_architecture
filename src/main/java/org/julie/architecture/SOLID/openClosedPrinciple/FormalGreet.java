package org.julie.architecture.SOLID.openClosedPrinciple;

public class FormalGreet implements Igreet{
    @Override
    public void greet() {
        System.out.println("Добро пожаловать, уважаемый гость!");
    }
}