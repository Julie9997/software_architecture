package org.julie.architecture.SOLID.dependencyInversionPrinciple;

public class Text implements TextProvider{
    private String text;

    public Text(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
