public class Card {
    private Rank rank;
    private Suit suit;
    public static int rankVal;
    public static String suitVal;

    public Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;

        this.rankVal = rank.rank;
        this.suitVal = suit.c;
    }

    @Override
    public String toString() {
        return "" + rank + suit;
    }

    public String display() {
        return String.format("|%- 5s|", suitVal);
    }
}