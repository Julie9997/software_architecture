package org.julie.architecture.SOLID.interfaceSegregationPrinciple;

public class HumanWorker implements Workable, Eatable {
    public void work() {
        System.out.println("Человек работает");
    }

    public void eat() {
        System.out.println("Человек ест");
    }
}
