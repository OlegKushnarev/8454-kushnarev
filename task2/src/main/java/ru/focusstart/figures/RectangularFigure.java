package ru.focusstart.figures;

public abstract class RectangularFigure extends Figure {
    private double diagonal;

    public RectangularFigure(String title) {
        super(title);
    }

    public double getDiagonal() {
        return diagonal;
    }

    protected abstract double calculateDiagonal();

    @Override
    public void calculateFigure() {
        super.calculateFigure();
        this.diagonal = this.calculateDiagonal();
    }
}
