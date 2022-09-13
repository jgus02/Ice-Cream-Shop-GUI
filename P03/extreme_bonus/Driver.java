import java.util.Timer;
import java.util.TimerTask;
import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {
        /*Timer updateSpeed = new Timer();
        
        Deck userHand = new Deck();
        Deck cpuHand = new Deck();
        userHand.shuffle;
        cpuHand.shuffle;

        Player user = new Player(userHand);
        Player cpu = new Player(cpuHand);   //CPU is an 'acronym' with no real meaning used
                                            //to represent computer opponents in some games

        Player[2] players = {user,cpu}; //player 0 is user, player 1 is cpu. 'player 1' and 'player 2'

        timer.scheduleAtFixedRate(TimerTask spit.newFrame, 0, 500) {
            
        }*/

        Rank rank = new Rank(0);

        Card test = new Card(rank, Suit.values()[0]);
        System.out.print("" + test.display());
    }
}