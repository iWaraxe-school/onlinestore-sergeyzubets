package by.issoft.consoleApp;

import by.issoft.store.Store;
import by.issoft.store.helper.Sorting;
import by.issoft.store.helper.StoreHelper;

public class StoreApp {

    /* method, which creates instance of Store,
    fill created store with Products and prints all the categories and all the products.
    The main purpose of this module is to run the store. */
    public static void main(String[] args) {

        //store init
        Store store = new Store();
        StoreHelper storeHelper = new StoreHelper(store);
        Sorting sorting = new Sorting(store);

        //store populating
        storeHelper.populateStoreViaFaker();

        System.out.println(store);
        sorting.printTopProducts();
        sorting.printSortedProducts(store.getListOfAllProducts());

    }

}