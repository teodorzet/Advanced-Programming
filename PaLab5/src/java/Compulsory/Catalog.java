package Compulsory;

import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class Catalog implements Serializable{
    List<Item> items;

    public Catalog() {
        this.items = new ArrayList<>();
    }

    public List<Item> getItems() {
        return items;
    }
    public void add(Item item){
        try{
            validate(item.path,item.name);
            this.items.add(item);
        } catch (Exception m) {
            System.out.println("Exception " + m + item.name);
        }

    }
    public void list(){
        System.out.println(items.toString());
    }
    public void play(int index) {
        if (Desktop.isDesktopSupported()) {
            try {
                Desktop desktop = Desktop.getDesktop();
                File file = new File(items.get(index).getPath() + "/" + items.get(index).getName());
                desktop.open(file);
            } catch (IOException ex) {}
        }
    }
    public void save(String path){
        try {
                FileOutputStream fileOut = new FileOutputStream(path);
                ObjectOutputStream out = new ObjectOutputStream(fileOut);
                Catalog catalog = new Catalog();
                catalog.items = this.items;
                out.writeObject(catalog);
                out.close();
                fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
    public void load(String path){
        try {
            FileInputStream fileIn = new FileInputStream(path);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            Catalog catalog = (Catalog) in.readObject();
            this.items = catalog.getItems();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c){
            System.out.println("Object not found");
            c.printStackTrace();
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

        public static void main(String args[]){
        String path = "C:/Users/Zetq/IdeaProjects/PaLab5/serializedobjects.ser";
        Catalog catalog1 = new Catalog();
        Item item1 = new Song("rain-on-me.mp3", "C:/Users/Zetq/Downloads");
        Item item2 = new Text("debug.log", "C:/Users/Zetq/Downloads");

        catalog1.add(item1);
        catalog1.add(item2);

        catalog1.play(0);
        catalog1.save(path);
        catalog1.list();

        Catalog catalog2 = new Catalog();
        catalog2.load(path);
        catalog2.list();
        //catalog2.play(0);
    }
}
