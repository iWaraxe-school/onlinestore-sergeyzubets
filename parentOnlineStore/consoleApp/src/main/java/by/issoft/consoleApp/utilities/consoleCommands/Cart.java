package by.issoft.consoleApp.utilities.consoleCommands;

import by.issoft.domain.utilities.PresentProductsAsTable;
import by.issoft.store.Store;

public class Cart extends ConsoleCommandHandler {

    @Override
    public boolean isCommandHandled(String consoleInput) {
        if (consoleInput.equals(SupportedConsoleCommands.CART.toString())) {
            System.out.println("All Purchased Gods:" + '\n'
                    + new PresentProductsAsTable().getProductsAsTable(Store.getInstance().getAllPurchasedGods()));
            return true;
        }
        return handleNext(consoleInput);
    }
}
