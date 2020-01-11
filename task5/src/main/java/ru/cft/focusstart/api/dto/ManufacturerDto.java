package ru.cft.focusstart.api.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = ManufacturerDto.Builder.class)
public class ManufacturerDto extends EntityDto {
/*    private final Long id;
    private final String title;*/

    @JsonPOJOBuilder(withPrefix = "")
    public static class Builder {
        private Long id;
        private String title;

        private Builder() {
        }
/*
        private Builder(ManufacturerDto manufacturerDto) {
            this.id = manufacturerDto.getId();
            this.title = manufacturerDto.getTitle();
        }
*/
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
/*
    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
*/
    public static Builder builder() {
        return new Builder();
    }

/*    public Builder toBuilder() {
        return new Builder(this);
    }*/

    @Override
    public String toString() {
        return this.getClass().getSimpleName() +
                "{id = " + this.getId() +
                ", title = " + this.getTitle() + '}';
    }
}
