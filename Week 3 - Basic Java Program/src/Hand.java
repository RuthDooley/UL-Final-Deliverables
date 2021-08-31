public class Hand {
    private Card[] cards;
    private int[] value;

    /** Hand Constructor:
     *  Description:    Create a new hand object. Give the value array the size of 6. Give the cards array the size of 5.
     *                  Call the method drawFromDeck to choose 5 random cards for the hand. Fill the value array given the
     *                  conditions (see comments below).
     *
     *  Parameter:      Deck object: d
     **/
    Hand(Deck d) {
        value = new int[6];
        cards = new Card[5];
        for (int x=0; x<5; x++) {
            cards[x] = d.drawFromDeck();
        }

        //**********
        int[] ranks = new int[14];
        int[] orderedRanks = new int[5];
        boolean flush = true, straight = false;
        int sameCards = 1,sameCards2 = 1;
        int largeGroupRank = 0,smallGroupRank = 0;
        int index = 0;
        int topStraightValue = 0;

        /**
         * The 14 element array ranks represents the rank 1-king a card in a hand could be excluding the 0th element.
         * Filling the array with 0 values.
         * */
        for (int x=0; x<=13; x++){
            ranks[x]=0;
        }

        /**
         * For each card in a hand, find the rank, increment the element of the rank array at this index for all five cards
         * */
        for (int x=0; x<=4; x++){
            ranks[cards[x].getRank()]++;
        }

        /**
         * Program is set to assume the cards for a flush sequence. Increment through the hand, if there is an instance
         * where the suit of card is not equal the suit after it, the hand is therefore not a flush
         * */
        for (int x=0; x<4; x++){
            if (cards[x].getSuit() != cards[x+1].getSuit())
                flush=false;
        }

        /**
         * If ranks[x] is greater than sameCards, the data is assigned there otherwise if ranks[x] is greater than sameCards2,
         * the data is assigned there.
         * */
        for (int x=13; x>=1; x--){
            if (ranks[x] > sameCards) {
                if (sameCards == 1) {
                    largeGroupRank = x;
                } else {
                    sameCards2 = sameCards;
                    smallGroupRank = x;
                }
                sameCards = ranks[x];
            } else if (ranks[x] > sameCards2) {
                sameCards2 = ranks[x];
                smallGroupRank = x;
            }
        }

        /**
         * If there is one ace. The 0th element of ordered ranks is assigned 14 and index incremented by 1.This is so
         * the ace can be cosidered as a high or low ace. IE. 1, 2, 3, 4 or J, Q, K, 1
         * */
        if (ranks[1]==1) {
            orderedRanks[index]=14;
            index++;
        }

        /**
         * Decrementing from 13 - 2 inclusive. If there is only one card in the hand at the index of the decrementer,
         * put this value into the ordered ranks array in the "index"th position and increment index by 1.
         * */
        for (int x=13; x>=2; x--) {
            if (ranks[x]==1) {
                orderedRanks[index]=x; //if ace
                index++;
            }
        }

        /**
         * Check if the hand (of five cards) is a straight. Only need to increment to 9 as the if conditional handles
         * 5 cards at once (9+5 = 14 size of ranks). If the cards are consecutive of one another. The hand is a straight
         * and the straight boolean is true and the highest value of the straight is recorded also.
         * */
        for (int x=1; x<=9; x++) {
            if (ranks[x]==1 && ranks[x+1]==1 && ranks[x+2]==1 && ranks[x+3]==1 && ranks[x+4]==1) {
                straight=true;
                topStraightValue=x+4;
                break;
            }
        }

        /**
         * Specific case to check for a straight, in the case of 10, j, Q, K, Ace. This ace hold higher than a king in
         * this situation and the topStraightValue is set to 14 (king = 13)
         * */
        if (ranks[10]==1 && ranks[11]==1 && ranks[12]==1 && ranks[13]==1 && ranks[1]==1){
            straight=true;
            topStraightValue=14;
        }

        /**
         * Fill the value array with all 0's. The first element of this array is to represent the ype of hand, the following
         * represent the remaining cards in an order corresponding to the type of hand see below
         * */
        for (int x=0; x<=5; x++) {
            value[x]=0;
        }

        /**
         * 1. High Card
         * Value 1 is given to the 0 index to represent the type of hand.
         * The remaining cards are given a separate element of the array
         * */
        if (sameCards==1){
            value[0]=1;
            value[1]=orderedRanks[0];
            value[2]=orderedRanks[1];
            value[3]=orderedRanks[2];
            value[4]=orderedRanks[3];
            value[5]=orderedRanks[4];
        }

        /**
         * 2. One Pair
         * Value 2 is given to the 0 index to represent the type of hand.
         * The rank of the pair is given to the 1st element of the value array. The remaining cards are given a
         * separate element of the array
         * */
        if (sameCards==2 && sameCards2==1){
            value[0]=2;
            value[1]=largeGroupRank;
            value[2]=orderedRanks[0];
            value[3]=orderedRanks[1];
            value[4]=orderedRanks[2];
        }

        /**
         * 3. Two Pairs
         * Value 3 is given to the 0 index to represent the type of hand.
         * The rank of the highest pair is given to the 1st element of the value array. The rank of the other pair is
         * given to the 2nd element of the value array. The remaining card is given a separate element of the array.
         * */
        if (sameCards==2 && sameCards2==2){
            value[0]=3;
            value[1]= largeGroupRank>smallGroupRank ? largeGroupRank : smallGroupRank; //rank of greater pair
            value[2]= largeGroupRank<smallGroupRank ? largeGroupRank : smallGroupRank;
            value[3]=orderedRanks[0];  //extra card
        }

        /**
         * 4. Three of a Kind
         * Value 4 is given to the 0 index to represent the type of hand.
         * The rank of the triple is given to the 1st element of the value array. The remaining cards are given a
         * separate element of the array
         * */
        if (sameCards==3 && sameCards2!=2) {
            value[0]=4;
            value[1]= largeGroupRank;
            value[2]=orderedRanks[0];
            value[3]=orderedRanks[1];
        }

        /**
         * 5. Straight
         * Value 5 is given to the 0 index to represent the type of hand.
         * The rank of the highest card in the straight is given to the 1st element of the array.
         * */
        if (straight && !flush) {
            value[0]=5;
            value[1]=topStraightValue;
        }

        /**
         * 6. Flush
         * Value 6 is given to the 0 index to represent the type of hand.
         * The remaining cards are given a separate element of the array. The highest card is the tie breaker.
         * */
        if (flush && !straight) {
            value[0]=6;
            value[1]=orderedRanks[0];
            value[2]=orderedRanks[1];
            value[3]=orderedRanks[2];
            value[4]=orderedRanks[3];
            value[5]=orderedRanks[4];
        }

        /**
         * 7. Full House
         * Value 7 is given to the 0 index to represent the type of hand.
         * The rank of the triple is given to the 1st element of the array and the rank of the pair os given to the 2nd
         * element of the array.
         * */
        if (sameCards==3 && sameCards2==2) {
            value[0]=7;
            value[1]=largeGroupRank;
            value[2]=smallGroupRank;
        }

        /**
         * 8. Four of a kind
         * Value 8 is given to the 0 index to represent the type of hand.
         * The rank of the four of a kind is given to the 1st element of the array and the rank of the 0th element of the
         * ordered ranks is given to the 2nd element of the array.
         * */
        if (sameCards==4){
            value[0]=8;
            value[1]=largeGroupRank;
            value[2]=orderedRanks[0];
        }

        /**
         * 9. Straight Flush
         * Value 9 is given to the 0 index to represent the type of hand.
         * The highest card of the straight flush is given to the 1st element of the array
         * */
        if (straight && flush) {
            value[0] = 9;
            value[1] = topStraightValue;
        }
    }

    /** Method Name:    display
     *  Description:    Display the type of hand it is ie. royal flush, straight flush etc. depending on the first index
     *                  of the value array. If this is not set print an error message.
     *
     *  Parameter:      No parameter.
     *  Output:         No output. Internal print statements only
     **/
    void display() {
        String s;
        switch(value[0]){
            case 1:
                s="high card";
                break;
            case 2:
                s="pair of " + Card.rankAsString(value[1]) + "\'s";
                break;
            case 3:
                s="two pair " + Card.rankAsString(value[1]) + " " + Card.rankAsString(value[2]);
                break;
            case 4:
                s="three of a kind " + Card.rankAsString(value[1]) + "\'s";
                break;
            case 5:
                s=Card.rankAsString(value[1]) + " high straight";
                break;
            case 6:
                s="flush";
                break;
            case 7:
                s="full house " + Card.rankAsString(value[1]) + " over " + Card.rankAsString(value[2]);
                break;
            case 8:
                s="four of a kind " + Card.rankAsString(value[1]);
                break;
            case 9:
                s="straight flush " + Card.rankAsString(value[1]) + " high";
                break;
            default:
                s="error in Hand.display: value[0] contains invalid value";
        }
        s = "				" + s;
        System.out.println(s);
    }

    /** Method Name:    displayAll
     *  Description:    Print out the value of each of the five cards that are in the hand.
     *
     *  Parameter:      No parameter.
     *  Output:         No output. Internal print statements only
     **/
    void displayAll() {
        for (int x=0; x<5; x++)
            System.out.println(cards[x]);
    }

    /** Method Name:    compareTo
     *  Description:    Compare two hands. If the element of value at index "this" is greater than the element of value
     *                  at index "that", return a 1, if they are equal return a 0, otherwise return a 1.
     *
     *  Parameter:      No parameter.
     *  Output:         Integer: Return an integer value 1, 0 or -1 depending on how the hands compare to one another.
     **/
    int compareTo(Hand that){
        for (int x=0; x<6; x++) {
            if (this.value[x]>that.value[x])
                return 1;
            else if (this.value[x]<that.value[x])
                return -1;
        }
        return 0;
    }
}