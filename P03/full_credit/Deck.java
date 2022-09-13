import java.util.Stack;
import java.util.Collections;

public class Deck {
    private Stack<Card> deck = new Stack<>();

    public Deck() {
        Card newCard;
        Rank newRank;
        for(int i=0;i<3;i++) {
            for(int j=(Rank.MIN);j<(Rank.MAX + 1);j++) {
                newRank = new Rank(j);
                newCard = new Card(newRank,Suit.values()[i]);
                this.deck.push(newCard);
            }
        }
    }

    public void shuffle() {
        Collections.shuffle(this.deck);
    }

    public boolean isEmpty() {
        return this.deck.empty();
    }

    public Card deal() {
        try {
            if (this.isEmpty()) {
                throw new IndexOutOfBoundsException();
            }
            return this.deck.pop();
        }
        catch (IndexOutOfBoundsException e) {
            throw new DeckEmptyException();
        }
    }

    public class DeckEmptyException extends IndexOutOfBoundsException {}

}
