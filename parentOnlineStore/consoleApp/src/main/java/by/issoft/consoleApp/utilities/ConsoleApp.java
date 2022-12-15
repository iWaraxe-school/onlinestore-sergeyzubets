package by.issoft.consoleApp.utilities;

import by.issoft.consoleApp.utilities.consoleCommands.*;
import by.issoft.store.Store;

import java.util.Scanner;

import static by.issoft.store.utilities.StoreConstants.ConsoleApp.*;

public class ConsoleApp {

    public void start() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(WELCOME_MESSAGE);
        while (true) {
            System.out.println(AVAILABLE_COMMANDS);
            String consoleInput = scanner.nextLine().toUpperCase(Store.getInstance().getStoreLocale());
            ConsoleCommandHandler consoleCommandHandler = ConsoleCommandHandler.link(
                    new QuitCommand(),
                    new SortCommand(),
                    new TopCommand(),
                    new UnacceptableCommand());
            consoleCommandHandler.isCommandHandled(consoleInput);
        }
    }
}