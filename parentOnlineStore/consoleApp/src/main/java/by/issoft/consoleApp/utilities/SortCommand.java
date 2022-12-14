package by.issoft.consoleApp.utilities;

import by.issoft.store.Store;
import by.issoft.store.helper.Sorting;

public class SortCommand extends ConsoleCommandHandler {

    private final Sorting sorting = new Sorting();

    @Override
    public boolean isCommandHandled(String consoleInput) {
        if (consoleInput.equals(SupportedConsoleCommands.SORT.toString())) {
            sorting.printSortedProducts(Store.getInstance().getListOfAllProducts());
            return true;
        }
        return handleNext(consoleInput);
    }

}
