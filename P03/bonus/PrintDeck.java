public class PrintDeck {
    public static void main (String[] args)
    {
        Deck table = new Deck();
        table.shuffle();

        Card curCard = table.deal();
        while(!table.isEmpty()) {
            System.out.print(curCard + " ");
            curCard = table.deal();
        }
        System.out.println();
    }
}