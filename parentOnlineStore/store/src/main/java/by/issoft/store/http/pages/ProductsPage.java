package by.issoft.store.http.pages;

import by.issoft.domain.utilities.PresentProductsAsTable;
import by.issoft.store.helper.DatabaseHelper;
import com.sun.net.httpserver.*;

import java.io.IOException;

import static by.issoft.store.http.server.ResponseHandler.handleResponse;

public class ProductsPage implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        handleResponse(httpExchange,
                new PresentProductsAsTable().getProductsAsTable(
                        new DatabaseHelper().getAllProductsFromDatabase()));
    }
}
