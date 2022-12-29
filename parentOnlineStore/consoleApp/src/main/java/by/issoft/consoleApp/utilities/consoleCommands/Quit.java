package by.issoft.consoleApp.utilities.consoleCommands;

import by.issoft.consoleApp.utilities.ConsoleApp;
import by.issoft.store.http.server.StoreServer;
import lombok.SneakyThrows;

public class Quit extends ConsoleCommandHandler {
    ConsoleApp consoleApp = new ConsoleApp();

    @SneakyThrows
    @Override
    public boolean isCommandHandled(String consoleInput) {
        if (consoleInput.equals(SupportedConsoleCommands.QUIT.toString())) {
            //System.out.println(GOODBYE_MESSAGE);        // was moved to HTTP Server
            consoleApp.setConsoleEnabled(false);
            StoreServer.getInstance().stop();
            return true;
        }
        return handleNext(consoleInput);
    }
}