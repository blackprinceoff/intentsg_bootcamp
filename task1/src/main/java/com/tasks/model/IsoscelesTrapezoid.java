package com.tasks.model;

import java.util.Objects;

public class IsoscelesTrapezoid extends Figure {

    private final int topBase;
    private final int bottomBase;
    private final int height;

    public IsoscelesTrapezoid(String color, int topBase, int bottomBase, int height) {
        super(color);
        this.topBase = topBase;
        this.bottomBase = bottomBase;
        this.height = height;
    }

    @Override
    public double getArea() {
        return 0.5 * (topBase + bottomBase) * height;
    }

    @Override
    public void draw() {
        System.out.printf(
                "Figure: isosceles trapezoid, area: %s sq. units, top base: %d units, bottom base: %d units, height: %d units, color: %s%n",
                getArea(), topBase, bottomBase, height, getColor());

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        IsoscelesTrapezoid that = (IsoscelesTrapezoid) o;
        return topBase == that.topBase && bottomBase == that.bottomBase && height == that.height;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), topBase, bottomBase, height);
    }
}
