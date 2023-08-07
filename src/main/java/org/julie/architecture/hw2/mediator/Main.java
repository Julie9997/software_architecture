package org.julie.architecture.hw2.mediator;

// Создаем интерфейс медиатора, определяющий общие операции для всех компонентов.
interface Mediator {
    void notify(Component sender, String event);
}

// Создаем базовый класс компонента, который будет взаимодействовать через медиатор.
abstract class Component {
    protected Mediator mediator;

    public Component(Mediator mediator) {
        this.mediator = mediator;
    }

    public abstract void send(String event);
    public abstract void receive(String event);
}

// Создаем класс конкретного медиатора, который реализует операции из интерфейса медиатора.
class ConcreteMediator implements Mediator {
    private Component component1;
    private Component component2;

    public void setComponent1(Component component) {
        this.component1 = component;
    }

    public void setComponent2(Component component) {
        this.component2 = component;
    }

    @Override
    public void notify(Component sender, String event) {
        if (sender == component1) {
            component2.receive(event);
        } else if (sender == component2) {
            component1.receive(event);
        }
    }
}

// Создаем классы конкретных компонентов, реализующие операции из базового класса компонента.
class ConcreteComponent1 extends Component {
    public ConcreteComponent1(Mediator mediator) {
        super(mediator);
    }

    @Override
    public void send(String event) {
        System.out.println("Component1 sends event: " + event);
        mediator.notify(this, event);
    }

    @Override
    public void receive(String event) {
        System.out.println("Component1 received event: " + event);
    }
}

class ConcreteComponent2 extends Component {
    public ConcreteComponent2(Mediator mediator) {
        super(mediator);
    }

    @Override
    public void send(String event) {
        System.out.println("Component2 sends event: " + event);
        mediator.notify(this, event);
    }

    @Override
    public void receive(String event) {
        System.out.println("Component2 received event: " + event);
    }
}

// Использование паттерна
public class Main {
    public static void main(String[] args) {
        ConcreteMediator mediator = new ConcreteMediator();

        // Создание компонентов
        ConcreteComponent1 component1 = new ConcreteComponent1(mediator);
        ConcreteComponent2 component2 = new ConcreteComponent2(mediator);

        // Установка компонентов в медиатор
        mediator.setComponent1(component1);
        mediator.setComponent2(component2);

        // Отправка события от компонента 1
        component1.send("Hello from Component1!");

        // Отправка события от компонента 2
        component2.send("Hi from Component2!");
    }
}


