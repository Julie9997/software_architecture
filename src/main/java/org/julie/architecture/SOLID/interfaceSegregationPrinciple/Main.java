package org.julie.architecture.SOLID.interfaceSegregationPrinciple;

public class Main {
    public static void main(String[] args) {
        Workable humanWorker = new HumanWorker();
        humanWorker.work();
        ((Eatable) humanWorker).eat();

        Workable robotWorker = new RobotWorker();
        robotWorker.work();
    }
}