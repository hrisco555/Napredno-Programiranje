package Cards;

import java.util.*;

//public class Cards.Deck {
//
//    private Cards.PlayingCard[] cards;
//    private boolean[] isDealt; //podelena, nepodelena
//    private int numDealt;
//
//    //funkciolnost za karta, za spil i poveke spilovi
//
//    public Cards.Deck(){
//        cards = new Cards.PlayingCard[52];
//        isDealt = new  boolean[52]; //primitiven
//        numDealt = 52;
//
//        for (int i = 0; i < Cards.PlayingCardType.values().length ; i++) { //GI VRTI SITE TIPOVI - ENUM - dolzinata na cel enum - nizata
//            for (int j = 1; j < 13; j++) { // odi do broevite
//                cards[i * 13 + j] = new Cards.PlayingCard(j, Cards.PlayingCardType.values()[i]);
//            }
//        }
//    }
//
//    public boolean hasCardsLeft(){
//        return numDealt > 0;
//    }
//
//    public Cards.PlayingCard[] shuffle(){ //mesa karti
//        List<Cards.PlayingCard> cardsList = Arrays.asList(cards); //ke gi pikne kartite vo lista
//        Collections.shuffle(cardsList);
//        return cards;
//    }
//
//    public Cards.PlayingCard dealCards() {//da podeli karti
//
//        int card = (int) Math.random() * 52; //kastira vo int zoso ke zeme 1 i ke mnozi so 52
//
//        if(!isDealt[card]){
//            isDealt[card] = true;
//            numDealt --;
//
//            return cards[card];
//        }
//
//        return dealCards();
//    }
//
//    @Override
//    public String toString() {
//        StringBuilder stringBuilder = new StringBuilder();
//
//        for (Cards.PlayingCard card: cards) { //za sekoja karta dodadi gi vrednostite
//            stringBuilder.append(card.toString());
//            stringBuilder.append("\n");
//
//        }
//        return stringBuilder.toString();
//    }
//
//    public static void main(String[] args) {
//        Cards.Deck deck1 = new Cards.Deck();
//        System.out.println(deck1);
//    }
//}

public class Deck {

    private PlayingCard[] cards;
    private boolean[] isDealt;  //podelena, nepodelena
    // This is an array of boolean values used to keep track of whether a card has been dealt from the deck or not. The isDealt array has the same length as the cards array, and each element corresponds to a card in the deck.  When a card is dealt, the corresponding element in this array will be set to true, indicating that the card is no longer available for dealing.
    private int dealtTotal;
    //This integer variable is used to keep track of the total number of cards that have been dealt from the deck.  It's updated each time a card is dealt. When dealtTotal reaches the total number of cards in the deck (e.g., 52 in a standard deck), it means that all cards have been dealt, and no more cards can be drawn from the deck.

    public Deck(){
        cards = new PlayingCard[52]; //array so dolzina od 52 karti

        isDealt = new boolean[52];  //The isDealt array will keep track of whether each card in the cards array has been dealt or not.
        Arrays.fill(isDealt, false); // Initialize the 'isDealt' array with all 'false' values.

        dealtTotal = 0; //Initializing dealtTotal to 0 in the constructor is important for ensuring that the deck starts in a consistent state.  When you create a new deck, it should have no cards dealt initially

        int index = 0; //int index = 0;: This line initializes an index variable named index to keep track of the current position in the cards array. It starts at 0.
        for (PlayingCardType type: PlayingCardType.values()) { // It iterates through each Cards.PlayingCardType enum value, representing the card suits (e.g., Hearts, Diamonds, Spades, Clubs).
            for (int rank = 1; rank <= 13; rank++) {  //This is the inner loop. It iterates through the possible card ranks, ranging from 1 to 13, which corresponds to the numeric ranks (2 to 10) and the face cards (Jack, Queen, King, Ace).
                cards[index] = new PlayingCard(rank,type); //gi zima rank i type of for loopsot
                //Inside the nested loops, a Cards.PlayingCard object is created with the current rank and type values. The rank and type values are used to create a new card object and assign it to the current position in the cards array.
                index++; //After each card is created and assigned to the array, the index is incremented to prepare for the next card.
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(" "); //StringBuilder = new StringBuilder();: This line creates an instance of the StringBuilder class. StringBuilder is used to efficiently build strings, especially when you need to concatenate multiple strings together. It's more efficient than using simple string concatenation with the + operator because it avoids creating unnecessary string objects.

        // The loop iterates through each card in the cards array and creates a multi-line string where each line represents a card in the deck.
        for(PlayingCard playingCard : cards){ //za sekoj eden playingcard, od nizata cards. for (Cards.PlayingCard card: cards) {: This is a for-each loop that iterates through an array of Cards.PlayingCard objects. It seems to be iterating through the array of cards in the deck.
            stringBuilder.append(playingCard.toString()); // the toString() method of the card is called to obtain a string representation of that card, and then it is appended to the StringBuilder. This is where the text representation of each card is added to the overall string.
            stringBuilder.append("\n");
        }
        return stringBuilder.toString(); //Finally, the toString() method of StringBuilder is called to convert the accumulated text into a single string, and this string is returned as the result of the toString() method for the entire class.
    }

    //проверка дали има останато карти.
    public boolean hasCardsLeft(){
        return dealtTotal > 0; //ako e pogolemo od 0 imame karti ostanati, ako e pomalo , nemame karti ostanati
    }

    public PlayingCard[] shuffle(){ //metod koj ke gi izmesa kartite

        //ke gi pikne kartite vo lista
        List<PlayingCard> playingCards = Arrays.asList(cards); //lista od Cards.PlayingCard. asList - zemi ja nizata cards i napravi ja kako lista
        Collections.shuffle(playingCards);
        return cards;
    }

    public PlayingCard dealCard(){ //delenje karti
        if(!hasCardsLeft()){ //ako nemame karti za delenje vrati null
            return null;
        }

        int card = (int) (Math.random() * 52); //random brojka od 0 do 1. Ako pomnozam so 52 ke dobijam random brojka

        if(!isDealt[card]){ //ako kartata ne e podelena
            isDealt[card] = true; //togas podeli ja
            dealtTotal++; //brojot na podeleni karti ke se zgolemi
            return cards[card]; //treba da ja vratama kartata sto e podelena vo toj moment
        }
            return dealCard(); //go delam cel spil rekurzivno
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Deck deck = (Deck) o;
        return dealtTotal == deck.dealtTotal && Arrays.equals(cards, deck.cards) && Arrays.equals(isDealt, deck.isDealt);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(dealtTotal);
        result = 31 * result + Arrays.hashCode(cards);
        result = 31 * result + Arrays.hashCode(isDealt);
        return result;
    }

    public PlayingCard[] getCards() {
        return cards;
    }

    public boolean[] getIsDealt() {
        return isDealt;
    }

    public int getDealtTotal() {
        return dealtTotal;
    }

    public static void main(String[] args) {
        Deck deck1 = new Deck();
        System.out.println(deck1);

        deck1.shuffle();
        System.out.println(deck1);

        PlayingCard card;
        while((card = deck1.dealCard()) != null){
            System.out.println(card);
        }

        System.out.println();
        Deck deck2 = new Deck();
        System.out.println(deck2);

        deck2.shuffle();
        System.out.println(deck2);

        deck2.dealCard();
    }
}