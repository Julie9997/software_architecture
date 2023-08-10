package org.julie.architecture.SOLID.dependencyInversionPrinciple;

public class ConsolePrinter implements Printer {
    public void print(TextProvider text) {
        System.out.println(text.getText());
    }
}
