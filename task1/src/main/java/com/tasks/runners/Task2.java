package com.tasks.runners;

import java.lang.reflect.Method;

import com.tasks.annotation.DefaultArea;
import com.tasks.exception.FigureNotFoundException;
import com.tasks.model.Circle;
import com.tasks.model.Figure;
import com.tasks.model.IsoscelesTrapezoid;
import com.tasks.model.Rectangle;
import com.tasks.model.RightTriangle;
import com.tasks.model.Square;
import com.tasks.supplier.FigureSupplier;
import com.tasks.storage.FigureStorage;

public class Task2 {
    public static void run(FigureSupplier figureSupplier) {
        FigureStorage<Figure> storage = new FigureStorage<>();

        for (int i = 0; i < 5; i++) {
            storage.add(figureSupplier.getRandomFigure());
        }
        System.out.println("Storage is: " + storage.size());
        int[] idsToCheck = {0, 2, 42, 4, 99};

        for (int id : idsToCheck) {
            try {
                storage.getById(id).draw();
            } catch (FigureNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.println("\nChecking for @DefaultArea annotation:");

        checkDefaultAreaAnnotation(Square.class, Circle.class, Rectangle.class,
                RightTriangle.class, IsoscelesTrapezoid.class);
    }

    public static void checkDefaultAreaAnnotation(Class<?>... classes) {
        for (Class<?> clazz : classes) {
            for (Method method : clazz.getDeclaredMethods()) {
                if (method.isAnnotationPresent(DefaultArea.class)) {
                    System.out.println("Method " + method.getName() + " in class " + clazz.getSimpleName()
                            + " is marked as @DefaultArea");
                }
            }
        }
    }
}
