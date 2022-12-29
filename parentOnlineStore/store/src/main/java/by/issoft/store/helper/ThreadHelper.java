package by.issoft.store.helper;

import by.issoft.domain.Product;
import by.issoft.store.Store;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.*;

import static by.issoft.store.utilities.StoreConstants.Threads.*;

@Slf4j
public class ThreadHelper extends Thread {
    private final ScheduledExecutorService cartCleanUpPeriodExecutor = Executors.newSingleThreadScheduledExecutor();
    private final ScheduledExecutorService orderThreadsExecutorService = Executors.newScheduledThreadPool(ORDER_THREADS_POOL_SIZE);

    public synchronized void orderProcessing(Product product) {
        log.info(START_PROCESSING_ORDER + this.getName());
        Runnable orderProcessingThread = () -> {
            Store.getInstance().addPurchasedGods(product);
            log.info(FINISH_PROCESSING_ORDER + product);
        };
        orderThreadsExecutorService.schedule(orderProcessingThread, getOrderLifeTime(), TimeUnit.SECONDS);
    }

    public void cleanUpCart() {
        Runnable cleanUpCartThread = Store.getInstance()::clearPurchasedGodsCollection;
        cartCleanUpPeriodExecutor.scheduleAtFixedRate(cleanUpCartThread, 0, CLEAN_UP_CART_INTERVAL, TimeUnit.MINUTES);
    }

    private int getOrderLifeTime() {
        return new Random().nextInt(MAX_ORDER_LIFE_TIME - MIN_ORDER_LIFE_TIME) + MIN_ORDER_LIFE_TIME;
    }
}
