package org.julie.architecture.SOLID.LiskovSubstitutionPrinciple;

public class Square implements Figure {
    private  int side;

    Square(int side){
        this.side = side;
    }
    @Override
    public int getArea() {
        return side * side;
    }
}
