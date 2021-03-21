package Optional;

import java.util.List;

public class ListCommand extends Command {

    public ListCommand(List<Item> items) {
        setItems(items);
    }

    @Override
    public void executeCommand() {
        if (this.arguments != null){
            System.out.println("Comanda are prea multe argumente.");
            return;
        }
        System.out.println(this.items);
    }
}
