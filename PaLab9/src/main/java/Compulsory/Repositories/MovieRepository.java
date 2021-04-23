package Compulsory.Repositories;

import Compulsory.Classes.Movie;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class MovieRepository {
    private EntityManager em = null;

    public MovieRepository(EntityManager em) {
        this.em = em;
    }

    public void create(Movie movie){
        em.getTransaction().begin();
        em.persist(movie);
        em.getTransaction().commit();
    }
    public Movie findById(long id){
        Movie movie = (Movie) em.find(Movie.class, id);
        return movie;
    }
    public List<Movie> findByName(String name){
        List<Movie> listOfMovies = new ArrayList<>();
        Query query = em.createNamedQuery("findByName");
        query.setParameter("substr","%" + name + "%");

        listOfMovies = query.getResultList();

        return listOfMovies;
    }
}
