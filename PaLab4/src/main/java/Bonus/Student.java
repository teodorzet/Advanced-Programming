package Bonus;

public class Student implements Comparable<Student> {
    private String name;

    public Student(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }
    @Override
    public String toString() {
        return this.name;
    }
    @Override
    public int compareTo(Student o) {
        if (Integer.parseInt(this.name.substring(1)) == Integer.parseInt(o.name.substring(1)))
            return 0;
        else
        if (Integer.parseInt(this.name.substring(1)) > Integer.parseInt(o.name.substring(1)))
            return -1;
        return 1;
    }
}
