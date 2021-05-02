package Optional;

import Optional.Classes.Movie;
import Optional.Repositories.AbstractRepository;
import Optional.Service.MovieService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class AbstractFactory {
    private Connection con;
    private EntityManager em;

    public AbstractFactory(Connection con, EntityManager em) {
        this.con = con;
        this.em = em;
    }

    public void createMovie(String title, LocalDateTime releaseDate, int duration, int score) throws SQLException {
        if (con == null) {
            AbstractRepository movieRepository = new AbstractRepository(em, Movie.class);
            Movie movie = new Movie(title, releaseDate.toString(), duration, score, em);
            movieRepository.create(movie);
        } else {
            MovieService movie = new MovieService(title, releaseDate, duration, score, con);
            movie.addMovie();
        }
    }

    public void findMovieById(long id) throws SQLException {
        if (con == null) {
            AbstractRepository movieRepository = new AbstractRepository(em, Movie.class);
            //movieRepository.findById(id);
            Movie movie = (Movie) movieRepository.findById(id);
            System.out.println(movie.getTitle());
        } else {
            MovieService movie = new MovieService(con);
            movie.findMovie((int) id);
            System.out.println(movie.getTitle());
        }
    }

    public List<Movie> findMovieByName(String name) throws SQLException {
        if (con == null) {
            AbstractRepository movieRepository = new AbstractRepository(em, Movie.class);
        /*
        for (Movie movie : movieRepository.findByName(name))
            System.out.println(movie.getTitle());
         */
            return movieRepository.findByName(name);
        } else {
            MovieService movie = new MovieService(con);
            return movie.findMovie(name);
        }
    }


}
