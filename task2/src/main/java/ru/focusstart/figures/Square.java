package ru.focusstart.figures;

import java.text.DecimalFormat;

public class Square extends RectangularFigure {
    private double widthSide;

    public Square(double widthSide) {
        super("Квадрат");
        this.widthSide = widthSide;
    }

    public double getWidthSide() {
        return widthSide;
    }

    public double calculateDiagonal() {
        return Math.sqrt(2 * this.getArea());
    }

    @Override
    public double calculateArea() {
        return Math.pow(widthSide, 2);
    }

    @Override
    public double calculatePerimetr() {
        return 4 * widthSide;
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder(super.toString());

        DecimalFormat outputFormat = new DecimalFormat("##.##");
        output.append("Длина стороны: ").append(outputFormat.format(this.widthSide)).append("\n");
        output.append("Длина диагонали: ").append(outputFormat.format(this.getDiagonal())).append("\n");
        return output.toString();
    }
}
