package problemaSerie3;

import problemaSerie2.map.HashTable;
import problemaSerie3.entities.Friend;
import problemaSerie3.exceptions.InvalidFileFormatException;
import problemaSerie3.commands.factory.ConsoleCommandFactory;
import problemaSerie3.structures.Digraph;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Centrality {

    private static boolean running = true;
    private static Scanner input = new Scanner(System.in);
    private static Digraph<Friend> graph = null;
    private static HashTable<String, Integer> map = null;

    public static void main(String[] args) {
        try {
            String file = args[0];
            map = FileOperator.loadTable(file);
            graph = FileOperator.loadGraph(file, map);



            System.out.println("===== Centrality =====");
            System.out.println("Welcome, type help if you  needhelp.");

            String line;
            while(running) {
                System.out.print("> ");
                line = input.nextLine();

                if(!ConsoleCommandFactory.parse(line))
                    System.out.println("Invalid command, type help for more info.");
            }
        } catch (IOException e) {
            System.out.println("Error in IO operations: " + e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("File parameter needed.");
        } catch (InvalidFileFormatException e) {
            System.out.println("The file has some line invalid!");
        }
    }

    public static boolean isRunning() {
        return running;
    }

    public static void stop() {
        running = false;
    }

    public static Digraph<Friend> getGraph() {
        return graph;
    }

    public static HashTable<String, Integer> getMap() {
        return map;
    }
}
