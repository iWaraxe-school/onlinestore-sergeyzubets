package by.issoft.consoleApp.utilities;

import by.issoft.store.Store;

import java.util.Scanner;

import static by.issoft.store.utilities.StoreConstants.ConsoleApp.*;

public class ConsoleApp {

    private static final String greetingMessage = GREETING_MESSAGE;
    private static final String availableCommands = AVAILABLE_COMMANDS;

    public void start() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(greetingMessage);
        while (true) {
            System.out.println(availableCommands);
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
