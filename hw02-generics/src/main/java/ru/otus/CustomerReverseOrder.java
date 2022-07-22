package ru.otus;

import java.util.Stack;

public class CustomerReverseOrder {

    private final Stack<Customer> listCustomerReverseOrder = new Stack<>();

    public void add(Customer customer) {
        listCustomerReverseOrder.push(customer);
    }

    public Customer take() {
        return listCustomerReverseOrder.pop();
    }
}
