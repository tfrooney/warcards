package com.bofa;

/**
 * Implements the card class for use in simulating a standard deck of cards
 *
 *     Suit is mostly ignored for this exercise.  It does not factor in evaluating
 *  game play.  It along with the value could be mapped to the traditional naming
 * of Spades, Clubs, Diamonds, and Hearts along with Jack, Queen, King, Ace.
 * This would only be cosmetic and can still be implemented without impacting
 * the logic of the game in any significant way.
 *
 * Created by trooney on 11/30/15.
 */
public class Card {

    private final int value;
    private final int suit;

    public Card(int v, int s) {
        value = v;
        suit = s;
    }

    public int getValue() {
        return value;
    }

    public int getSuit() {
        return suit;
    }
}
