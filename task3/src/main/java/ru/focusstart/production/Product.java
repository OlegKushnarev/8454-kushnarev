package ru.focusstart.production;

public class Product {
    private static int count = 0;
    private int id;

    public Product() {
        this.id = ++count;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Ресурс " + id;
    }
}
