package Compulsory;

public class School implements Comparable<School>{
    private String name;

    public School(String name) {
        this.name = name;
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
}
