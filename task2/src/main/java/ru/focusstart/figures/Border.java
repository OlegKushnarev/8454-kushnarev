package ru.focusstart.figures;

import java.io.IOException;
import java.util.List;

public interface Border {
    Figure getFigure(List<String> figureOptions) throws IOException;
}

class SquareBorder implements Border {

    @Override
    public Figure getFigure(List<String> figureOptions) throws IOException {
        if (figureOptions.size() < 2) {
            throw new IOException("Входной файл не содержит необходимого количества данных для расчёта фигуры.");
        }

        return new Square(Double.parseDouble(figureOptions.get(1)));
    }
}

class RectangleBorder implements Border {

    @Override
    public Figure getFigure(List<String> figureOptions) throws IOException {
        if (figureOptions.size() < 3) {
            throw new IOException("Входной файл не содержит необходимого количества данных для расчёта фигуры.");
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

class CircleBorder implements Border {

    @Override
    public Figure getFigure(List<String> figureOptions) throws IOException {
        if (figureOptions.size() < 2) {
            throw new IOException("Входной файл не содержит необходимого количества данных для расчёта фигуры.");
        }

        return new Circle(Double.parseDouble(figureOptions.get(1)));
    }
}