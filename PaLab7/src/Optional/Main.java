package Optional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main{

    public static void main(String args[]) throws InterruptedException {
        int n = 3;
        Player player1 = new Player("Mihai",true);
        Player player2 = new Player("Alex",true);
        Player player3 = new Player("Vlad",true);
        Player player4 = new Player("Teodor",true);

        List<Token> listOfTokens = new ArrayList<>();

        for(int i=0;i<n;i++)
            for(int j=0;j<n;j++){
                if (i != j)
                listOfTokens.add(new Token(i,j,(int) (Math.random() * 10 ) ));
            }

        System.out.println(listOfTokens);

        Game game1 = new Game();
        game1.setDurationOfGameInMinutes(1);
        game1.setListOfPlayers(Arrays.asList(player1,player2,player3,player4));
        game1.setListOfTokens(listOfTokens);
        game1.run();
        //game1.winnerOfTheGame();
    }

}
