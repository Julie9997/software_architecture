package org.julie.architecture.hw2.iterator;

import java.util.NoSuchElementException;

// Создаем интерфейс итератора
interface Iterator<T> {
    boolean hasNext(); // Метод, проверяющий наличие следующего элемента
    T next(); // Метод, который возвращает следующий элемент
}

// Создаем интерфейс коллекции
interface Iterable<T> {
    Iterator<T> iterator(); // Метод, который возвращает итератор
}

// Создаем простую коллекцию
class MyCollection<T> implements Iterable<T> {
    private T[] elements; // Массив для хранения элементов коллекции

    public MyCollection(T[] elements) {
        this.elements = elements;
    }

    // Реализуем метод, который возвращает итератор для данной коллекции
    @Override
    public Iterator<T> iterator() {
        return new MyIterator();
    }

    // Приватный класс, реализующий итератор для данной коллекции
    private class MyIterator implements Iterator<T> {
        private int currentIndex; // Индекс текущего элемента

        public MyIterator() {
            currentIndex = 0; // Начинаем с индекса 0
        }

        // Проверяем наличие следующего элемента
        @Override
        public boolean hasNext() {
            return currentIndex < elements.length;
        }

        // Возвращаем следующий элемент и увеличиваем индекс
        @Override
        public T next() {
            if (hasNext()) {
                T currentElement = elements[currentIndex];
                currentIndex++;
                return currentElement;
            }
            throw new NoSuchElementException(); // Бросаем исключение, если больше нет элементов
        }
    }
}

// Использование итератора и коллекции
public class Main {
    public static void main(String[] args) {
        // Создаем массив с элементами
        Integer[] numbers = {1, 2, 3, 4, 5};

        // Создаем экземпляр коллекции
        MyCollection<Integer> collection = new MyCollection<>(numbers);

        // Получаем итератор
        Iterator<Integer> iterator = collection.iterator();

        // Перебираем элементы с помощью итератора и выводим их на экран
        while (iterator.hasNext()) {
            Integer number = iterator.next();
            System.out.println(number);
        }
    }
}

