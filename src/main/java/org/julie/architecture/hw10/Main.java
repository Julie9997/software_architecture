package org.julie.architecture.hw10;

public class Main {
    public static void main(String[] args) {
        OrderRepository orderRepository = new OrderRepositoryImpl();
        ConsoleInterface consoleInterface = new ConsoleInterface(orderRepository);
        consoleInterface.start();
    }
}
