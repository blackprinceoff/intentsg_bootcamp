package com.tasks;

import com.tasks.runners.Task1;
import com.tasks.runners.Task2;
import com.tasks.runners.Task3;
import com.tasks.supplier.FigureSupplier;

public class Main {
    public static void main(String[] args) {
        // Task1.run(new FigureSupplier());
        // Task2.run(new FigureSupplier());
        Task3.run(new FigureSupplier());
    }
}