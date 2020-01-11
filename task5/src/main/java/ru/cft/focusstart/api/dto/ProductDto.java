package ru.cft.focusstart.api.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import ru.cft.focusstart.entity.Category;
import ru.cft.focusstart.entity.Manufacturer;

@JsonDeserialize(builder = ProductDto.Builder.class)
public class ProductDto extends EntityDto {
/*    private final Long id;
    private final String title;*/
    private final Long categoryId;
    private final Long manufacturerId;
    private final String vendorCode;
    private final String description;

    @JsonPOJOBuilder(withPrefix = "")
    public static class Builder {

        private Long id;
        private String title;
        private Long categoryId;
        private Long manufacturerId;
        private String vendorCode;
        private String description;

        private Builder() {
        }
/*
        private Builder(ProductDto productDto) {
            super(productDto);
            this.categoryId = productDto.categoryId;
            this.manufacturerId = productDto.manufacturerId;
            this.vendorCode = productDto.vendorCode;
            this.description = productDto.description;
        }
*/
        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder title(String name) {
            this.title = name;
            return this;
        }

        public Builder categoryId(Long categoryId) {
            this.categoryId = categoryId;
            return this;
        }

        public Builder manufacturerId(Long manufacturerId) {
            this.manufacturerId = manufacturerId;
            return this;
        }

        public Builder vendorCode(String vendorCode) {
            this.vendorCode = vendorCode;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public ProductDto build() {
            return new ProductDto(this.id, this.title, this.categoryId, this.manufacturerId, this.vendorCode, this.description);
        }
    }

    private ProductDto(Long id, String title, Long categoryId, Long manufacturerId, String vendorCode, String description) {
        super(id, title);
        this.categoryId = categoryId;
        this.manufacturerId = manufacturerId;
        this.vendorCode = vendorCode;
        this.description = description;
    }

    public static Builder builder() {
        return new Builder();
    }
/*
    public Builder toBuilder() {
        return new Builder(this);
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }*/

    public Long getCategoryId() {
        return categoryId;
    }

    public Long getManufacturerId() {
        return manufacturerId;
    }

    public String getVendorCode() {
        return vendorCode;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id = " + this.getId() +
                ", title = " + this.getTitle() +
                ", category = " + this.categoryId +
                ", manufacturer = " + this.manufacturerId +
                ", vendorCode = " + this.vendorCode +
                ", description = " + this.description + '}';
    }
}
