package by.issoft.store.helper;

import by.issoft.store.Store;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.*;

import static by.issoft.store.utilities.StoreConstants.ConsoleApp.*;

@Slf4j
public class ThreadHelper extends Thread {

    private boolean isThreadAlive = true;
    private final ScheduledExecutorService periodExecutor = Executors.newSingleThreadScheduledExecutor();

    @Override
    public void run() {
        while (isThreadAlive) {
            orderProcessing(getOrderLifeTime());
        }
    }

    @SneakyThrows
    private synchronized void orderProcessing(int orderLifeTime) {
        log.info("Start processing order " + this.getName() + '\n' + BACK_TO_THE_MAIN_MENU);
        TimeUnit.SECONDS.sleep(orderLifeTime);
        Store.getInstance().addPurchasedGods(Store.getInstance().getRandomProductFromStore());
        isThreadAlive = false;
        this.notifyAll();
        log.info("Stop processing order " + this.getName() + '\n' + BACK_TO_THE_MAIN_MENU);
    }

    public void cleanUpCart() {
        Runnable cleanUpCartThread = Store.getInstance()::clearPurchasedGodsCollection;
        periodExecutor.scheduleAtFixedRate(cleanUpCartThread, 0, CLEAN_UP_CART_INTERVAL, TimeUnit.MINUTES);
    }

    public void turnOffCleanUpCartThread() {
        periodExecutor.shutdown();
    }

    private int getOrderLifeTime() {
        return new Random().nextInt(MAX_ORDER_LIFE_TIME - MIN_ORDER_LIFE_TIME) + MIN_ORDER_LIFE_TIME;
    }

}
