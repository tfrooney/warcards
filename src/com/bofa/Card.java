package com.bofa;

/**
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