package by.issoft.consoleApp.utilities.consoleCommands;

import by.issoft.store.Store;

public class PrintAllGods extends ConsoleCommandHandler {

    @Override
    public boolean isCommandHandled(String consoleInput) {
        if (consoleInput.equals(SupportedConsoleCommands.CATALOG.toString())) {
            System.out.println(Store.getInstance());
            return true;
        }
        return handleNext(consoleInput);
    }
}
