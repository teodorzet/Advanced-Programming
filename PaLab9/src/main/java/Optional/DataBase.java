package Optional;

import Optional.Classes.Movie;
import Optional.Repositories.AbstractRepository;
import Optional.Repositories.MovieRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class DataBase {
    static DataBase instance = null;
    static Connection con = null;
    static EntityManagerFactory factory = null;
    static EntityManager em = null;
    static String url = "jdbc:oracle:thin:@localhost:1521:XE";
    static public DataBase getInstance(String connectionType) throws SQLException {
        if (instance == null)
            instance = new DataBase(connectionType);
        return instance;
    }

    private DataBase(String connectionType){
        if (connectionType.equals("JPA")) {
            factory = Persistence.createEntityManagerFactory("MyApp");
            em = factory.createEntityManager();
        }
        else if (connectionType.equals("JDBC")){
            try {
                con = DriverManager.getConnection(
                        url, "intelij", "intelij");
            } catch (SQLException e) {
                System.err.println("Cannot connect to DB: " + e);
            }
        } else {
            System.out.println("Connection type invalid.");
            return;
        }
    }

    static public void createMovie(String title, LocalDateTime releaseDate, int duration, int score) throws SQLException {
        AbstractFactory task = new AbstractFactory(con,em);
        task.createMovie(title,releaseDate,duration,score);
    }
    static public void findMovieById(long id) throws SQLException {
        AbstractFactory task = new AbstractFactory(con,em);
        task.findMovieById(id);
    }

    static public List<Movie> findMoviesByName(String name) throws SQLException {
        AbstractFactory task = new AbstractFactory(con,em);
        return task.findMovieByName(name);
    }

}
