package ru.cft.focusstart.entity;

public class Manufacturer extends Entity {

    @Override
    public String toString() {
        return this.getClass().getSimpleName() +
                "{id = " + this.getId() +
                ", title = " + this.getTitle() + '}';
    }
}