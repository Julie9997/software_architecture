package org.julie.architecture.hw2.chainOfResponsibility;

// Создаем базовый абстрактный класс для обработчиков
abstract class Handler {
    private Handler nextHandler; // Ссылка на следующий обработчик в цепочке

    public void setNextHandler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public void handleRequest(Request request) {
        if (canHandle(request)) {
            processRequest(request); // Обработка запроса
        } else if (nextHandler != null) {
            nextHandler.handleRequest(request); // Передача запроса следующему обработчику в цепочке
        } else {
            System.out.println("Нет обработчика для данного запроса");
        }
    }

    // Метод, проверяющий, может ли данный обработчик обработать запрос
    protected abstract boolean canHandle(Request request);

    // Метод, реализующий обработку запроса данным обработчиком
    protected abstract void processRequest(Request request);
}

// Создаем класс, представляющий запрос
class Request {
    private String type;

    public Request(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}

// Создаем конкретные классы обработчиков
class ConcreteHandler1 extends Handler {
    @Override
    protected boolean canHandle(Request request) {
        return request.getType().equals("Type1"); // Может обработать запрос типа "Type1"
    }

    @Override
    protected void processRequest(Request request) {
        System.out.println("Обработка запроса типа Type1");
        // Реализация обработки запроса типа "Type1"
    }
}

class ConcreteHandler2 extends Handler {
    @Override
    protected boolean canHandle(Request request) {
        return request.getType().equals("Type2"); // Может обработать запрос типа "Type2"
    }

    @Override
    protected void processRequest(Request request) {
        System.out.println("Обработка запроса типа Type2");
        // Реализация обработки запроса типа "Type2"
    }
}

public class Main {
    public static void main(String[] args) {
        // Создаем обработчики
        Handler handler1 = new ConcreteHandler1();
        Handler handler2 = new ConcreteHandler2();

        // Устанавливаем связи между обработчиками в цепочке
        handler1.setNextHandler(handler2);

        // Создаем запросы разных типов
        Request request1 = new Request("Type1");
        Request request2 = new Request("Type2");
        Request request3 = new Request("Type3");

        // Передаем запросы на обработку первому обработчику в цепочке
        handler1.handleRequest(request1);
        handler1.handleRequest(request2);
        handler1.handleRequest(request3);
    }
}


