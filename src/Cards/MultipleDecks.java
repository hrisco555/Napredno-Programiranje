package Cards;

public class MultipleDecks{

    private Deck[] decks;

    public MultipleDecks(int num_decks){
        decks = new Deck[num_decks];

        for (int i = 0; i < num_decks; i++) {
            decks[i] = new Deck(); // By creating a new Cards.Deck() in each iteration, you are effectively creating numberOfDecks separate and independent decks of playing cards.
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        for(Deck deck:decks){
            stringBuilder.append(deck.toString());
            stringBuilder.append(" ");
        }
        return stringBuilder.toString();
    }
}