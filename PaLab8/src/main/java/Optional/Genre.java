package Optional;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Genre implements GenreService {
    private int id;
    private String name;
    private Connection con;

    public Genre(String name, Connection con) {
        this.name = name;
        this.con = con;
    }
    public Genre(Connection con){
        this.con = con;
    }

    @Override
    public void addGenre() throws SQLException {
        PreparedStatement stmt = con.prepareStatement("INSERT INTO GENRES (id,name)"
                + " VALUES ((SELECT COALESCE(MAX(id)+1,1) FROM GENRES),?)");

        stmt.setString(1, name);

        stmt.executeUpdate();
    }
    @Override
    public void findGenre(int id) throws SQLException {
        PreparedStatement stmt = con.prepareStatement("SELECT * FROM GENRES WHERE id=" + id);
        ResultSet resultSet = stmt.executeQuery();

        while(resultSet.next()){
            this.id = resultSet.getInt("id");
            name = resultSet.getString("name");
        }

    }
    @Override
    public void findGenre(String name) throws SQLException {
        PreparedStatement stmt = con.prepareStatement("SELECT * FROM GENRES WHERE name=" + name);
        ResultSet resultSet = stmt.executeQuery();

        while(resultSet.next()){
            this.id = resultSet.getInt("id");
            name = resultSet.getString("name");
        }

    }
    @Override
    public String toString() {
        return id + " " + name;
    }
}
