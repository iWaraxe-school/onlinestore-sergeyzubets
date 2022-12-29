package by.issoft.store.http.pages;

import by.issoft.domain.utilities.PresentProductsAsTable;
import by.issoft.store.Store;
import com.sun.net.httpserver.*;

import java.io.IOException;

import static by.issoft.store.http.server.ResponseHandler.handleResponse;

public class PurchasedGodsPage implements HttpHandler {

    public void handle(HttpExchange httpExchange) throws IOException {
        handleResponse(httpExchange,
                "All Purchased Gods:" + '\n'
                        + new PresentProductsAsTable().getProductsAsTable(Store.getInstance().getAllPurchasedGods()));
    }
}
