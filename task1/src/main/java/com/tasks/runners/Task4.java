package com.tasks.runners;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import com.tasks.model.Circle;
import com.tasks.model.Figure;
import com.tasks.supplier.FigureSupplier;

public class Task4 {
    public static void run(FigureSupplier figureSupplier) {
        List<Figure> figures = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            figures.add(figureSupplier.getRandomFigure());
        }

        testPredicates(figures);
        mapToDescriptions(figures);
        countByType(figures);
        findFirstCircle(figures);
    }

    private static void testPredicates(List<Figure> figures) {
        Predicate<Figure> isLarge = (figure) -> figure.getArea() > 50;
        Predicate<Figure> isRed = (figure) -> figure.getColor().equals("red");
        System.out.println(figures.stream().filter(isLarge).count());
        System.out.println(figures.stream().filter(isLarge.and(isRed)).count());
    }

    private static void mapToDescriptions(List<Figure> figures) {
        figures.stream()
                .map(f -> f.getClass().getSimpleName() + "[" + f.getColor() + "] area=" + f.getArea())
                .forEach(System.out::println);
    }

    private static void countByType(List<Figure> figures) {
        Map<String, Long> countMap = figures.stream()
                .collect(Collectors.groupingBy(f -> f.getClass().getSimpleName(), Collectors.counting()));
        System.out.println(countMap);
    }

    private static void findFirstCircle(List<Figure> figures) {
        Optional<Figure> firstCircle = figures.stream()
                .filter(f -> f instanceof Circle)
                .findFirst();
        System.out.println(
                firstCircle.map(f -> f.getClass().getSimpleName() + "[" + f.getColor() + "] area=" + f.getArea())
                        .orElse("no circle in the list"));

        List<Figure> noCircles = new ArrayList<>(figures);
        noCircles.removeIf(f -> f instanceof Circle);
        System.out.println(noCircles.stream().filter(f -> f instanceof Circle).findFirst()
                .map(f -> f.getClass().getSimpleName() + "[" + f.getColor() + "] area=" + f.getArea())
                .orElse("no circle in the list"));
    }

}
