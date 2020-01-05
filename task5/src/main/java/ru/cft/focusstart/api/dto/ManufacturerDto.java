package ru.cft.focusstart.api.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = ManufacturerDto.Builder.class)
public class ManufacturerDto {
    private final Long id;
    private final String title;

    @JsonPOJOBuilder(withPrefix = "")
    public static class Builder {

        private Long id;
        private String title;

        private Builder() {
        }

        private Builder(ManufacturerDto manufacturerDto) {
            this.id = manufacturerDto.id;
            this.title = manufacturerDto.title;
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

    public ManufacturerDto(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public static Builder builder() {
        return new ManufacturerDto.Builder();
    }

    public Builder toBuilder() {
        return new ManufacturerDto.Builder(this);
    }

    @Override
    public String toString() {
        return "Manufacturer{" +
                "id = " + this.id +
                ", title = " + this.title + '}';
    }
}
