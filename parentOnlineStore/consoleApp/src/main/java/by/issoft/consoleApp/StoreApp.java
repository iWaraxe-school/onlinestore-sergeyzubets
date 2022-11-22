package by.issoft.consoleApp;

import by.issoft.store.Store;

public class StoreApp {

    /* method, which creates instance of Store,
    fill created store with Products and prints all the categories and all the products.
    The main purpose of this module is to run the store. */
    public static void main(String[] args) {
        Store store = new Store();
        System.out.println("Store" + store);
    }
}