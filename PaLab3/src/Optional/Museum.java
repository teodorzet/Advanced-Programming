package Optional;

import javax.print.attribute.standard.Destination;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import static java.time.temporal.ChronoUnit.MINUTES;

public class Museum extends Location implements Payable, Visitable {
    private LocalTime openingTime, closingTime;
    private double ticketPrice;

    public Museum(String name, Map<Location, Integer> cost, int index) {
        super(name, cost, index);
    }


    @Override
    public LocalTime getOpeningTime() {
        return openingTime;
    }
    public void setOpeningTime(LocalTime openingTime){
        this.openingTime = openingTime;
    }
    @Override
    public LocalTime getClosingTime() {
        return closingTime;
    }
    public void setClosingTime(LocalTime closingTime){
        this.closingTime = closingTime;
    }
    @Override
    public double getTicketPrice() {
        return ticketPrice;
    }
    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }
    @Override
    public void timeSet() {
        setOpeningTime(LocalTime.of(9,30));
        setClosingTime(LocalTime.of(20,00));
    }
    @Override
    public Duration getVisitingDuration() {
        int durationInInt = (int) MINUTES.between(getOpeningTime(),getClosingTime());
        Duration duration = new Duration(LocalTime.of(durationInInt / 60,durationInInt % 60));
        return duration;
    }
}
