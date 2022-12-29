package by.issoft.store.http.pages;

import by.issoft.store.Store;
import com.sun.net.httpserver.*;

import java.io.IOException;

import static by.issoft.store.http.server.ResponseHandler.handleResponse;

public class CategoriesPage implements HttpHandler {

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        handleResponse(httpExchange, Store.getInstance().getAllCategories().toString());
    }
}
