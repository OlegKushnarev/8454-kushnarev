package ru.cft.focusstart.api.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = ManufacturerDto.Builder.class)
public class ManufacturerDto extends EntityDto {

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

        public ManufacturerDto build() {
            return new ManufacturerDto(this.id, this.title);
        }
    }

    private ManufacturerDto(Long id, String title) {
        super(id, title);
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() +
                "{id = " + this.getId() +
                ", title = " + this.getTitle() + '}';
    }
}
