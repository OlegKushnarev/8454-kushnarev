package ru.focusstart.figures;

import java.text.DecimalFormat;

public class Square extends Figure {
    private double widthSide;
    private double diagonal;

    public Square(double widthSide) {
        super("Квадрат");
        this.widthSide = widthSide;
    }

    public double getDiagonal() {
        return diagonal;
    }

    public double getWidthSide() {
        return widthSide;
    }

    public void setWidthSide(double widthSide) {
        this.widthSide = widthSide;
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
    public void calculateFigure() {
        super.calculateFigure();
        this.diagonal = this.calculateDiagonal();
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder(super.toString());

        DecimalFormat outputFormat = new DecimalFormat("##.##");
        output.append("Длина стороны: ").append(outputFormat.format(this.widthSide)).append("\n");
        output.append("Длина диагонали: ").append(outputFormat.format(this.diagonal)).append("\n");

        return output.toString();
    }
}
