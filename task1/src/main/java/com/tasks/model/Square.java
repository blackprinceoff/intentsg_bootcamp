package com.tasks.model;

import java.util.Objects;

public class Square extends Figure {

    private final int side;

    public Square(String color, int side) {
        super(color);
        this.side = side;
    }

    @Override
    public double getArea() {
        return side * side;
    }

    @Override
    public void draw() {
        System.out.println(
                "Figure: square, area: " + getArea() + " sq. units, side: " + side + " units, color: " + getColor());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Square square = (Square) o;
        return side == square.side;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), side);
    }
}
