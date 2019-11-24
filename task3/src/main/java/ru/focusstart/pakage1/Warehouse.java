package ru.focusstart.pakage1;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Warehouse {
    private static final int MAX_CAPACITY = 10;
    private int numberProduct = 0;
    private Queue<Product> products;

    public Warehouse() {
        products =  new ConcurrentLinkedQueue<>();
    }

    public boolean accept(Product product) {
        if (numberProduct <= MAX_CAPACITY) {
            return products.offer(product);
        }
        return false;
    }

    public Product giveOut() {
        if (!products.isEmpty()) {
            return products.poll();
        }
        return null;
    }
}
