package problemaSerie3;

import problemaSerie3.commands.factory.ConsoleCommandFactory;

import java.io.IOException;
import java.util.Scanner;

public class Centrality {

    private static boolean running = true;
    private static Scanner input = new Scanner(System.in);
    private static String file;

    public static void main(String[] args) {
        file = args[0]; //get the first argumment, should be something like: C:\\example.ez
        if(args[0].length() == 0)
            throw new IllegalArgumentException();

        try {
            FileOperator.load(file);
        } catch (IOException e) {
            System.out.println("Error in IO operations: " + e.getMessage());
        }

        String line;

        System.out.println("===== Centrality =====");
        System.out.println("Welcome, type help if you need help.");

        while(running) {
            System.out.print("> ");
            line = input.nextLine();

            if(ConsoleCommandFactory.parse(line) == false)
                System.out.println("Invalid command, type help for more info.");
        }
    }

    public static boolean isRunning() {
        return running;
    }

    public static void stop() {
        running = false;
    }
}
