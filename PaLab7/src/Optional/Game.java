package Optional;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import static java.time.temporal.ChronoUnit.MINUTES;

public class Game implements Runnable{
    private static List<Player> listOfPlayers;
    protected static List<Token> listOfTokens;
    private Map<Player,Integer> scores;
    private int localScore=0;
    private int localScoreLength=0;
    private int durationOfGameInMinutes;


    public void setDurationOfGameInMinutes(int durationOfGameInMinutes) {
        this.durationOfGameInMinutes = durationOfGameInMinutes;
    }
    public void determineScore(){
        for(int i=0;i<listOfPlayers.size();i++){
            /*
             * Determine each player's score
             */
            Player player = listOfPlayers.get(i);
            int scoreOfPlayer=0;
            Token firstToken;
            localScore=0;
            localScoreLength=0;
            for(int j=0;j<player.myTokens.size();j++){

                firstToken = player.myTokens.get(j);
                player.myTokens.remove(firstToken);
                largestClosedSequence(player.myTokens,firstToken,firstToken.getLastNumber(),firstToken.getValue(),1);
                player.myTokens.add(firstToken);
            }
            System.out.println(player.name + " has score " + localScore + " and the length " + localScoreLength);
            player.score = localScore;
        }
    }
    public void largestClosedSequence(List<Token> listOfTokens,Token firstToken, int lastValue, int currentScore, int length){
        for(int i=0;i<listOfTokens.size();i++){
            if (listOfTokens.get(i).getFirstNumber() == lastValue){
                length++;
                currentScore+=listOfTokens.get(i).getValue();
                lastValue = listOfTokens.get(i).getLastNumber();

                if (listOfTokens.get(i).getLastNumber() == firstToken.getFirstNumber()){
                    /*
                     * Found a sequence
                     */
                    if (length > localScoreLength){
                        localScoreLength = length;
                        localScore = currentScore;
                    }
                    break;
                }

                Token tokenChosen = listOfTokens.get(i);
                listOfTokens.remove(tokenChosen);
                largestClosedSequence(listOfTokens,firstToken,lastValue,currentScore,length);
                listOfTokens.add(tokenChosen);

            } else {

            }
        }
    }
    public List<Player> getListOfPlayers() {
        return listOfPlayers;
    }
    public void winnerOfTheGame(){
        determineScore();
        int maxScore = 0;
        Player winner = null;
        for(int i=0;i<listOfPlayers.size();i++){
            if (listOfPlayers.get(i).score > maxScore){
                winner = listOfPlayers.get(i);
                maxScore = listOfPlayers.get(i).score;
            }
        }
        if (winner != null) {
            System.out.println("Winner of the game is " + winner.name + " with the score " + winner.score);
        } else {
            System.out.println("Nobody won the game.");
        }
    }
    public void setListOfPlayers(List<Player> listOfPlayers) {
        this.listOfPlayers = listOfPlayers;
    }
    public void setListOfTokens(List<Token> listOfTokens){
        this.listOfTokens = listOfTokens;
    }
    public synchronized void pickToken(Player player) throws IOException {
        int tokenIndex;

        if (player.isBot) {
            tokenIndex = (int) (Math.random() * listOfTokens.size() - 1);
        } else {
            for(int i=0;i<listOfTokens.size();i++) {
                System.out.print(i + ": [" + listOfTokens.get(i) + "] || ");
            }
            System.out.println();

            Read read = new Read();
            read.readNumber(listOfTokens.size());

            tokenIndex = read.numberToReturn;
        }
        Token tokenChosen = listOfTokens.get(tokenIndex);
        listOfTokens.remove(listOfTokens.get(tokenIndex));

        player.myTokens.add(tokenChosen);
        System.out.println(player.name + " chose token " + tokenChosen);
    }
    @Override
    public void run() {
        LocalTime startTime = LocalTime.now();
        LocalTime finalTime = LocalTime.now();

        for(int i=0;i<listOfPlayers.size();i++){
            listOfPlayers.get(i).startPlayer();
        }

        int turn = 0;
        while(listOfTokens.size() != 0 && startTime.until(finalTime, MINUTES) < durationOfGameInMinutes){
            finalTime = LocalTime.now();
            System.out.println("It's " + listOfPlayers.get(turn).name + "'s turn.");
            listOfPlayers.get(turn).myTurn = true;

            while(listOfPlayers.get(turn).myTurn){
                System.out.print("");
            }
            turn = (turn + 1) % listOfPlayers.size();
        }
        System.out.println();
        System.out.println("Game ended.");
        System.out.println();

        for(int i=0;i<listOfPlayers.size();i++) {
            System.out.println(listOfPlayers.get(i).toString());
        }

        winnerOfTheGame();
    }
}
