package Optional;

import java.sql.SQLException;

public interface DirectorService {

    public void addDirector() throws SQLException;
    public void findDirector(String firstName, String lastname) throws SQLException;
    public void findDirector(int id) throws SQLException;
    public void addDirectorToMovie(int movieKey) throws SQLException;
    public String getFullName();
}
