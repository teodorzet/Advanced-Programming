package Optional;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Player extends Game implements Runnable{
    private Thread t;
    protected String name;
    protected List<Token> myTokens;
    protected int index;
    protected int score;
    protected boolean myTurn;
    protected boolean isBot;

    public Player(String name, boolean isBot) {
        this.name = name;
        this.myTokens = new ArrayList<>();
        this.isBot = isBot;
        myTurn = false;
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
            if (myTurn) {
                try {
                    pickToken(this);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                myTurn = false;
            }
        }
    }
    public void startPlayer() {
        if (t == null) {
            t = new Thread (this);
            t.start();
        }
    }
}
