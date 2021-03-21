package Optional;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

public class AddCommand extends Command{
    public AddCommand(List<Item> items) {
        setItems(items);
    }

    @Override
    public void executeCommand() {
        Item item = null;
        String[] vectorOfArguments = this.arguments.split(" ");
        vectorOfArguments[1] = vectorOfArguments[1].substring(1,vectorOfArguments[1].length()-1);
        vectorOfArguments[2] = vectorOfArguments[2].substring(1,vectorOfArguments[2].length()-1);
        //System.out.println(vectorOfArguments[1] + " " + vectorOfArguments[2]);
        if (vectorOfArguments[0].equals("song")) {
            item = new Song(vectorOfArguments[1], vectorOfArguments[2]);
        } else if (vectorOfArguments[0].equals("text")) {
            item = new Text(vectorOfArguments[1], vectorOfArguments[2]);
        }
        if (vectorOfArguments.length > 3){
            System.out.println("Prea multe argumente.");
            return;
        } else if (vectorOfArguments.length < 2){
            System.out.println("Prea putine argumente.");
            return;
        }


        try{
            validate(item.path,item.name);
            System.out.println("Element adaugat cu succes.");
            this.items.add(item);
        } catch (Exception m) {
            System.out.println("Exception " + m + item.name);
        }
    }
    static void validate(String path, String name) throws InvalidDataException, IOException {
        File file = new File(path + "/" + name);
        if (!file.exists())
            throw new InvalidDataException("File not exists: ");
        else {
            BasicFileAttributes attributes = Files.readAttributes(Path.of(path), BasicFileAttributes.class);
            FileTime fileTime = attributes.creationTime();
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime converted = LocalDateTime.ofInstant(fileTime.toInstant(), ZoneId.systemDefault());
            if (now.isBefore(converted)) {
                throw new InvalidDataException("File have invalid date: ");
            }
        }
    }
}
