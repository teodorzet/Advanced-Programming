package Bonus;

public class School implements Comparable<School>{
    private String name;
    private int capacity;

    public School(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
    }

    public String getName() {
        return name;
    }
    @Override
    public int compareTo(School o) {
        if (o.name == null)
            return 0;
        return this.name.compareTo(o.name);
    }
    @Override
    public String toString() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getCapacity() {
        return capacity;
    }
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}