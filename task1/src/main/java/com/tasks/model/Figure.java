package com.tasks.model;

import java.util.Objects;

public abstract class Figure {

    private final String color;

    public Figure(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public abstract double getArea();

    public abstract void draw();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Figure figure = (Figure) o;
        return Objects.equals(getColor(), figure.getColor());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getColor());
    }
}
