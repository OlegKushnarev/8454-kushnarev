package ru.cft.focusstart.entity;

public class Manufacturer {
    private Long id;
    private String title;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Manufacturer{" +
                "id = " + this.id +
                ", title = " + this.title + '}';
    }
}
