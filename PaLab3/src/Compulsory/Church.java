package Compulsory;

import java.time.LocalTime;
import java.util.Map;

public class Church extends Location implements Visitable,Classifiable{
    private LocalTime openingTime, closingTime;
    private int rank;

    public Church(String name, Map<Location, Integer> cost) {
        super(name, cost);
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
    public int getRank() {
        return this.rank;
    }
    public void setRank(int rank) {
        this.rank = rank;
    }
}

