package ru.cft.focusstart.api.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = CategoryDto.Builder.class)
public class CategoryDto extends EntityDto {

    @JsonPOJOBuilder(withPrefix = "")
    public static class Builder {
        private Long id;
        private String title;

        private Builder() {
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public CategoryDto build() {
            return new CategoryDto(this.id, this.title);
        }
    }

    private CategoryDto(Long id, String title) {
        super(id, title);
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public String toString() {
        return "Category{" +
                "id = " + this.getId() +
                ", name = " + this.getTitle() + '}';
    }
}
