package cracking_the_coding_interview.ch9_7_object_oriented_programming.q1_deck_of_cards;

import java.util.Random;

/**
 * Created By: Prashant Chaubey
 * Created On: 15-09-2019 19:10
 * Purpose: TODO:
 **/
enum CardSuite {
    SPADE, DIAMOND, CLUB, HEART
}

enum CardValue {
    TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE;
}

class Card {
    private CardSuite suite;
    private CardValue value;
    private boolean inHand;

    public Card(CardSuite suite, CardValue value) {
        this.suite = suite;
        this.value = value;
    }

    public CardSuite getSuite() {
        return suite;
    }

    public CardValue getValue() {
        return value;
    }

    public boolean isInHand() {
        return inHand;
    }

    public void setInHand(boolean inHand) {
        this.inHand = inHand;
    }
}

abstract class DeckOfCards {
    private Card[] cards;
    private CardSuite trumpSuite;
    //How many indexes are given to players.
    private int dealtIndex;

    void shuffle() {
    }

    abstract void initialize();

    abstract int getWinningCardIndex(Card[] playerCards);

    public Card[] getCards(int num) {
        return null;
    }

    boolean areCardsLeft() {
        return dealtIndex != cards.length;
    }

    public CardSuite getTrumpSuite() {
        return trumpSuite;
    }

    public void setTrumpSuite(CardSuite trumpSuite) {
        this.trumpSuite = trumpSuite;
    }
}

class DeckOfCardsFor325 extends DeckOfCards {
    private static int NO_OF_PLAYERS = 3;

    @Override
    void initialize() {

    }

    @Override
    int getWinningCardIndex(Card[] playerCards) {
        if (playerCards.length != NO_OF_PLAYERS) {
            throw new RuntimeException("Invalid no of players");
        }
        return -1;
    }

}

class Person {
    Card[] cardsInHand;
    Card[] wonCards;

    void addCard(Card card) {
    }

    void addCards(Card[] cards) {

    }

    Card runCard() {
        return null;
    }

    void addWonCards(Card[] wonCards) {

    }

    CardSuite getTrumpSuite() {
        return null;
    }

    boolean isEmptyHand() {
        return cardsInHand.length == 0;
    }

    int wonCardsSize() {
        return -1;
    }
}

class Game {

    public static void main(String[] args) {
        int playersCount = 3;
        DeckOfCards deck = new DeckOfCardsFor325();
        deck.initialize();
        deck.shuffle();
        Person[] persons = new Person[playersCount];
        for (Person person : persons) {
            person.addCards(deck.getCards(5));
        }
        deck.setTrumpSuite(persons[(int) new Random().nextInt(playersCount)].getTrumpSuite());
        for (Person person : persons) {
            person.addCards(deck.getCards(3));
        }
        for (Person person : persons) {
            person.addCards(deck.getCards(2));
        }
        while (!persons[0].isEmptyHand()) {
            Card[] runningCards = new Card[playersCount];
            for (int i = 0; i < playersCount; i++) {
                runningCards[i] = persons[i].runCard();
            }
            int winningIndex = deck.getWinningCardIndex(runningCards);
            persons[winningIndex].addWonCards(runningCards);
        }
        int winningIndex = 0;
        for (int i = 0; i < playersCount; i++) {
            if (persons[i].wonCardsSize() > persons[winningIndex].wonCardsSize()) {
                winningIndex = i;
            }
        }
        System.out.println("Winner is " + persons[winningIndex]);

    }
}



