package ru.focusstart.production;

import java.io.IOException;
import java.util.concurrent.*;

public class Main {
    private final static int NUMBER_MANUFECTURES = 5;
    private final static int NUMBER_CONSUMERS = 10;

    public static void main(String[] args) throws IOException {
        BlockingQueue<Product> warehouse = new ArrayBlockingQueue<>(20, true);

        for (int i = 1; i <= NUMBER_MANUFECTURES; ++i) {
            Thread manufacturer = new Thread(new Manufacturer(warehouse), "Производитель " + i);
            manufacturer.start();
        }

        for (int j = 1; j <= NUMBER_CONSUMERS; ++j) {
            Thread consumer = new Thread(new Consumer(warehouse), "Потребитель " + j);
            consumer.start();
        }

    }
}
