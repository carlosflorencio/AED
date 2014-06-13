package problemaSerie3.commands;

import problemaSerie3.Centrality;
import problemaSerie3.commands.factory.ConsoleCommand;
import problemaSerie3.entities.Friend;
import problemaSerie3.structures.Digraph;

import java.util.Iterator;
import java.util.regex.Pattern;

public class ClosenessConsoleCommand extends ConsoleCommand {

    public ClosenessConsoleCommand() {
        super(Pattern.compile("c", Pattern.CASE_INSENSITIVE), "c - Closeness");
    }

    @Override
    public void run(String cmd) {

        System.out.println("vertice closeness");

        Iterator<Integer> itr = Centrality.getMap().iterator();
        Digraph<Friend> graph = Centrality.getGraph();

        while(itr.hasNext()) {
            int vertex = itr.next();

            System.out.println(graph.closeness(vertex));
        }

    }
}
