package by.issoft.consoleApp;

import by.issoft.consoleApp.utilities.ConsoleApp;
import by.issoft.store.utilities.DBUtility;

public class StoreApp {

    public static void main(String[] args) {
        //store init
        DBUtility.initDatabase();

        //store populating
//        new StoreHelper().populateStoreViaFaker();

        //console
        new ConsoleApp().start();
    }
}