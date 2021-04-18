package Optional;

import java.sql.SQLException;

public interface ActorService {

    public void addActor() throws SQLException;
    public void findActor(String firstName, String lastname) throws SQLException;
    public void findActor(int id) throws SQLException;
    public void addActorToMovie(int movieKey) throws SQLException;
    public String getFullName();
}
