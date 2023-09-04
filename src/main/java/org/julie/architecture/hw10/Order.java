package org.julie.architecture.hw10;

import java.util.List;

class Order {
    private int id;
    private List<OrderItem> items;

    public Order(int id, List<OrderItem> orderItems) {
        this.id = id;
        this.items = orderItems;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public double calculateTotalCost() {
        double totalCost = 0;
        for (OrderItem orderItem : items) {
            totalCost += orderItem.getProduct().getPrice() * orderItem.getQuantity();
        }
        return totalCost;
    }
}