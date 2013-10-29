package ui;

import commands.Command;
import commands.CommandFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Application {
    private final static String PROMPT = "EchoClient> ";

    public static void main(String[] args) {
        CommandFactory factory = new CommandFactory();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = null;
        while (true) {
            System.out.print(PROMPT);
            try {
                line = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
            if (line == null) {
                // EOF
                return;
            }
            Command command = factory.createCommand(line);
            if (command == null) {
                System.out.println("Invalid command.");
                continue;
            } else {
                command.execute();
            }
        }
    }
}
