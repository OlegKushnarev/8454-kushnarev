package ru.cft.focusstart.entity;

public class Product {
    private Long id;
    private String title;
    private Category category;
    private Manufacturer manufacturer;
    private String vendorCode;
    private String description;

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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getVendorCode() {
        return vendorCode;
    }

    public void setVendorCode(String vendorCode) {
        this.vendorCode = vendorCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id = " + this.id +
                ", title = " + this.title +
                ", category = " + this.category +
                ", manufacturer = " + this.manufacturer +
                ", vendorCode = " + this.vendorCode +
                ", description = " + this.description + '}';
    }
}
