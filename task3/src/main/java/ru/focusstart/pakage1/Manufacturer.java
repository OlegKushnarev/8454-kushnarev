package ru.focusstart.pakage1;

import ru.focusstart.log.FormatOutput;

import java.util.concurrent.BlockingQueue;
import java.util.logging.*;

public class Manufacturer implements Runnable {
    private static final Logger LOGGER = Logger.getLogger(Manufacturer.class.getName());
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
            Handler consoleHandler = new ConsoleHandler();
            consoleHandler.setFormatter(new FormatOutput());
            LOGGER.setUseParentHandlers(false);
            LOGGER.addHandler(consoleHandler);

            Product product = this.produce();
            LOGGER.info("Производитель " + product + " " + Events.valueOf("MADE").getEvent());
            Thread.sleep(3000);
            warehouse.put(product);
            LOGGER.info("Производитель " + product + " " + Events.valueOf("PUT").getEvent());
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
