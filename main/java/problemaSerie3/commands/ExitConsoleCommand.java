package problemaSerie3.commands;

import problemaSerie3.Centrality;
import problemaSerie3.commands.factory.ConsoleCommand;

import java.util.regex.Pattern;

public class ExitConsoleCommand extends ConsoleCommand {

    public ExitConsoleCommand() {
        super(Pattern.compile("e", Pattern.CASE_INSENSITIVE), "Exit the app");
    }

    @Override
    public void run(String cmd) {
        Centrality.stop();
    }
}
