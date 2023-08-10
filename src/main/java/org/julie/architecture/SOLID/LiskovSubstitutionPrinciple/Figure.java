package org.julie.architecture.SOLID.LiskovSubstitutionPrinciple;

interface Figure {
    public default int getArea() {
        return 0;
    }
}
