package Optional;

import java.io.Serializable;

public class Song extends Item implements Serializable {

    public Song(String name, String path) {
        this.name = name;
        this.path = path;
    }
}
