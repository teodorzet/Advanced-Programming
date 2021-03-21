package Compulsory;

import java.io.Serializable;

public class Text extends Item implements Serializable {

    public Text(String name, String path) {
        this.name = name;
        this.path = path;
    }
}
