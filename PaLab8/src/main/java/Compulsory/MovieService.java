package Compulsory;

import java.sql.Connection;
import java.sql.SQLException;

public interface MovieService {

    public void addMovie() throws SQLException;
    public void findMovie(int id) throws SQLException;
    public void findMovie(String name) throws SQLException;
}
