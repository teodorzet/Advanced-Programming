package Optional;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

public class LoadCommand extends Command{

    public LoadCommand(List<Item> items) {
        setItems(items);
    }

    @Override
    public void executeCommand() {
        try {
            String[] vectorOfArguments = this.arguments.split(" ");
            if (vectorOfArguments.length == 1) {
                FileInputStream fileIn = new FileInputStream(vectorOfArguments[0]);
                ObjectInputStream in = new ObjectInputStream(fileIn);
                Catalog catalog = (Catalog) in.readObject();
                this.items = catalog.getItems();
                in.close();
                fileIn.close();
                System.out.println("Loadul a avut succes.");
            } else {
                System.out.println("Argumentele sunt gresite.");
            }
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c){
            System.out.println("Object not found");
            c.printStackTrace();
        }
    }
}
