package View;
import Model.Deck;
import Model.Hand;

public class main {
    public static void main(String[] args) {
        /** Create a new deck, and two hands */
        Deck deck = new Deck();
        Hand hand = new Hand(deck);
        Hand hand2 = new Hand(deck);

        /** Display each deck including: Whether they are the first or second hand, what type of hand, and the
         *  contents of the hand */
        System.out.println("First Hand\n___________");
        hand.display();
        hand.displayAll();
        System.out.println("Second Hand\n___________");
        hand2.display();
        hand2.displayAll();

        /** compare the two hands and display the result*/
        printWinner(hand.compareTo(hand2));
    }

    /** Method Name:    printWinner
     *  Description:    Input an integer value and print out first hand wins, second hand wins, or draw depeding on if
     *                  the input is 1, -1 or 0 respectively.
     *
     *  Parameter:      int: result. The returned value of the compare method in hand class when two hands are compared against
     *                  each other
     *  Output:         No output. Internal print statements only
     **/
    static void printWinner(int result){
        if (result == 1){
            System.out.println("\nFirst hand wins");
        } else if (result == -1) {
            System.out.println("\nSecond hand wins");
        } else {
            System.out.println("\nDraw");
        }
    }
}
