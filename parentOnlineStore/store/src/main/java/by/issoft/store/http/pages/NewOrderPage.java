package by.issoft.store.http.pages;

import by.issoft.store.Store;
import by.issoft.store.helper.ThreadHelper;
import com.sun.net.httpserver.*;
import lombok.SneakyThrows;

import static by.issoft.store.http.server.ResponseHandler.handleResponse;
import static by.issoft.store.utilities.StoreConstants.Http.*;

public class NewOrderPage implements HttpHandler {

    @SneakyThrows
    @Override
    public void handle(HttpExchange httpExchange) {
        String productName = httpExchange.getRequestURI().getQuery().substring(httpExchange.getRequestURI().getQuery().indexOf("=") + 1);
        String response;
        if (!Store.getInstance().getListOfAllProducts().isEmpty()) {
            response = "Product '" + productName + "' was added to the cart.";
            new ThreadHelper().orderProcessing(Store.getInstance().getProductByName(productName));
        } else {
            response = EMPTY_STORE;
        }
        handleResponse(httpExchange, response);
    }
}