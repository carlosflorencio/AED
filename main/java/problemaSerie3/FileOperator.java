package problemaSerie3;

import problemaSerie2.map.HashTable;
import problemaSerie3.entities.Friend;
import problemaSerie3.exceptions.InvalidFileFormatException;
import problemaSerie3.structures.Digraph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileOperator {

    public static Digraph<Friend> loadGraph(String filename, HashTable<String, Integer> map) throws IOException, InvalidFileFormatException {
        BufferedReader reader = null;
        Digraph<Friend> graph = new Digraph<>(false); //not a directional graph

        try {
            String line;
            reader = new BufferedReader( new FileReader( filename ) );

            while((line = reader.readLine()) != null) {
                String[] numbers = line.split("\\s+");
                if(numbers.length != 2) throw new InvalidFileFormatException();

                int vertex = map.get(numbers[0]);
                int edge = map.get(numbers[1]);

                graph.insertVertex(new Friend(numbers[0]), vertex); //insert first the vertex if not exists
                graph.insertVertex(new Friend(numbers[1]), edge); //try to insert the vertex of that edge

                graph.insertEdge(vertex, edge, 1); //default cost = 1
            }
            return graph;
        } catch (IOException e) {
            throw e;
        } finally {
            //Close stream
            try {
                if(reader != null)
                    reader.close();
            } catch(IOException e) {
                //Failed to close? hum
                throw e;
            }

        }
    }

    public static HashTable<String, Integer> loadTable(String filename) throws InvalidFileFormatException, IOException {
        BufferedReader reader = null;
        HashTable<String, Integer> map = new HashTable<>(2000000, 2); //we cannot let the table rehash because something is wrong
        Integer res;
        int count = 1;
        try {
            String line;

            reader = new BufferedReader( new FileReader( filename ) );

            while((line = reader.readLine()) != null) {
                String[] numbers = line.split("\\s+");
                if(numbers.length != 2) throw new InvalidFileFormatException();

                for (int i = 0; i < numbers.length; i++) {
                    if(map.get(numbers[i]) == null) { //not registered yet? lets inserted it and update the counter
                       res = map.put(numbers[i], count++);
                    }
                }
            }
            return map;
        } catch (IOException e) {
            throw e;
        } finally {
            //Close stream
            try {
                if(reader != null)
                    reader.close();
            } catch(IOException e) {
                //Failed to close? hum
                throw e;
            }

        }
    }
}
