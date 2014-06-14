package problemaSerie3.commands;

import problemaSerie3.Centrality;
import problemaSerie3.commands.factory.ConsoleCommand;
import problemaSerie3.entities.Friend;
import problemaSerie3.structures.Graph;

import java.util.Iterator;
import java.util.regex.Pattern;

public class DegreeConsoleCommand extends ConsoleCommand {

    public DegreeConsoleCommand() {
        super(Pattern.compile("d", Pattern.CASE_INSENSITIVE), "d - Degree");
    }

    @Override
    public void run(String cmd) {

        System.out.println("vertice degree");


        Iterator<Integer> itr = Centrality.getMap().iterator();
        Graph<Friend> graph = Centrality.getGraph();


        while(itr.hasNext()) {
            int vertex = itr.next();

            System.out.println(graph.getVertexData(vertex) + " -> " + graph.getIndDeg(vertex));
        }

    }
}
