package by.issoft.store.http.server;

import by.issoft.store.Store;
import by.issoft.store.helper.ThreadHelper;
import by.issoft.store.http.pages.*;
import com.sun.net.httpserver.*;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;

import static by.issoft.store.utilities.StoreConstants.ConsoleApp.*;
import static by.issoft.store.utilities.StoreConstants.Http.*;

@Slf4j
public class StoreServer {
    private static StoreServer instance;

    private StoreServer() {
    }

    public static StoreServer getInstance() throws IOException {
        if (instance == null) {
            synchronized (StoreServer.class) {
                if (instance == null) {
                    instance = new StoreServer();
                }
            }
        }
        return instance;
    }

    public void start() throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(PORT), 0);
        Authenticator authenticator = new Authenticator("realm");
        server.createContext(MAIN_PAGE_URL, new MainPage()).setAuthenticator(authenticator);
        server.createContext(CATEGORIES_PAGE_URL, new CategoriesPage()).setAuthenticator(authenticator);
        server.createContext(CATALOG_PAGE_URL, new CatalogPage()).setAuthenticator(authenticator);
        server.createContext(PRODUCTS_PAGE_URL, new ProductsPage()).setAuthenticator(authenticator);
        server.createContext(TOP_GODS_PAGE_URL, new TopGodsPage()).setAuthenticator(authenticator);
        server.createContext(SORTED_GODS_PAGE_URL, new SortPage()).setAuthenticator(authenticator);
        server.createContext(CART_PAGE_URL, new PurchasedGodsPage()).setAuthenticator(authenticator);
        server.createContext(NEW_ORDER_PAGE_URL, new NewOrderPage());
        server.setExecutor(null);       // creates a default executor
        server.start();
        new ThreadHelper().cleanUpCart();
        log.info(START_SERVER_MESSAGE);
        System.out.println(Store.WELCOME_MESSAGE);
    }

    @SneakyThrows
    public void stop() {
        log.info(STOP_SERVER_MESSAGE);
        System.out.println(GOODBYE_MESSAGE);
        System.exit(0);
    }
}