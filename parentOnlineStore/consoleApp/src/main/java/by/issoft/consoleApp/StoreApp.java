package by.issoft.consoleApp;

import by.issoft.consoleApp.utilities.ConsoleApp;
import by.issoft.store.http.server.StoreServer;
import by.issoft.store.utilities.DatabaseUtility;

import java.io.IOException;

public class StoreApp {

    public static void main(String[] args) throws IOException {
        //to recreate DB if necessary
        //DatabaseUtility.dropAndRefillDatabase();

        //main
        DatabaseUtility.readFromDatabase();
        StoreServer.getInstance().start();
        new ConsoleApp().start();
    }
}