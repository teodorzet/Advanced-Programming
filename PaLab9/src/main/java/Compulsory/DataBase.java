package Compulsory;

import Compulsory.Classes.Movie;
import Compulsory.Repositories.MovieRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class DataBase {
    static DataBase instance = null;
    static EntityManagerFactory factory = null;
    static EntityManager em = null;
    static public DataBase getInstance() throws SQLException {
        if (instance == null)
            instance = new DataBase();
        return instance;
    }

    private DataBase(){
        factory = Persistence.createEntityManagerFactory("MyApp");
        em = factory.createEntityManager();
    }

    static public void createMovie(String title, LocalDateTime releaseDate, int duration, int score){
        MovieRepository movieRepository = new MovieRepository(em);
        Movie movie = new Movie(title,releaseDate.toString(),duration,score,em);
        movieRepository.create(movie);
    }
    static public void findMovieById(long id){
        MovieRepository movieRepository = new MovieRepository(em);
        //movieRepository.findById(id);
        System.out.println(movieRepository.findById(id).getTitle());
    }
    static public void findMoviesByName(String name){
    MovieRepository movieRepository = new MovieRepository(em);

        for (Movie movie : movieRepository.findByName(name))
        System.out.println(movie.getTitle());
    }
}
