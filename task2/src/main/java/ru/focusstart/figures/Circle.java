package ru.focusstart.figures;

import java.text.DecimalFormat;
import java.util.List;

public class Circle extends Figure {

    private double radius;

    public Circle(double radius) {
        super("Круг");
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public double getDiameter() {
        return 2 * radius;
    }

    @Override
    public double calculateArea() {
        return Math.PI * Math.pow(radius, 2);
    }

    @Override
    public double calculatePerimetr() {
        return Math.PI * getDiameter();
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder(super.toString());

        DecimalFormat outputFormat = new DecimalFormat("##.##");

        output.append("Радиус: ").append(outputFormat.format(this.radius)).append("\n");
        output.append("Диаметр: ").append(outputFormat.format(this.getDiameter())).append("\n");

        return output.toString();
    }
}
