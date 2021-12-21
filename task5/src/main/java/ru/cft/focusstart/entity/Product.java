package ru.cft.focusstart.entity;

public class Product extends Entity {
    private Category category;
    private Manufacturer manufacturer;
    private String vendorCode;
    private String description;

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
        return this.getClass().getSimpleName() +
                "{id = " + this.getId() +
                ", title = " + this.getTitle() +
                ", category = " + this.category +
                ", manufacturer = " + this.manufacturer +
                ", vendorCode = " + this.vendorCode +
                ", description = " + this.description + '}';
    }
}