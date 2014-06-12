package problemaSerie3.commands;

import problemaSerie3.commands.factory.ConsoleCommand;
import problemaSerie3.commands.factory.ConsoleCommandFactory;

import java.util.regex.Pattern;

public class HelpConsoleCommand extends ConsoleCommand {

    public HelpConsoleCommand() {
        super(Pattern.compile("help", Pattern.CASE_INSENSITIVE), "help - lists commands");
    }

    @Override
    public void run(String cmd) {
        ConsoleCommand[] cmds = ConsoleCommandFactory.getCommands();

        System.out.println("Commands:");
        for(int i = 0; i < cmds.length; ++i) {
            System.out.println("\t" + cmds[i].getDescription());
        }
    }
}
