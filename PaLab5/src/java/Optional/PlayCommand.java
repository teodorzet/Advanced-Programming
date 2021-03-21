package Optional;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class PlayCommand extends Command {

    public PlayCommand(List<Item> items) {
        setItems(items);
    }

    @Override
    public void executeCommand() {
        if (Desktop.isDesktopSupported()) {
            try {
                String[] vectorOfArguments = this.arguments.split(" ");
                if (vectorOfArguments.length == 1) {
                    Desktop desktop = Desktop.getDesktop();
                    File file = new File(items.get(Integer.parseInt(vectorOfArguments[0])).getPath() + "/" + items.get(Integer.parseInt(vectorOfArguments[0])).getName());
                    desktop.open(file);
                    System.out.println("Fisierul a fost rulat.");
                } else {
                    System.out.println("Argumentele sunt gresite.");
                }
            } catch (IOException ex) {

            }
            catch (IndexOutOfBoundsException i){
                System.out.println("Nu exista un fisier pe pozitia aceasta.");
            }
        }
    }
}
