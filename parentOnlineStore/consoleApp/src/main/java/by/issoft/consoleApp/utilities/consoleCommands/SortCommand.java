package by.issoft.consoleApp.utilities.consoleCommands;

import by.issoft.store.Store;

public class SortCommand extends ConsoleCommandHandler {

    @Override
    public boolean isCommandHandled(String consoleInput) {
        if (consoleInput.equals(SupportedConsoleCommand.SORT.toString())) {
            sorting.printSortedProducts(Store.getInstance().getListOfAllProducts());
            return true;
        }
        return handleNext(consoleInput);
    }
}