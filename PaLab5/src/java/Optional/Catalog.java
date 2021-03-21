package Optional;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Catalog implements Serializable {
    List<Item> items;

    public Catalog() {
        this.items = new ArrayList<>();
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
    public List<Item> getItems() {
        return this.items;
    }
    public void validate(String command) throws InvalidDataException, IOException {
        if (!command.equals("add") && !command.equals("list") && !command.equals("save")
                && !command.equals("load") && !command.equals("play") && !command.equals("report")
                && !command.equals("quit")) {
            throw new InvalidDataException("Command not valid.");
        }
    }
    public void readCommand() throws IOException {
        String command = null;
        Command commandObject;
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));

        while (command != "quit") {
            command = reader.readLine();
            String[] commandType = command.split(" ", 3);

            try {
                validate(commandType[0]);
                commandObject = null;
                if (commandType[0].equals("add")) {
                    commandObject = new AddCommand(this.items);
                } else if (commandType[0].equals("list")) {
                    commandObject = new ListCommand(this.items);
                } else if (commandType[0].equals("save")) {
                    commandObject = new SaveCommand(this.items);
                } else if (commandType[0].equals("load")) {
                    commandObject = new LoadCommand(this.items);
                } else if (commandType[0].equals("play")) {
                    commandObject = new PlayCommand(this.items);
                } else if (commandType[0].equals("report")) {
                    commandObject = new ReportCommand(this.items);
                } else if (commandType[0].equals("quit")) {
                    return;
                }

                commandType = Arrays.copyOfRange(commandType, 1, commandType.length);

                for (String word : commandType) {
                    commandObject.setArguments(word);
                }
                commandObject.executeCommand();

            } catch (Exception m) {
                System.out.println("Exception " + m);
            }


            }
        }

        public static void main (String args[]) throws IOException {
            String path = "C:/Users/Zetq/IdeaProjects/PaLab5/serializedobjects.ser";
            Catalog catalog1 = new Catalog();
            //Item item1 = new Song("rain-on-me.mp3", "C:/Users/Zetq/Downloads");
            //Item item2 = new Text("debug.log", "C:/Users/Zetq/Downloads");

            catalog1.readCommand();
        }
}
