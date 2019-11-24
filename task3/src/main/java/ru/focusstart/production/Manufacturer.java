package ru.focusstart.production;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.BlockingQueue;

public class Manufacturer implements Runnable {
    private static final long WORK_TIME = 3000;
    private static final Logger log = LoggerFactory.getLogger(Manufacturer.class);
    private BlockingQueue<Product> warehouse;

    public Manufacturer(BlockingQueue<Product> warehouse) {
        this.warehouse = warehouse;
    }

    public synchronized Product produce() {
        return new Product();
    }

    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(WORK_TIME);
                Product product = this.produce();
                log.info(product + " " + Events.valueOf("MADE").getEvent() + "\n");
                warehouse.put(product);
                log.info(product + " " + Events.valueOf("PUT").getEvent() + "\n");
            }
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
