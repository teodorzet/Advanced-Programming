package Compulsory;

import java.sql.SQLException;

public class Main {

    public static void main(String args[]) throws SQLException {
        DataBase db = DataBase.getInstance();

        //db.addMovie("Titanic", LocalDateTime.now(),10,10);
        //db.addGenre("Tragedy");
        db.findMovie(1);
        db.findGenre(3);

    }
}
