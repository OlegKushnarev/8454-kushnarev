package ru.focusstart.figures;

import java.text.DecimalFormat;
import java.util.List;

public class Rectangle extends RectangularFigure {
    private double longSide;
    private double shortSide;

    public Rectangle(double longSide, double shortSide) {
        super("Прямоугольник");
        this.longSide = longSide;
        this.shortSide = shortSide;
    }

    @Override
    public double calculateArea() {
        return shortSide * longSide;
    }

    @Override
    public double calculatePerimetr() {
        return 2 * (shortSide + longSide);
    }

    @Override
    public double calculateDiagonal() {
        return Math.sqrt(Math.pow(shortSide, 2) + Math.pow(longSide, 2));
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder(this.getOutput());
        DecimalFormat outputFormat = new DecimalFormat("##.##");
        output.append("Длина (размер длинной стороны): ").append(outputFormat.format(longSide)).append("\n");
        output.append("Ширина (размер короткой стороны): ").append(outputFormat.format(this.shortSide)).append("\n");
        output.append("Длина диагонали: ").append(outputFormat.format(this.getDiagonal())).append("\n");
        return output.toString();
    }
}
