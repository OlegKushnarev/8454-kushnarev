package ru.focusstart.figures;

public enum Figures {
    CIRCLE(new CircleCreator()),
    SQUARE(new SquareCreator()),
    RECTANGLE(new RectangleCreator()),
    NONE(null);

    private FigureCreator figureCreator;

    public FigureCreator getFigureCreator() throws IllegalArgumentException {
        return figureCreator;
    }

    Figures(FigureCreator figureCreator) {
        this.figureCreator = figureCreator;
    }
}
