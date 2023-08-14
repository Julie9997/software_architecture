package org.julie.architecture.hw4;

/**
 * Класс прямоугольника
 */
class Rectangle implements Shape {
    private double length;
    private double width;

    /**
     * Конструктор для прямоугольника
     * @param length Длина
     * @param width Ширина
     */
    public Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }

    /**
     * Подсчет площади прямоугольника
     * @return Площадь прямоугольника
     */
    @Override
    public double getArea() {
        return length * width;
    }

    /**
     * Подсчет периметра прямоугольника
     * @return Преиметр прямоугольника
     */
    @Override
    public double getPerimeter() {
        return 2 * (length + width);
    }
}
