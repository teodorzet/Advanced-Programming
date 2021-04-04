package Compulsory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Game{
    private List<Player> listOfPlayers;
    protected static List<Token> listOfTokens;

    public void setListOfPlayers(List<Player> listOfPlayers) {
        this.listOfPlayers = listOfPlayers;
    }
    public void setListOfTokens(List<Token> listOfTokens){
        this.listOfTokens = listOfTokens;
    }
    public void startGame(){
        for(int i=0;i<listOfPlayers.size();i++){
            listOfPlayers.get(i).startPlayer();
        }

        while(listOfTokens.size() != 0){

        }
        for(int i=0;i<listOfPlayers.size();i++) {
            System.out.println(listOfPlayers.get(i).toString());
        }
    }
    public synchronized void pickToken(Player player){

            int randomToken = (int) (Math.random() * listOfTokens.size() - 1);

            Token tokenChoosed = listOfTokens.get(randomToken);
            listOfTokens.remove(listOfTokens.get(randomToken));

            player.myTokens.add(tokenChoosed);
            System.out.println(player.name + " choosed token " + tokenChoosed);
    }
}
