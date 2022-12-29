package by.issoft.consoleApp.utilities.consoleCommands;

import static by.issoft.store.utilities.DatabaseUtility.dropAndRefillDatabase;

public class Recreate extends ConsoleCommandHandler{

    public boolean isCommandHandled(String consoleInput) {
        if (consoleInput.equals(SupportedConsoleCommands.RECREATE.toString())) {
            dropAndRefillDatabase();
            return true;
        }
        return handleNext(consoleInput);
    }
}
