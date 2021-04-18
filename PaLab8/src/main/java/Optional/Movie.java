package Optional;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Movie implements MovieService {
    private int id;
    private String title;
    private LocalDateTime releaseDate;
    private int duration;
    private int score;
    private Connection con;

    public Movie(String title, LocalDateTime releaseDate, int duration, int score, Connection con) {
        this.id = id;
        this.title = title;
        this.releaseDate = releaseDate;
        this.duration = duration;
        this.score = score;
        this.con = con;
    }
    public Movie(Connection con){
        this.con = con;
    };

    @Override
    public void addMovie() throws SQLException {
        PreparedStatement stmt = con.prepareStatement("INSERT INTO MOVIES (id,title,release_date,duration,score)"
                + " VALUES ((SELECT COALESCE(MAX(id)+1,1) FROM MOVIES),?,?,?,?)");

        stmt.setString(1, title);
        stmt.setString(2, releaseDate.toString());
        stmt.setInt(3, duration);
        stmt.setInt(4, score);

        stmt.executeUpdate();
    }
    @Override
    public void findMovie(int id) throws SQLException {
        PreparedStatement stmt = con.prepareStatement("SELECT * FROM MOVIES WHERE id=" + id);
        ResultSet resultSet = stmt.executeQuery();

        while(resultSet.next()){
            this.id = resultSet.getInt("id");
            title = resultSet.getString("title");
            releaseDate = resultSet.getObject("release_date",LocalDateTime.class);
            duration = resultSet.getInt("duration");
            score = resultSet.getInt("score");
        }

    }
    @Override
    public void findMovie(String name) throws SQLException {
        PreparedStatement stmt = con.prepareStatement("SELECT * FROM MOVIES WHERE title=" + "'" + name + "'");
        ResultSet resultSet = stmt.executeQuery();

        while(resultSet.next()){
            this.id = resultSet.getInt("id");
            title = resultSet.getString("title");
            releaseDate = resultSet.getObject("release_date",LocalDateTime.class);
            duration = resultSet.getInt("duration");
            score = resultSet.getInt("score");
        }

    }
    @Override
    public int getId() {
        return id;
    }
    @Override
    public String getTitle() {
        return title;
    }
    @Override
    public List<Actor> findActors() throws SQLException {
        List<Actor> listOfActors = new ArrayList<>();
        Actor actor = null;
        int actorKey;

        PreparedStatement stmt = con.prepareStatement("SELECT * FROM MOVIEACTORS WHERE moviekey=" + id);
        ResultSet resultSet = stmt.executeQuery();

        while(resultSet.next()){
            actorKey = resultSet.getInt("actorkey");

            stmt = con.prepareStatement("SELECT * FROM ACTORS WHERE id=" + actorKey);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                actor = new Actor(rs.getInt("id"), rs.getString("firstname"),
                        rs.getString("lastname"), rs.getInt("age"), con);
            }

            listOfActors.add(actor);
        }


        return listOfActors;
    }
    @Override
    public List<Director> findDirectors() throws SQLException {
        List<Director> listOfDirectors = new ArrayList<>();
        Director director = null;
        int directorKey;

        PreparedStatement stmt = con.prepareStatement("SELECT * FROM MOVIEDIRECTORS WHERE moviekey=" + id);
        ResultSet resultSet = stmt.executeQuery();

        while(resultSet.next()){
            directorKey = resultSet.getInt("directorkey");

            stmt = con.prepareStatement("SELECT * FROM DIRECTORS WHERE id=" + directorKey);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                director = new Director(rs.getInt("id"), rs.getString("firstname"),
                        rs.getString("lastname"), rs.getInt("age"), con);
            }

            listOfDirectors.add(director);
        }


        return listOfDirectors;
    }
    @Override
    public String toString() {
        return id + " " + title + " " + releaseDate + " " + duration + " " + score;
    }
}
