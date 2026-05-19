package com.tasks.model;

import java.util.Objects;

public class Rectangle extends Figure {

    private final int length;
    private final int width;

    public Rectangle(String color, int length, int width) {
        super(color);
        this.length = length;
        this.width = width;
    }

    @Override
    public double getArea() {
        return length * width;
    }

    @Override
    public void draw() {
        System.out.println("Figure: rectangle, area: " + getArea() + " sq. units, length: " + length + " units, width: "
                + width + " units, color: " + getColor());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Rectangle rectangle = (Rectangle) o;
        return length == rectangle.length && width == rectangle.width;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), length, width);
    }
}
