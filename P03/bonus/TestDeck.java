public class TestDeck {
    public static void main (String[] args) {
        Deck deck = new Deck();
        char suitExpected[] = {'U','T','A'};
        int count = 0;
        Card card;

        //TEST 1. Card Verification
        for(int i = 2; i >= 0; i--) {
            for(int j = 9; j >= 0;j--) {
                card = deck.deal();
                if(!card.toString().equals("" + j + suitExpected[i])) {
                    if(count==0) System.err.print("FAIL: Missing card(s):");
                    System.err.print(" " + j + suitExpected[i]);
                    count++;
                }
            }
        }
        if(count>0) System.err.println(".");

        //TEST 2. Shuffle Verification
        deck = new Deck();
        Deck deckTwo = new Deck();
        Card cardTwo;
        deck.shuffle();
        deckTwo.shuffle();
        
        count = 0;
        do {
            card = deck.deal();
            cardTwo = deckTwo.deal();
            count++;
        } while(card.toString().equals(cardTwo.toString()) && count<(3*10));

        if(count == 3*10){
            System.err.println("FAIL: Decks failed to shuffle correctly, " + count + " matching deals.");
        }

        deckTwo = null;

        //TEST 3. isEmpty Verification
        deck = new Deck();

        int i = 0;

        for(i=0;i<29;i++){
            if(deck.isEmpty()) {
                System.err.println("FAIL: From deals 0-29, deal " + i + " returns empty.");
                i=30;
            }
            deck.deal();
        }

        deck.deal();
        i+=1;
        if(!deck.isEmpty()){
            System.err.println("FAIL: Deck not empty on deal " + i + ".");
        }
        try { 
            deck.deal();
            System.err.println("FAIL: Deck not empty on deal " + (i+1) + ".");
        }
        catch (Deck.DeckEmptyException e) {}
    }
}