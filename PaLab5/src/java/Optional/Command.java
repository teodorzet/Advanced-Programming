package Optional;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public abstract class Command extends Catalog{
    String arguments;

    public Command() {
    }

    public void executeCommand() throws IOException {
    }
    public String getArguments() {
        return arguments;
    }
    public void setArguments(String arguments) {
        if (this.arguments != null)
        this.arguments = this.arguments + " " + arguments;
        else this.arguments = arguments;
    }
}
