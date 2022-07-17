package ru.otus;

import java.util.*;

public class CustomerService {

    private Map<Customer, String> map = new HashMap<>();

    public Map.Entry<Customer, String> getSmallest() {

        List<Map.Entry<Customer, String>> list = new LinkedList<>(map.entrySet());
        Map.Entry<Customer, String> minScores = list.get(0);
        for (Map.Entry<Customer, String> entry : list) {
            if (entry.getKey().getScores() < minScores.getKey().getScores()) {
                minScores = entry;
            }
        }
        return cloneEntry(minScores);
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {

        List<Map.Entry<Customer, String>> list = new LinkedList<>(map.entrySet());
        Map.Entry<Customer, String> nextEntryByScore = null;

        boolean flag = false;
        long min = 10_000_000_000L;

        for (Map.Entry<Customer, String> entry : list) {
            if (entry.getKey().getScores() > customer.getScores()) {
                if ((entry.getKey().getScores() - customer.getScores()) < min) {
                    min = entry.getKey().getScores();
                    nextEntryByScore = entry;
                    flag = true;
                }
            }
        }
        if (flag) {
            return cloneEntry(nextEntryByScore);
        } else return null;
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
