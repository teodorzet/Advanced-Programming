package Optional;

public class School implements Comparable<School>{
    private String name;
    private int capacity;
    private int index;

    public School(String name,int capacity, int index) {
        this.name = name;
        this.capacity = capacity;
        this.index = index;
    }

    public String getName() {
        return name;
    }
    public int getIndex() {
        return index;
    }
    @Override
    public int compareTo(School o) {
        if (o.name == null)
            return 0;
        return this.name.compareTo(o.name);
    }
    @Override
    public String toString() {
        return this.name + " " + this.capacity;
    }
    public int getCapacity() {
        return capacity;
    }
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
    public void setName(String name) {
        this.name = name;
    }
}