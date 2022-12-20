package by.issoft.consoleApp.utilities.consoleCommands;

import by.issoft.store.helper.ThreadHelper;
import lombok.SneakyThrows;

public class Order extends ConsoleCommandHandler {

    @SneakyThrows
    @Override
    public boolean isCommandHandled(String consoleInput) {
        if (consoleInput.equals(SupportedConsoleCommands.ORDER.toString())) {
            ThreadHelper threadHelper = new ThreadHelper();
            threadHelper.start();
            return true;
        }
        return handleNext(consoleInput);
    }
}
