package com.bofa;

import java.util.ArrayList;
import java.util.Random;

/**
 * Implements the deck class.  This represents a collection of playing cards
 * Upon initialization the deck will contain a set of 52 card objects
 * The deck may be shuffled at any point
 *
 *
 * Created by trooney on 11/30/15.
 */
public class Deck {

    ArrayList<Card> cards = new ArrayList<>();


    public Deck(){
        initializeDeck();
    }

    private void initializeDeck() {
        for(int v=1; v<14; v++){
            for(int s=1; s<=4; s++){
                Card c = new Card(v,s);
                cards.add(c);
            }
        }
    }


    public void shuffle() {
        Random r = new Random();
        int next = 0;
        ArrayList<Card> shuffled = new ArrayList<>();
        while(!cards.isEmpty()){
            next = r.nextInt(cards.size());
            shuffled.add(cards.remove(next));
        }
        cards = shuffled;
    }

    public boolean hasCards() {
        if(cards.isEmpty())
            return false;
        return true;
    }

    public Card dealCard() {
        Card c = null;
        if(!cards.isEmpty())
            c = cards.remove(0);
        return c;
    }
}
