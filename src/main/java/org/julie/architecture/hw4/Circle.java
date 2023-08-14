package org.julie.architecture.hw4;

/**
 * Класс круга
 */
class Circle implements Shape {
    private double radius;

    /**
     * Конструктор для круга
     * @param radius Радиус
     */
    public Circle(double radius) {
        this.radius = radius;
    }

    /**
     * Подсчет площади круга
     * @return Площадь круга
     */
    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }

    /**
     * Подсчет периметра круга
     * @return Преиметр круга
     */
    @Override
    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }
}
