package org.julie.architecture.SOLID.openClosedPrinciple;

public class Greeting {
    private String type;

    public Greeting(String type) {
        this.type = type;
    }

    public void greet() {
        if ("formal".equals(type)) {
            System.out.println("Добро пожаловать, уважаемый гость!");
        } else if ("informal".equals(type)) {
            System.out.println("Привет, друг!");
        }
    }
}
