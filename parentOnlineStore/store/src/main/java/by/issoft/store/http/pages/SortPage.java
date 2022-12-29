package by.issoft.store.http.pages;

import by.issoft.store.helper.Sorting;
import com.sun.net.httpserver.*;

import java.io.IOException;

import static by.issoft.store.http.server.ResponseHandler.handleResponse;

public class SortPage implements HttpHandler {

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        handleResponse(httpExchange, new Sorting().getSortedProducts());
    }
}
