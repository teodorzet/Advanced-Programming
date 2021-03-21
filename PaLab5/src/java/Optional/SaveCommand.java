package Optional;

import java.io.*;
import java.util.List;

public class SaveCommand extends Command{

    public SaveCommand(List<Item> items) {
        setItems(items);
    }

    @Override
    public void executeCommand(){
        try {
            String[] vectorOfArguments = this.arguments.split(" ");
            if (vectorOfArguments.length == 1) {
                System.out.println("-" + vectorOfArguments[0] + "-");
                FileOutputStream fileOut = new FileOutputStream(vectorOfArguments[0], false);
                ObjectOutputStream out = new ObjectOutputStream(fileOut);
                Catalog catalog = new Catalog();
                catalog.items = this.items;
                out.writeObject(catalog);
                out.close();
                fileOut.close();
                System.out.println("Salvarea a avut succes.");
            } else {
                System.out.println("Argumente incorecte.");
            }
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
}
