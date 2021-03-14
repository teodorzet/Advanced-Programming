package Bonus;

import java.time.LocalTime;

public interface Visitable {
    LocalTime getOpeningTime();
    LocalTime getClosingTime();

    default void timeSet(){
    }
    Duration getVisitingDuration();
}
