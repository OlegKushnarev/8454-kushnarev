package ru.focusstart.pakage1;

import java.io.IOException;
import java.util.concurrent.*;

public class Class1 {

    public static void main(String[] args) throws IOException {
        ExecutorService manufacturers = Executors.newFixedThreadPool(2);
        ExecutorService consumers = Executors.newFixedThreadPool(5);
        BlockingQueue<Product> warehouse = new ArrayBlockingQueue<>(20, true);

        while (true) {
            manufacturers.execute(new Manufacturer(warehouse));
            consumers.execute(new Consumer(warehouse));
        }
    }
}
