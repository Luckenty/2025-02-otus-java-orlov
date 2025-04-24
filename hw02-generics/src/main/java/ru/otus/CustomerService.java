package ru.otus;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class CustomerService {

    private final TreeMap<Customer, String> customerMap = new TreeMap<>(
            Comparator.comparingLong(Customer::getScores).thenComparingLong(Customer::getId)
    );

    public Map.Entry<Customer, String> getSmallest() {
        var copy = customerMap.firstEntry();
        if (copy != null) {
            return Map.entry(new Customer(
                            copy.getKey().getId(),
                            copy.getKey().getName(),
                            copy.getKey().getScores()
                    ),
                    copy.getValue());
        } else {
            return null;
        }
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {
        var copy = customerMap.higherEntry(customer);
        if (copy != null) {
            return Map.entry(new Customer(
                            copy.getKey().getId(),
                            copy.getKey().getName(),
                            copy.getKey().getScores()
                    ),
                    copy.getValue());
        } else {
            return null;
        }
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
