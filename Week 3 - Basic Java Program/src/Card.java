public class Card {
    private short rank, suit;
    private static String[] suits = { "hearts", "spades", "diamonds", "clubs" };
    private static String[] ranks  = { "Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King" };

    /** Method Name:    rankAsString
     *  Description:    Input an integer. Return the value of the rank at the integers index.
     *
     *  Parameter:      int: rank
     *  Output:         String: Return the string value of the element in the ranks array at index "rank"
     **/
    public static String rankAsString(int rank) {
        return ranks[rank];
    }

    /** Card Constructor:
     *  Description:    Create a new Card object and initialise the suit attribute with the short input suit and
     *                  initialise the rank attribute with the short input rank.
     *
     *  Parameters:     short: suit, short: rank
     **/
    Card(short suit, short rank) {
        this.suit=suit;
        this.rank=rank;
    }

    /** Method Name:    toString
     *  Description:    When the Card is constructed, the rank and suit short variable is given a value. This method
     *                  outputs the String element of index "rank" from the ranks array and the element at index suit from
     *                  the suits string array, in the format ""rank" of "suit"".
     *
     *  Parameter:      No parameter
     *  Output:         String: Return the string value of the format ""rank" of "suit"".
     **/
    public @Override String toString() {
        return ranks[rank] + " of " + suits[suit];
    }

    /** Method Name:    getRank
     *  Description:    When the Card is constructed the rank short variable is initialise with a value. This method returns
     *                  that value when called.
     *
     *  Parameter:      No parameter
     *  Output:         Short: Return the short value of the rank variable
     **/
    public short getRank() {
        return rank;
    }

    /** Method Name:    getSuit
     *  Description:    When the Card is constructed the suit short variable is initialise with a value. This method returns
     *                  that value when called.
     *
     *  Parameter:      No parameter
     *  Output:         Short: Return the short value of the suit variable
     **/
    public short getSuit() {
        return suit;
    }
}