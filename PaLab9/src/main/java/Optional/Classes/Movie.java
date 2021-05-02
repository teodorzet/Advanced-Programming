package Optional.Classes;

import javax.persistence.*;
import java.util.Comparator;

@NamedQuery(name="findByName", query = "SELECT m FROM Movie m WHERE m.title LIKE :substr")
@Entity
@Table(name = "MOVIES", schema = "INTELIJ")
public class Movie implements Comparator<Movie> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator ="init")
    @Column(name = "ID")
    private long id;
    @Basic
    @Column(name = "TITLE")
    private String title;
    @Basic
    @Column(name = "RELEASE_DATE")
    private String releaseDate;
    @Basic
    @Column(name = "DURATION")
    private long duration;
    @Basic
    @Column(name = "SCORE")
    private double score;

    public Movie(String title, String releaseDate, int duration, double score, EntityManager em) {
        this.title = title;
        this.releaseDate = releaseDate;
        this.duration = duration;
        this.score = score;
    }
    public Movie(){

    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getReleaseDate() {
        return releaseDate;
    }
    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }
    public long getDuration() {
        return duration;
    }
    public void setDuration(long duration) {
        this.duration = duration;
    }
    public double getScore() {
        return score;
    }
    public void setScore(long score) {
        this.score = score;
    }

    @Override
    public int compare(Movie o1, Movie o2) {
        if (o1.score > o2.score)
            return 1;
        if (o1.score == o2.score)
            return 0;
        else
            return -1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Movie that = (Movie) o;

        if (id != that.id) return false;
        if (duration != that.duration) return false;
        if (score != that.score) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (releaseDate != null ? !releaseDate.equals(that.releaseDate) : that.releaseDate != null) return false;

        return true;
    }
}
