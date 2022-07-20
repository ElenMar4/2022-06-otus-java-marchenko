package ru.otus;

import java.util.*;

public class CustomerService {

    private final TreeMap<Customer, String> map = new TreeMap<>(Comparator.comparingLong(Customer::getScores));

    public Map.Entry<Customer, String> getSmallest() {

        return cloneEntry(map.firstEntry());
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {

        if (map.higherEntry(customer) == null) {
            return null;
        }
        return cloneEntry(map.higherEntry(customer));
    }

    public void add(Customer customer, String data) {

        map.put(customer, data);
    }

    public Map.Entry<Customer, String> cloneEntry(Map.Entry<Customer, String> entry) {

        Customer resultCustomer = new Customer(entry.getKey().getId(),
                entry.getKey().getName(),
                entry.getKey().getScores()
        );
        String date = entry.getValue();
        Map<Customer, String> resultMap = new HashMap<>();
        resultMap.put(resultCustomer, date);
        return resultMap.entrySet()
                .stream()
                .findFirst()
                .get();
    }
}
