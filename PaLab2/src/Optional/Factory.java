package Optional;

public class Factory extends Source{
    public Factory(int capacity) {
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
