package Bonus;

public class Warehouse extends Source {
    public Warehouse(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public int getCapacity() {
        return this.capacity;
    }
    @Override
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
