package Compulsory;

import java.beans.IntrospectionException;
import java.util.ArrayList;
import java.util.List;

public class Player extends Game implements Runnable{
    private Thread t;
    protected String name;
    protected List<Token> myTokens;
    protected int index;

    public Player(String name) {
        this.name = name;
        this.myTokens = new ArrayList<>();
    }

    public String getName() {
        return name;
    }
    public List<Token> getMyListOfTokens() {
        return myTokens;
    }
    @Override
    public String toString() {
        return "Player " + name + " has tokens: " + myTokens.toString();
    }

    public void run() {
        //System.out.println("Player " + this.name + " started playing");

        while (listOfTokens.size() > 0) {
            pickToken(this);
        }
    }
    public void startPlayer() {
        if (t == null) {
            t = new Thread (this);
            t.start();
        }
    }
}
