package com.tasks.runners;

import com.tasks.model.Figure;
import com.tasks.supplier.FigureSupplier;

public class Task1 {
    public static void run(FigureSupplier figureSupplier) {
        Figure[] figures = new Figure[6];

        for (int i = 0; i < figures.length; i++) {
            if (i < figures.length / 2) {
                figures[i] = figureSupplier.getRandomFigure();
            } else {
                figures[i] = figureSupplier.getDefaultFigure();
            }
        }

        for (Figure figure : figures) {
            figure.draw();
        }
    }
}
