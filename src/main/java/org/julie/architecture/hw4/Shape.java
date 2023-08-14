package org.julie.architecture.hw4;

// Интерфейс для геометрических фигур
interface Shape {
    /**
     * Вычисляет площадь фигуры.
     *
     * @return Площадь фигуры.
     */
    double getArea();
    /**
     * Вычисляет периметр фигуры.
     *
     * @return Периметр фигуры.
     */
    double getPerimeter();
}