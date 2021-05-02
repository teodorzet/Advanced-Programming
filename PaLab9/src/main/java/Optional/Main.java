package Optional;

import java.sql.SQLException;
import java.time.LocalDateTime;

public class Main {

    public static void main(String args[]) throws SQLException {
        DataBase db = DataBase.getInstance("JDBC");
        //db.createMovie("Star Wars Episode II", LocalDateTime.of(2002,5,16,0,0),142,10);
        //db.createMovie("Star Wars Episode III", LocalDateTime.of(2005,5,17,0,0),140,10);
        db.findMovieById(1000);
        //db.findMoviesByName("Star Wars");

        Chart starWarsChart = new Chart("Star Wars Series",LocalDateTime.now(),db.findMoviesByName("Star Wars"));
        starWarsChart.printChart();
    }
}
