package org.julie.architecture.hw2.strategy;

// Создаем интерфейс, определяющий общее поведение стратегий
interface Strategy {
    void execute(); // Метод, выполняющий стратегию
}

// Создаем конкретные классы-стратегии, которые реализуют интерфейс Strategy
class ConcreteStrategy1 implements Strategy {
    @Override
    public void execute() {
        System.out.println("Выполняется стратегия 1");
        // Реализация стратегии 1
    }
}

class ConcreteStrategy2 implements Strategy {
    @Override
    public void execute() {
        System.out.println("Выполняется стратегия 2");
        // Реализация стратегии 2
    }
}

// Создаем класс контекста, который использует стратегии
class Context {
    private Strategy strategy;

    public Context(Strategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public void executeStrategy() {
        strategy.execute(); // Выполняем стратегию
    }
}

public class Main {
    public static void main(String[] args) {
        // Создаем объект контекста
        Context context = new Context(new ConcreteStrategy1());

        // Выполняем стратегию 1
        context.executeStrategy();

        // Изменяем стратегию на стратегию 2
        context.setStrategy(new ConcreteStrategy2());

        // Выполняем стратегию 2
        context.executeStrategy();
    }
}
