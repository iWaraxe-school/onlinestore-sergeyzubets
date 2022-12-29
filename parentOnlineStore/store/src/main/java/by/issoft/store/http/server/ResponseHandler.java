package by.issoft.store.http.server;

import com.sun.net.httpserver.HttpExchange;

import java.io.*;

import static by.issoft.store.utilities.StoreConstants.Http.HTTP_SUCCESS_STATUS_RESPONSE_CODE;

public class ResponseHandler {

    public static void handleResponse(HttpExchange httpExchange, String data) throws IOException {
        httpExchange.sendResponseHeaders(HTTP_SUCCESS_STATUS_RESPONSE_CODE, data.length());
        OutputStream outputStream = httpExchange.getResponseBody();
        outputStream.write(data.getBytes());
        outputStream.close();
    }
}
