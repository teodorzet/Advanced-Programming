package Optional;

import org.junit.Test;

public class MyClass {
    private int number;
    private String name;


    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Test
    public static void method1() {
        throw new RuntimeException("method1 exception");
    }

    @Test
    public static void method2() {

    }

}
