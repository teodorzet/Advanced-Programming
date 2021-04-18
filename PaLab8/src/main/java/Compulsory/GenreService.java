package Compulsory;

import java.sql.SQLException;

public interface GenreService {
    public void addGenre() throws SQLException;
    public void findGenre(int id) throws SQLException;
    public void findGenre(String name) throws SQLException;
}
