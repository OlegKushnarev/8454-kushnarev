package ru.focusstart.figures;

import java.text.DecimalFormat;

public abstract class Figure {
    private String title;
    private double area;
    private double perimeter;

    public Figure(String title) {
        this.title = title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public double getArea() {
        return area;
    }

    public double getPerimeter() {
        return perimeter;
    }

    public String getOutput() {
        StringBuilder output = new StringBuilder();

        DecimalFormat outputFormat = new DecimalFormat("##.##");

        output.append("Тип фигуры: ").append(this.title).append("\n");
        output.append("Площадь: ").append(outputFormat.format(this.area)).append("\n");
        output.append("Периметр: ").append(outputFormat.format(this.perimeter)).append("\n");

        return output.toString();
    }

    @Override
    public String toString() {
        return this.getOutput();
    }

    public abstract double calculateArea();
    public abstract double calculatePerimetr();

    public void calculateFigure() {
        this.area = this.calculateArea();
        this.perimeter = this.calculatePerimetr();
    }
}
