package by.issoft.consoleApp.utilities.consoleCommands;

import static by.issoft.store.utilities.StoreConstants.ConsoleApp.*;

public class QuitCommand extends ConsoleCommandHandler {

    @Override
    public boolean isCommandHandled(String consoleInput) {
        if (consoleInput.equals(SupportedConsoleCommand.QUIT.toString())) {
            System.out.println(GOODBYE_MESSAGE);
            System.exit(EXIT_STATUS);
            return true;
        }
        return handleNext(consoleInput);
    }
}