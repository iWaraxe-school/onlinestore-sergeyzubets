package by.issoft.consoleApp.utilities.consoleCommands;

import by.issoft.store.Store;
import by.issoft.store.http.client.Client;
import lombok.SneakyThrows;

import java.util.Scanner;

import static by.issoft.store.utilities.StoreConstants.Http.EMPTY_STORE;
import static com.google.common.base.Preconditions.*;

public class Order extends ConsoleCommandHandler {

    @SneakyThrows
    @Override
    public boolean isCommandHandled(String consoleInput) {
        if (consoleInput.equals(SupportedConsoleCommands.ORDER.toString())) {
            checkArgument(Store.getInstance().getListOfAllProducts().size() > 0, EMPTY_STORE);
            System.out.println("Enter product name");
            new Client().purchaseProduct(new Scanner(System.in).nextLine());
            return true;
        }
        return handleNext(consoleInput);
    }
}
