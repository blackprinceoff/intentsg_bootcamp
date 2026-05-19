package com.tasks.runners;

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

        Map<String, List<Figure>> groupedMap = groupByFigureType(figures);

        uniqueFiguresByColor(figures);
        topThreeFiguresByArea(figures);
        avgAreaByColor(figures);
        immutableCatalog(groupedMap);
    }

    private static Map<String, List<Figure>> groupByFigureType(List<Figure> figures) {
        Map<String, List<Figure>> groupedByType = new HashMap<>();
        for (Figure figure : figures) {
            groupedByType.computeIfAbsent(figure.getClass().getSimpleName(), k -> new ArrayList<>()).add(figure);
        }
        groupedByType.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(entry -> {
                    double sum = entry.getValue().stream()
                            .mapToDouble(Figure::getArea)
                            .sum();
                    System.out.println(
                            entry.getKey() + ": count = " + entry.getValue().size() + ", total area = " + sum);
                });
        return groupedByType;
    }

    private static void uniqueFiguresByColor(List<Figure> figures) {
        Map<String, Set<Figure>> uniqueByColor = new HashMap<>();
        for (Figure figure : figures) {
            uniqueByColor.computeIfAbsent(figure.getColor(), k -> new HashSet<>()).add(figure);
        }
        uniqueByColor.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(entry -> {
                    System.out.println(entry.getKey() + ": unique figures = " + entry.getValue().size());
                });

        Set<Figure> reds = uniqueByColor.computeIfAbsent("red", k -> new HashSet<>());
        int before = reds.size();

        reds.add(new Circle("red", 5));
        reds.add(new Circle("red", 5));

        int after = reds.size();

        System.out.println("Red Set size: " + before + " -> " + after + " (expected +1)");
    }

    // list.sort() calls Collections.sort() under the hood in Java 8+.
    // Both modify the list in-place, but using a Stream is safer here to avoid side effects.

    private static void topThreeFiguresByArea(List<Figure> figures) {
        figures.stream().sorted(Comparator.comparingDouble(Figure::getArea).reversed())
                .limit(3)
                .forEach(figure -> System.out.println(
                        figure.getClass().getSimpleName() + " [" + figure.getColor() + "] area=" + figure.getArea()));
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
                .sorted(Map.Entry.comparingByKey())
                .forEach(entry -> {
                    double avg = entry.getValue()[0] / entry.getValue()[1];
                    System.out.println(entry.getKey() + ": average area = " + avg);
                });
    }

    private static void immutableCatalog(Map<String, List<Figure>> originalMap) {

        Map<String, List<Figure>> unmodifiable = Collections.unmodifiableMap(originalMap);

        try {
            unmodifiable.put("test", new ArrayList<>());
        } catch (UnsupportedOperationException e) {
            System.out.println("UnsupportedOperationException is thrown because the map is read-only.");
        }

        originalMap.get("Circle").add(new Circle("black", 10));
        System.out.println(unmodifiable.get("Circle").size());
    }
}
