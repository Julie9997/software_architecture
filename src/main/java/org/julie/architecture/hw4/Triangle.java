package org.julie.architecture.hw4;

/**
 * Класс треугольника
 */
class Triangle implements Shape {
    private double side1;
    private double side2;
    private double side3;

    /**
     * Конструктор класса треугольник
     * @param side1 Сторона 1
     * @param side2 Сторона 2
     * @param side3 Сторона 3
     */
    public Triangle(double side1, double side2, double side3) {
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
    }

    /**
     * Подсчет площади треугольника
     * @return Площадь треугольника
     */
    @Override
    public double getArea() {
        double s = (side1 + side2 + side3) / 2;
        return Math.sqrt(s * (s - side1) * (s - side2) * (s - side3));
    }

    /**
     * Подсчет периметра треугольника
     * @return Преиметр треугольника
     */
    @Override
    public double getPerimeter() {
        return side1 + side2 + side3;
    }
}
