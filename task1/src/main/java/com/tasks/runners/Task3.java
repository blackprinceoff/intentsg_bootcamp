package com.tasks.runners;

import java.lang.instrument.Instrumentation;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.tasks.model.Circle;
import com.tasks.model.Figure;
import com.tasks.supplier.FigureSupplier;

public class Task3 {
    public static void run(FigureSupplier figureSupplier) {
        List<Figure> figures = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            figures.add(figureSupplier.getRandomFigure());
        }

        groupByFigureType(figures);
        uniqueFiguresByColor(figures);
        topThreeFiguresByArea(figures);
        avgAreaByColor(figures);
        immutableCatalog(figures);
    }

    private static Map<String, List<Figure>> groupByFigureType(List<Figure> figures) {
        Map<String, List<Figure>> groupedByType = new HashMap<>();
        for (Figure figure : figures) {
            groupedByType.computeIfAbsent(figure.getClass().getSimpleName(), k -> new ArrayList<>()).add(figure);
        }
        groupedByType.entrySet().stream()
                .sorted((entry1, entry2) -> entry1.getKey().compareTo(entry2.getKey()))
                .forEach(entry -> {
                    double sum = entry.getValue().stream()
                            .mapToDouble(Figure::getArea)
                            .sum();
                    System.out.println(
                            entry.getKey() + ": кількість = " + entry.getValue().size() + ", загальна площа = " + sum);
                });
        return groupedByType;
    }

    private static void uniqueFiguresByColor(List<Figure> figures) {
        Map<String, Set<Figure>> uniqueByColor = new HashMap<>();
        for (Figure figure : figures) {
            uniqueByColor.computeIfAbsent(figure.getColor(), k -> new HashSet<>()).add(figure);
        }
        uniqueByColor.entrySet().stream()
                .sorted((entry1, entry2) -> entry1.getKey().compareTo(entry2.getKey()))
                .forEach(entry -> {
                    System.out.println(entry.getKey() + ": унікальних фігур = " + entry.getValue().size());
                });

        Set<Figure> uniqueFigures = new HashSet<>();
        Figure uniqueFigure1 = new Circle("red", 5);
        Figure uniqueFigure2 = new Circle("red", 5);
        uniqueFigures.add(uniqueFigure1);
        uniqueFigures.add(uniqueFigure2);

        System.out.println("Кількість унікальних фігур: " + uniqueFigures.size());
    }

    private static void topThreeFiguresByArea(List<Figure> figures) {
        figures.sort(Comparator.comparingDouble(Figure::getArea).reversed());
        for (int i = 0; i < 3; i++) {
            Figure f = figures.get(i);
            System.out.println(f.getClass().getSimpleName() + " [" + f.getColor() + "] area=" + f.getArea());
        }
    }

    private static void avgAreaByColor(List<Figure> figures) {
        Map<String, double[]> statsByColor = new HashMap<>();
        for (Figure figure : figures) {
            statsByColor.merge(figure.getColor(), new double[] { figure.getArea(), 1 },
                    (oldValue, newValue) -> new double[] {
                            oldValue[0] + newValue[0],
                            oldValue[1] + newValue[1]
                    });
        }

        statsByColor.entrySet().stream()
                .sorted((entry1, entry2) -> entry1.getKey().compareTo(entry2.getKey()))
                .forEach(entry -> {
                    double avg = entry.getValue()[0] / entry.getValue()[1];
                    System.out.println(entry.getKey() + ": середня площа = " + avg);
                });
    }

    private static void immutableCatalog(List<Figure> figures) {

        Map<String, List<Figure>> originalMap = groupByFigureType(figures);
        Map<String, List<Figure>> unmodifiable = Collections.unmodifiableMap(originalMap);

        try {
            unmodifiable.put("test", new ArrayList<>());
        } catch (UnsupportedOperationException e) {
            System.out.println("Летить UnsupportedOperationException, бо мапа read-only.");
        }

        originalMap.get("Circle").add(new Circle("black", 10));
        System.out.println(unmodifiable.get("Circle").size());
    }
}
