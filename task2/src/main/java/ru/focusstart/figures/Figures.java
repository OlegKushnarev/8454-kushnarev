package ru.focusstart.figures;

public enum Figures {
    CIRCLE (new CircleBorder()),
    SQUARE (new SquareBorder()),
    RECTANGLE (new RectangleBorder()),
    NONE(null);

    private Border border;

    public Border getBorder() throws IllegalArgumentException{
        return border;
    }

    Figures(Border border) {
        this.border = border;
    }
}
