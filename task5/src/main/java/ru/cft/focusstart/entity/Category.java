package ru.cft.focusstart.entity;

public class Category extends Entity {

    @Override
    public String toString() {
        return this.getClass().getSimpleName() +
                "{id = " + this.getId() +
                ", title = " + this.getTitle() + '}';
    }
}