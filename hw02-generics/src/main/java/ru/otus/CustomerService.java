package ru.otus;

import java.util.Map;
import java.util.TreeMap;

public class CustomerService {

    private final TreeMap<Customer, String> customerMap = new TreeMap<>();

    public Map.Entry<Customer, String> getSmallest() {
        var copyMap = new TreeMap<Customer,String>();
        for (Map.Entry<Customer, String> entry : customerMap.entrySet()) {
            copyMap.put(
                    new Customer(
                            entry.getKey().getId(),
                            entry.getKey().getName(),
                            entry.getKey().getScores()
                    ),
                    entry.getValue()
            );
        }
        return copyMap.firstEntry();
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {
        var copyMap = new TreeMap<Customer,String>();
        for (Map.Entry<Customer, String> entry : customerMap.entrySet()) {
            copyMap.put(
                    new Customer(
                            entry.getKey().getId(),
                            entry.getKey().getName(),
                            entry.getKey().getScores()
                    ),
                    entry.getValue()
            );
        }
        return copyMap.higherEntry(customer);
    }

    public void add(Customer customer, String data) {
        Customer copy = new Customer(
                customer.getId(),
                customer.getName(),
                customer.getScores()
        );
        customerMap.put(copy, data);
    }
}
