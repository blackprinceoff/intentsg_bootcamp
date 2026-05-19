package com.tasks.storage;

import com.tasks.model.Figure;
import com.tasks.exception.FigureNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class FigureStorage<T extends Figure> {
    private final List<T> figures = new ArrayList<>();

    public void add(T figure) {
        figures.add(figure);
    }

    public int size() {
        return figures.size();
    }

    public T getById(int id) {
        if (id < 0 || id >= size()) {
            throw new FigureNotFoundException("Figure #" + id + " not found");
        }
        return figures.get(id);
    }

}
