package ru.focusstart.pakage1;

import ru.focusstart.log.FormatOutput;

import java.util.concurrent.BlockingQueue;
import java.util.logging.*;

public class Consumer implements Runnable {
    private static final Logger LOGGER = Logger.getLogger(Consumer.class.getName());
    private BlockingQueue<Product> warehouse;

    public Consumer(BlockingQueue<Product> warehouse) {
        this.warehouse = warehouse;
    }

    @Override
    public void run() {
        try {
            Handler consoleHandler = new ConsoleHandler();
            consoleHandler.setFormatter(new FormatOutput());
            LOGGER.setUseParentHandlers(false);
            LOGGER.addHandler(consoleHandler);

            Product product = warehouse.take();
            LOGGER.info("Потребитель " + product + " " + Events.valueOf("TAKEN").getEvent());
            Thread.sleep(5000);
            LOGGER.info("Потребитель " + product +  " " + Events.valueOf("CONSUMED").getEvent());
            product = null;
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
