package Optional.Service;

import Optional.Classes.Movie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MovieService {
    private int id;
    private String title;
    private LocalDateTime releaseDate;
    private double duration;
    private double score;
    private Connection con;

    public MovieService(String title, LocalDateTime releaseDate, double duration, double score, Connection con) {
        this.id = id;
        this.title = title;
        this.releaseDate = releaseDate;
        this.duration = duration;
        this.score = score;
        this.con = con;
    }

    public MovieService(Connection con) {
        this.con = con;
    }

    public void addMovie() throws SQLException {
        PreparedStatement stmt = con.prepareStatement("INSERT INTO MOVIES (id,title,release_date,duration,score)"
                + " VALUES ((SELECT COALESCE(MAX(id)+1,1) FROM MOVIES),?,?,?,?)");

        stmt.setString(1, title);
        stmt.setString(2, releaseDate.toString());
        stmt.setDouble(3, duration);
        stmt.setDouble(4, score);

        stmt.executeUpdate();
    }
    public void findMovie(int id) throws SQLException {
        PreparedStatement stmt = con.prepareStatement("SELECT * FROM MOVIES WHERE id=" + id);
        ResultSet resultSet = stmt.executeQuery();

        while (resultSet.next()) {
            this.id = resultSet.getInt("id");
            title = resultSet.getString("title");
            releaseDate = resultSet.getObject("release_date", LocalDateTime.class);
            duration = resultSet.getInt("duration");
            score = resultSet.getInt("score");
        }

    }
    public List<Movie> findMovie(String name) throws SQLException {
        PreparedStatement stmt = con.prepareStatement("SELECT * FROM MOVIES WHERE title LIKE " + "'%" + name + "%'");
        ResultSet resultSet = stmt.executeQuery();

        List<Movie> listOfMovies = new ArrayList<>();

        while (resultSet.next()) {
            this.id = resultSet.getInt("id");
            title = resultSet.getString("title");
            releaseDate = resultSet.getObject("release_date", LocalDateTime.class);
            duration = resultSet.getInt("duration");
            score = resultSet.getDouble("score");

            listOfMovies.add(new Movie(title,releaseDate.toString(),(int) duration,score,null));
        }

        return listOfMovies;
    }
    public int getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    @Override
    public String toString() {
        return id + " " + title + " " + releaseDate + " " + duration + " " + score;
    }
}
