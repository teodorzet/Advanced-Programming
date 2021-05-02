package Optional;

import Optional.Classes.Movie;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Chart {
    private String name;
    private LocalDateTime creationDate;
    private List<Movie> movieList;

    public Chart(String name, LocalDateTime creationDate, List<Movie> movieList) {
        this.name = name;
        this.creationDate = creationDate;
        this.movieList = movieList;

        Collections.sort(movieList, (movie1,movie2) -> {
            return (int) (movie2.getScore()*10 - movie1.getScore()*10);
        });
    }

    @Override
    public String toString() {
        return name + " created on " + creationDate.toLocalDate() + " has movies: \n" + movieList.toString();
    }

    public void printChart() {
        System.out.println(name + " created on " + creationDate.toLocalDate() + " has movies: \n");
        for (Movie movie : movieList)
            System.out.println(movie.getTitle() + ", with rating: " + movie.getScore());
    }
}
