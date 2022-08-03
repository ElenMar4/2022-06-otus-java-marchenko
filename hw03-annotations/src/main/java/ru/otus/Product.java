package ru.otus;

import java.util.Objects;

public class Product {

    private String vendorCode;
    private String firmName;
    private String name;
    private double price;

    public Product(String vendorCode, String firmName, String name, double price) {
        this.vendorCode = vendorCode;
        this.firmName = firmName;
        this.name = name;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void validateVendorCode() {
        if (this.vendorCode.isBlank()) {
            throw new NullPointerException("vendorCode can't be null or empty");
        }
    }

    public void validateFirmName() {
        if (this.firmName.isBlank()) {
            throw new NullPointerException("firmName can't be null or empty");
        }
    }

    public void validateName() {
        if (this.name.isBlank()) {
            throw new NullPointerException("name can't be null or empty");
        }
    }

    public void validatePrice() {
        if (this.price <= 0) {
            throw new NumberFormatException("name can't be negative number");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product product)) return false;
        return Objects.equals(vendorCode, product.vendorCode) && Objects.equals(firmName, product.firmName) && Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vendorCode, firmName, name);
    }

    @Override
    public String toString() {
        return "Product{" +
                "vendorCode='" + vendorCode + '\'' +
                ", firmName='" + firmName + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
