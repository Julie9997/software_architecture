package org.julie.architecture.hw2.memento;

// Создаем класс, представляющий состояние объекта
class OriginatorState {
    private String state; // Поле, хранящее состояние объекта

    public OriginatorState(String state) {
        this.state = state;
    }

    public String getState() {
        return state; // Возвращаем состояние
    }
}

// Создаем объект, состояние которого мы будем сохранять и восстанавливать
class Originator {
    private String state;

    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public Memento saveStateToMemento() {
        return new Memento(state); // Создаем объект Memento и передаем в него текущее состояние
    }

    public void restoreStateFromMemento(Memento memento) {
        state = memento.getState(); // Восстанавливаем состояние из объекта Memento
    }

    // Вложенный класс Memento, который хранит состояние объекта
    public static class Memento {
        private final String savedState;

        public Memento(String state) {
            savedState = state;
        }

        public String getState() {
            return savedState; // Возвращаем сохраненное состояние
        }
    }
}

// Создаем объект, который будет хранить и восстанавливать состояния объекта Originator
class Caretaker {
    private Originator.Memento memento;

    public void saveState(Originator.Memento memento) {
        this.memento = memento;
    }

    public Originator.Memento getSavedState() {
        return memento; // Возвращаем сохраненное состояние
    }
}

public class Main {
    public static void main(String[] args) {
        Originator originator = new Originator();
        Caretaker caretaker = new Caretaker();

        originator.setState("Состояние 1");
        System.out.println("Текущее состояние: " + originator.getState());

        caretaker.saveState(originator.saveStateToMemento()); // Сохраняем текущее состояние
        originator.setState("Состояние 2");
        System.out.println("Измененное состояние: " + originator.getState());

        originator.restoreStateFromMemento(caretaker.getSavedState()); // Восстанавливаем состояние
        System.out.println("Восстановленное состояние: " + originator.getState());
    }
}
