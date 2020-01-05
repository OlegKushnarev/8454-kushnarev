package ru.cft.focusstart.api.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = CategoryDto.Builder.class)
public class CategoryDto {
    private final Long id;
    private final String name;

    @JsonPOJOBuilder(withPrefix = "")
    public static class Builder {

        private Long id;

        private String name;

        private Builder() {
        }

        private Builder(CategoryDto categoryDto) {
            this.id = categoryDto.id;
            this.name = categoryDto.name;
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public CategoryDto build() {
            return new CategoryDto(this.id, this.name);
        }
    }

    public CategoryDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static Builder builder() {
        return new Builder();
    }

    public Builder toBuilder() {
        return new Builder(this);
    }

    @Override
    public String toString() {
        return "Category{" +
                "id = " + this.id +
                ", name = " + this.name + '}';
    }
}
