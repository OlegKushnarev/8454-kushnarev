package ru.focusstart.figures;

import java.util.List;

public interface FigureCreator {
    Figure getFigure(List<String> figureOptions) throws IllegalArgumentException;
}

class SquareCreator implements FigureCreator {
    @Override
    public Figure getFigure(List<String> figureOptions) throws IllegalArgumentException {
        if (figureOptions.size() < 2) {
            throw new IllegalArgumentException("Входной файл не содержит необходимого количества данных для расчёта фигуры.");
        }
        return new Square(Double.parseDouble(figureOptions.get(1)));
    }
}

class RectangleCreator implements FigureCreator {
    @Override
    public Figure getFigure(List<String> figureOptions) throws IllegalArgumentException {
        if (figureOptions.size() < 3) {
            throw new IllegalArgumentException("Входной файл не содержит необходимого количества данных для расчёта фигуры.");
        }

        double longSide = Double.parseDouble(figureOptions.get(1));
        double shortSide = Double.parseDouble(figureOptions.get(2));
        if (longSide < shortSide) {
            double tmp = longSide;
            longSide = shortSide;
            shortSide = tmp;
        }

        return new Rectangle(longSide, shortSide);
    }
}

class CircleCreator implements FigureCreator {
    @Override
    public Figure getFigure(List<String> figureOptions) throws IllegalArgumentException {
        if (figureOptions.size() < 2) {
            throw new IllegalArgumentException("Входной файл не содержит необходимого количества данных для расчёта фигуры.");
        }

        return new Circle(Double.parseDouble(figureOptions.get(1)));
    }
}