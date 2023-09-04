package org.julie.architecture.hw10;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleInterface {
    private final OrderRepository orderRepository;
    private final Scanner scanner;

    public ConsoleInterface(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        boolean running = true;
        while (running) {
            System.out.println("1. Создать заказ");
            System.out.println("2. Получить заказ по ID");
            System.out.println("3. Получить все заказы");
            System.out.println("4. Выход");
            System.out.print("Введите ваш выбор: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Пропустим символ новой строки

            switch (choice) {
                case 1:
                    createOrder();
                    break;
                case 2:
                    getOrderById();
                    break;
                case 3:
                    getAllOrders();
                    break;
                case 4:
                    running = false;
                    break;
                default:
                    System.out.println("Неверный выбор. Пожалуйста, попробуйте снова.");
                    break;
            }
            System.out.println();
        }
        System.out.println("Завершение программы.");
    }

    private void createOrder() {
        System.out.print("Введите общую стоимость заказа: ");
        double totalCost = scanner.nextDouble();
        scanner.nextLine(); // Пропустим символ новой строки

        Order order = new Order(0, new ArrayList<>()); // Заглушка для ID и OrderItems
        boolean addingItems = true;
        while (addingItems) {
            System.out.print("Введите ID продукта: ");
            int productId = scanner.nextInt();
            System.out.print("Введите количество: ");
            int quantity = scanner.nextInt();
            scanner.nextLine(); // Пропустим символ новой строки

            Product product = orderRepository.getProductById(productId);
            if (product != null) {
                OrderItem orderItem = new OrderItem(0, product, quantity); // Заглушка для ID
                order.getItems().add(orderItem);
                System.out.println("Товар добавлен в заказ.");
            } else {
                System.out.println("Продукт не найден. Пожалуйста, попробуйте снова.");
            }

            System.out.print("Добавить ещё один товар? (да/нет): ");
            String choice = scanner.nextLine();
            if (!choice.equalsIgnoreCase("да")) {
                addingItems = false;
            }
        }

        orderRepository.save(order);
        System.out.println("Заказ успешно создан.");
    }

    private void getOrderById() {
        System.out.print("Введите ID заказа: ");
        int orderId = scanner.nextInt();
        scanner.nextLine(); // Пропустим символ новой строки

        Order order = orderRepository.getById(orderId);
        if (order != null) {
            System.out.println("ID заказа: " + order.getId());
            System.out.println("Общая стоимость: $" + order.calculateTotalCost());
            System.out.println("Товары:");
            for (OrderItem orderItem : order.getItems()) {
                System.out.println("- " + orderItem.getQuantity() + " x " + orderItem.getProduct().getName()
                        + " ($" + orderItem.getProduct().getPrice() + ")");
            }
        } else {
            System.out.println("Заказ не найден.");
        }
    }

    private void getAllOrders() {
        List<Order> orders = orderRepository.getAll();
        if (orders.isEmpty()) {
            System.out.println("Заказы не найдены.");
        } else {
            for (Order order : orders) {
                System.out.println("ID заказа: " + order.getId());
                System.out.println("Общая стоимость: $" + order.calculateTotalCost());
                System.out.println("Товары:");
                for (OrderItem orderItem : order.getItems()) {
                    System.out.println("- " + orderItem.getQuantity() + " x " + orderItem.getProduct().getName()
                            + " ($" + orderItem.getProduct().getPrice() + ")");
                }
                System.out.println("----------------------------------");
            }
        }
    }
}

