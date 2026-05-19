package com.tasks.runners;

import com.tasks.exception.FigureNotFoundException;
import com.tasks.model.Circle;
import com.tasks.model.Figure;
import com.tasks.model.IsoscelesTrapezoid;
import com.tasks.model.Rectangle;
import com.tasks.model.RightTriangle;
import com.tasks.model.Square;
import com.tasks.supplier.FigureSupplier;
import com.tasks.util.AnnotationScanner;
import com.tasks.storage.FigureStorage;

public class Task2 {
    public static void run(FigureSupplier figureSupplier) {
        FigureStorage<Figure> storage = new FigureStorage<>();
        final int FIGURES_TO_GENERATE = 5;

        for (int i = 0; i < FIGURES_TO_GENERATE; i++) {
            storage.add(figureSupplier.getRandomFigure());
        }
        System.out.println("Storage size: " + storage.size());
        int[] idsToCheck = { 0, 2, 42, 4, 99 };

        for (int id : idsToCheck) {
            try {
                storage.getById(id).draw();
            } catch (FigureNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.println("\nChecking for @DefaultArea annotation:");

        AnnotationScanner.scanDefaultAreas(Square.class, Circle.class, Rectangle.class,
                RightTriangle.class, IsoscelesTrapezoid.class);
    }
}
