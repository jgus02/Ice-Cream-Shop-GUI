public class PrintDeck {
    public static void main (String[] args)
    {
        Deck table = new Deck();
        table.shuffle();

        Card curCard;
        while(!table.isEmpty()) {
            curCard = table.deal();
            System.out.print(curCard + " ");
        }
        System.out.println();
    }
}