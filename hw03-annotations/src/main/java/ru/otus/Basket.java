package ru.otus;

import java.util.ArrayList;
import java.util.List;

public class Basket {

    private final List<Product> order = new ArrayList<>();

    public List<Product> getOrder() {
        return order;
    }

    public void addProductInOrder(Product product) {
        validateProduct(product);
        order.add(product);
    }

    public void removeProductInOrder(Product product) {
        order.remove(product);
    }

    public double getSumOrder() {
        double sum = 0;
        for (Product product : order) {
            sum += product.getPrice();
        }
        return sum;
    }

    public void validateProduct( Product product) {
        product.validateVendorCode();
        product.validateFirmName();
        product.validateName();
        product.validatePrice();
    }

    @Override
    public String toString() {
        return "ServiceOrder{" +
                "order=" + order +
                '}';
    }
}
