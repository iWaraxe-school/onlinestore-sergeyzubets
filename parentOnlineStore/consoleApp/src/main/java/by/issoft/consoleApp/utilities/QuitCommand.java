package by.issoft.consoleApp.utilities;

import static by.issoft.store.utilities.StoreConstants.ConsoleApp.*;

public class QuitCommand extends ConsoleCommandHandler {

    private static final String goodbyeMessage = GOODBYE_MESSAGE;
    public static final int exitStatus = EXIT_STATUS;

    @Override
    public boolean isCommandHandled(String consoleInput) {
        if (consoleInput.equals(SupportedConsoleCommands.QUIT.toString())) {
            System.out.println(goodbyeMessage);
            System.exit(exitStatus);
            return true;
        }
        return handleNext(consoleInput);
    }

}
