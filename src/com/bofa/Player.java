package com.bofa;

import java.util.ArrayList;

/**
 * Created by trooney on 11/30/15.
 */
public class Player implements Comparable<Player> {

    private final int playerNumber;
    private ArrayList<Card> hand = null;

    public Player(int number) {
        playerNumber = number;
        hand = new ArrayList<>();
    }

    public Card playCard() {
        Card c = null;
        if(!hand.isEmpty()) {
            c = hand.remove(0);
        }
        return c;
    }

    public void giveCard(Card c){
        hand.add(c);
    }

    public int cardCount() {
        return hand.size();
    }

    public ArrayList<Card> getWarPile() {
        ArrayList<Card> pile = new ArrayList<>();

        for(int i=0; i < 3; i++)
            pile.add(hand.remove(0));

        return pile;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public boolean hasCards() {
        if(hand.isEmpty()){
            return false;
        }
        return true;
    }

    public void collectPot(Pot pot) {
        hand.addAll(pot.collectPot());
    }

    @Override
    public int compareTo(Player p) {
        if(this.getPlayerNumber() == p.getPlayerNumber())
            return 0;
        else
            return this.getPlayerNumber() > p.getPlayerNumber() ? 1 : -1;
    }
}
