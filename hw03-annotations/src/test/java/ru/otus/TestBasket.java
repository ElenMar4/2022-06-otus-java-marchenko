package ru.otus;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestBasket {

    Product product1 = new Product("LL123", "LENOVO", "laptop", 50000.00);
    Product product2 = new Product("LA854", "ACER", "laptop", 80000.00);
    Product product3 = new Product("KR562", "Red Square Keyrox", "keyboard", 5000.00);

    Basket testOrder = new Basket();

    @BeforeEach
    public void setup() {
        testOrder = new Basket();
    }


    @org.junit.jupiter.api.Test
    @DisplayName("записывает и выдает продукт")
    public void getOrder() {
        testOrder.addProductInOrder(product1);
        assertThat(testOrder.getOrder().get(0)).isEqualTo(product1);
    }


    @Test
    @DisplayName("добавляет новый продукт в заказ")
    public void addProductInOrder() {
        testOrder.addProductInOrder(product1);
        assertFalse(testOrder.getOrder().isEmpty());
        assertEquals(1, testOrder.getOrder().size());
    }


    @Test
    @DisplayName("выбрасывает исключение NullPointerException, если добавили null")
    public void addNullInOrder() {
        assertThrows(NullPointerException.class, () -> testOrder.addProductInOrder(null));
    }


    @Test
    @DisplayName("удаляет продукт из заказа")
    public void removeProductInOrder() {
        testOrder.addProductInOrder(product1);
        testOrder.addProductInOrder(product2);
        testOrder.removeProductInOrder(product1);
        Product product = null;
        for (Product tempProduct : testOrder.getOrder()) {
            if (tempProduct.equals(product1)) {
                product = tempProduct;
            }
        }
        assertThat(product).isEqualTo(null);
    }


    @Test
    @DisplayName("считает стоимость заказа")
    public void getSumOrder() {
        testOrder.addProductInOrder(product1);
        testOrder.addProductInOrder(product2);
        testOrder.addProductInOrder(product3);
        double sum = product1.getPrice() + product2.getPrice() + product3.getPrice();
        assertThat(sum).isEqualTo(testOrder.getSumOrder());
    }

    @AfterEach
    public void tearDown() {
        testOrder.getOrder().clear();
    }
}