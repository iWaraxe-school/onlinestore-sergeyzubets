package by.issoft.store.http.client;

import by.issoft.domain.Product;
import by.issoft.store.Store;
import com.google.gson.Gson;

import static by.issoft.store.utilities.StoreConstants.Http.*;
import static io.restassured.RestAssured.given;

public class Client {
    private static final Gson gson = new Gson();
    private static final String USERNAME = "user";
    private static final String PASSWORD = "user";

    public void purchaseProduct(String productName) {
        String request = "?name=" + productName;
        Product purchasedProduct = Store.getInstance().getProductByName(productName);
        String json = gson.toJson(purchasedProduct);
        given().auth().basic(USERNAME, PASSWORD)
                .header("Content-type", "application/json")
                .and()
                .body(json)
                .when()
                .post(HOST + ":" + PORT + NEW_ORDER_PAGE_URL + request);
    }
}
