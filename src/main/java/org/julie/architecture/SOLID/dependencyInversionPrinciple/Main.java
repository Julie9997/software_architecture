package org.julie.architecture.SOLID.dependencyInversionPrinciple;

public class Main {
    public static void main(String[] args) {
        TextProvider myText = new Text("Hello, world!");
        Printer myPrinter = new ConsolePrinter();
        myPrinter.print(myText);
    }
}