package by.issoft.consoleApp;

import by.issoft.consoleApp.utilities.ConsoleApp;
import by.issoft.store.Store;
import by.issoft.store.helper.StoreHelper;

public class StoreApp {

    /* method, which creates instance of Store,
    fill created store with Products and prints all the categories and all the products.
    The main purpose of this module is to run the store. */
    public static void main(String[] args) {

        //store init
        Store store = Store.getInstance();
        StoreHelper storeHelper = new StoreHelper();
        ConsoleApp consoleApp = new ConsoleApp();

        //store populating
        storeHelper.populateStoreViaFaker();

        //console
        consoleApp.start();

    }

}