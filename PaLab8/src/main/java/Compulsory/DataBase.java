package Compulsory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class DataBase {
    static Connection con = null;
    static DataBase instance = null;
    static String url = "jdbc:oracle:thin:@localhost:1521:XE";
    static public DataBase getInstance() throws SQLException {
        if (instance == null)
            instance = new DataBase();
        return instance;
    }

    DataBase(){
        try {
            con = DriverManager.getConnection(
                    url, "intelij", "intelij");
        } catch (SQLException e) {
            System.err.println("Cannot connect to DB: " + e);
        }
    }

    static public void addMovie(String title, LocalDateTime releaseDate, int duration, int score) throws Exception {
        MovieService movie = new Movie(title,releaseDate,duration,score,con);
        movie.addMovie();
    }
    static public void findMovie(int id) throws SQLException {
        MovieService movie = new Movie(con);
        movie.findMovie(id);
        System.out.println(movie.toString());
    }
    static public void findMovie(String name) throws SQLException {
        MovieService movie = new Movie(con);
        movie.findMovie(name);
        System.out.println(movie.toString());
    }
    static public void addGenre(String title) throws SQLException {
        GenreService genre = new Genre(title,con);
        genre.addGenre();
    }
    static public void findGenre(int id) throws SQLException {
        GenreService genre = new Genre(con);
        genre.findGenre(id);
        System.out.println(genre.toString());
    }
    static public void findGenre(String name) throws SQLException {
        GenreService genre = new Genre(con);
        genre.findGenre(name);
        System.out.println(genre.toString());
    }
}
