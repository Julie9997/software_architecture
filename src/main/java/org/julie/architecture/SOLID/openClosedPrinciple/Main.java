package org.julie.architecture.SOLID.openClosedPrinciple;

public class Main {
    public static void main(String[] args) {
        Igreet greet = new InformalGreet();
        greet.greet();
        Igreet greet2 = new FormalGreet();
        greet2.greet();
    }
}