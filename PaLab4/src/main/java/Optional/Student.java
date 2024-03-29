package Optional;

public class Student implements Comparable<Student> {
    private String name;
    private int index;
    private int score;

    public Student(String name, int score, int index) {
        this.name = name;
        this.score = score;
        this.index =  index;
    }
    public int getIndex() {
        return index;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }
    @Override
    public String toString() {
        return this.name + " " + this.score;
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
    public int getScore() {
        return score;
    }
}
