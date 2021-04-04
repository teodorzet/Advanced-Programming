package Compulsory;

public class Token {
    private int firstNumber;
    private int lastNumber;
    private int value;

    public Token(int firstNumber, int lastNumber, int value) {
        this.firstNumber = firstNumber;
        this.lastNumber = lastNumber;
        this.value = value;
    }

    public int getFirstNumber() {
        return firstNumber;
    }
    public void setFirstNumber(int firstNumber) {
        this.firstNumber = firstNumber;
    }
    public int getLastNumber() {
        return lastNumber;
    }
    public void setLastNumber(int lastNumber) {
        this.lastNumber = lastNumber;
    }
    public int getValue() {
        return value;
    }
    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "(" + firstNumber + "," + lastNumber + ") - " + value;
    }
}
