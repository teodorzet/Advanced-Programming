package Optional;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class Actor implements ActorService{
    private int id;
    private String firstName;
    private String lastName;
    private int age;
    private Connection con;

    public Actor(int id, String firstName, String lastName, int age, Connection con) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.con = con;
    }
    public Actor(String firstName, String lastName, int age, Connection con) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.con = con;
    }
    public Actor(Connection con){
        this.con = con;
    };

    @Override
    public void addActor() throws SQLException {
        PreparedStatement stmt = con.prepareStatement("INSERT INTO ACTORS (id,firstname,lastname,age)"
                + " VALUES ((SELECT COALESCE(MAX(id)+1,1) FROM ACTORS),?,?,?)");

        stmt.setString(1, firstName);
        stmt.setString(2, lastName);
        stmt.setInt(3, age);

        stmt.executeUpdate();
    }
    @Override
    public void findActor(String firstName, String lastName) throws SQLException {
        PreparedStatement stmt = con.prepareStatement("SELECT * FROM ACTORS WHERE lastname=" + "'" + lastName + "'" +
                        " AND firstName=" + "'" + firstName + "'"  );
        ResultSet resultSet = stmt.executeQuery();

        while(resultSet.next()){
            this.id = resultSet.getInt("id");
            this.firstName = resultSet.getString("firstname");
            this.lastName = resultSet.getString("lastname");
            age = resultSet.getInt("age");
        }

    }
    @Override
    public void findActor(int id) throws SQLException {
        PreparedStatement stmt = con.prepareStatement("SELECT * FROM ACTORS WHERE id=" + "'" + id + "'");
        ResultSet resultSet = stmt.executeQuery();

        while(resultSet.next()){
            this.id = resultSet.getInt("id");
            this.firstName = resultSet.getString("firstname");
            this.lastName = resultSet.getString("lastname");
            age = resultSet.getInt("age");
        }

    }
    @Override
    public void addActorToMovie(int movieKey) throws SQLException {
        PreparedStatement stmt = con.prepareStatement("INSERT INTO MOVIEACTORS (moviekey,actorkey)"
                + " VALUES (?,?)");
        stmt.setInt(1,movieKey);
        stmt.setInt(2,this.id);

        stmt.executeQuery();
    }
    @Override
    public String getFullName() {
        return firstName + " " + lastName;
    }

    @Override
    public String toString() {
        return id + " " + firstName + " " + lastName + " " + age;
    }
}
