package Optional;

import java.sql.SQLException;
import java.util.List;

public interface MovieService {

    public void addMovie() throws SQLException;
    public void findMovie(int id) throws SQLException;
    public void findMovie(String name) throws SQLException;
    public int getId();
    public String getTitle();
    public List<Actor> findActors() throws SQLException;
    public List<Director> findDirectors() throws SQLException;
}
