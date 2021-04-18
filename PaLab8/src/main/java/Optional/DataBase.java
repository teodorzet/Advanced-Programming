package Optional;

import java.sql.Connection;
import java.sql.DriverManager;
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
    static public void findMovie(String title) throws SQLException {
        MovieService movie = new Movie(con);
        movie.findMovie(title);
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
    static public void addActor(String firstName,String lastName,int age) throws SQLException {
        ActorService actor = new Actor(firstName,lastName,age,con);
        actor.addActor();
    }
    static public void findActor(String firstName, String lastName) throws SQLException {
        ActorService actor = new Actor(con);
        actor.findActor(firstName, lastName);
        System.out.println(actor.toString());
    }
    static public void findActor(int id) throws SQLException {
        ActorService actor = new Actor(con);
        actor.findActor(id);
        System.out.println(actor.toString());
    }
    static public void addDirector(String firstName,String lastName,int age) throws SQLException {
        DirectorService director = new Director(firstName,lastName,age,con);
        director.addDirector();
    }
    static public void findDirector(String firstName, String lastName) throws SQLException {
        DirectorService director = new Director(con);
        director.findDirector(firstName, lastName);
        System.out.println(director.toString());
    }
    static public void findDirector(int id) throws SQLException {
        DirectorService director = new Director(con);
        director.findDirector(id);
        System.out.println(director.toString());
    }
    static public void addActorToMovie(String firstName, String lastName, String title) throws SQLException {
        ActorService actor = new Actor(con);
        actor.findActor(firstName,lastName);

        MovieService movie = new Movie(con);
        movie.findMovie(title);

        actor.addActorToMovie(movie.getId());
    }
    static public void addDirectorToMovie(String firstName, String lastName, String title) throws SQLException {
        DirectorService director = new Director(con);
        director.findDirector(firstName,lastName);

        MovieService movie = new Movie(con);
        movie.findMovie(title);

        director.addDirectorToMovie(movie.getId());
    }
    static public void findMovieActors(String title) throws SQLException{
        MovieService movie = new Movie(con);
        movie.findMovie(title);
        System.out.println("Movie " + movie.getTitle() + " has actors:");
        for (var actor : movie.findActors()){
            System.out.print(actor.getFullName() + ", ");
        }
        System.out.println();
    }
    static public void findMovieDirectors(String title) throws SQLException{
        MovieService movie = new Movie(con);
        movie.findMovie(title);
        System.out.println("Movie " + movie.getTitle() + " has directors:");
        for (var director : movie.findDirectors()){
            System.out.print(director.getFullName() + ", ");
        }
        System.out.println();
    }

}
