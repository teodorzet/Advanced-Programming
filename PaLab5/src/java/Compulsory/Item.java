package Compulsory;

import java.io.Serializable;

public abstract class Item implements Serializable {
    String name;
    String path;

    public String getName() {
        return name;
    }
    public String getPath() {
        return path;
    }

    @Override
    public String toString() {
        return path + " " + name;
    }
}
