package com.tasks.model;

import java.util.Objects;

public class RightTriangle extends Figure {

    private final int firstLeg;
    private final int secondLeg;

    public RightTriangle(String color, int firstLeg, int secondLeg) {
        super(color);
        this.firstLeg = firstLeg;
        this.secondLeg = secondLeg;
    }

    @Override
    public double getArea() {
        return 0.5 * firstLeg * secondLeg;
    }

    @Override
    public void draw() {
        System.out.println("Figure: right triangle, area: " + getArea() + " sq. units, firstLeg: " + firstLeg
                + " units, secondLeg: " + secondLeg + " units, color: " + getColor());
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        RightTriangle that = (RightTriangle) o;
        return firstLeg == that.firstLeg && secondLeg == that.secondLeg;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), firstLeg, secondLeg);
    }
}
