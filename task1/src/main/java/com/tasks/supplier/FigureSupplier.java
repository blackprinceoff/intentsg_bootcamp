package com.tasks.supplier;

import com.tasks.model.Circle;
import com.tasks.model.Figure;
import com.tasks.model.FigureType;
import com.tasks.model.IsoscelesTrapezoid;
import com.tasks.model.Rectangle;
import com.tasks.model.RightTriangle;
import com.tasks.model.Square;

import java.util.Random;

public class FigureSupplier {

    private final Random random = new Random();
    private final ColorSupplier colorSupplier = new ColorSupplier();

    public Figure getRandomFigure() {
        FigureType type = FigureType.values()[random.nextInt(FigureType.values().length)];
        switch (type) {
            case SQUARE:
                return new Square(colorSupplier.getRandomColor(), random.nextInt(10) + 1);
            case CIRCLE:
                return new Circle(colorSupplier.getRandomColor(), random.nextInt(10) + 1);
            case RECTANGLE:
                return new Rectangle(colorSupplier.getRandomColor(), random.nextInt(10) + 1, random.nextInt(10) + 1);
            case RIGHT_TRIANGLE:
                return new RightTriangle(colorSupplier.getRandomColor(), random.nextInt(10) + 1,
                        random.nextInt(10) + 1);
            case ISOSCELES_TRAPEZOID:
                return new IsoscelesTrapezoid(colorSupplier.getRandomColor(), random.nextInt(10) + 1,
                        random.nextInt(10) + 1,
                        random.nextInt(10) + 1);
            default:
                return getDefaultFigure();
        }
    }

    public Figure getDefaultFigure() {
        return new Circle("white", 10);
    }

}
