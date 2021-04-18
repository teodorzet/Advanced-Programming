package Optional;

import java.sql.SQLException;
import java.time.LocalDateTime;

public class Main {

    public static void main(String args[]) throws Exception {
        DataBase db = DataBase.getInstance();

        db.findMovie(1);
        db.findGenre(2);
        //db.findActor("Ford");

        //db.addActor("Harrison","Ford",78);
        //db.addDirector("George","Lucas",76);
        //db.addMovie("Star Wars Episode I", LocalDateTime.of(1999,5,19,0,0),136,10);
        //db.addActorToMovie("Harrison","Ford","Star Wars Episode I");
        //db.addDirectorToMovie("George","Lucas","Star Wars Episode I");
        //db.addActor("Mark","Hamill",69);
        //db.addActorToMovie("Mark","Hamill","Star Wars Episode I");
        db.findMovieActors("Star Wars Episode I");
        db.findMovieDirectors("Star Wars Episode I");
    }
}
