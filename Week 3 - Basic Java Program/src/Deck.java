import java.util.Random;
import java.util.ArrayList;

public class Deck {
    private ArrayList<Card> cards;

    /** Deck Constructor:
     *  Description:    Create a new deck object. Using nested loops a deck can consist of each card of a deck. As part of
     *                  the constructor the deck is shuffled into a random order using the variable generator.
     **/
    Deck() {
        cards = new ArrayList<Card>();
        int index1, index2;
        Random generator = new Random();
        Card temp;

        for (short a=0; a<=3; a++) {
            for (short b=0; b<=12; b++) {
                cards.add( new Card(a,b));
            }
        }

        int size = cards.size()-1;

        for (short i=0; i<100; i++) {
            index1 = generator.nextInt(size);
            index2 = generator.nextInt(size);

            temp = (Card) cards.get( index2);
            cards.set(index2, cards.get( index1));
            cards.set(index1, temp);
        }
    }

    /** Method Name:    drawFromDeck
     *  Description:    Return the last element of the array list cards and remove it as well.
     *
     *  Parameter:      No parameter
     *  Output:         Card object: Return the card at the end of the deck
     **/
    public Card drawFromDeck() {
        return cards.remove(cards.size()-1);
    }

    /** Method Name:    getTotalCards
     *  Description:    Return the size of the array list cards
     *
     *  Parameter:      No parameter
     *  Output:         Integer: Return the size of the cards arraylist
     **/
    public int getTotalCards() {
        return cards.size();
    }
} 