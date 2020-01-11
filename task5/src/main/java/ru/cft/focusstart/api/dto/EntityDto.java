package ru.cft.focusstart.api.dto;

public class EntityDto {
    private final Long id;
    private final String title;

    protected EntityDto(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() +
                "{id = " + this.id +
                ", title = " + this.title + '}';
    }
}