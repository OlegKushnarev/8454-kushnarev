package ru.focusstart.production;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {
    private static final long CONSUME_TIME = 5000;
    private static final Logger log = LoggerFactory.getLogger(Manufacturer.class);
    private BlockingQueue<Product> warehouse;

    public Consumer(BlockingQueue<Product> warehouse) {
        this.warehouse = warehouse;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Product product = warehouse.take();
                log.info(product + " " + Events.valueOf("TAKEN").getEvent() + "\n");
                Thread.sleep(CONSUME_TIME);
                log.info(product + " " + Events.valueOf("CONSUMED").getEvent() + "\n");
            }
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
