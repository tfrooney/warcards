package com.bofa;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by trooney on 12/1/15.
 */
public class DeckTest {

    Deck deck = null;

    @Before
    public void setUp() throws Exception {
        deck = new Deck();

    }

    @Test
    public void testHasCards() throws Exception {
        int count = 0;
        Card c = null;

        deck = new Deck();
        deck.shuffle();
        assertTrue(deck.hasCards());
        while(count < 51){
            c = deck.dealCard();
            assertTrue(deck.hasCards());
            count++;

        }
        c = deck.dealCard();
        assertFalse(deck.hasCards());

    }

    @Test
    public void testDealCard() throws Exception {
        int count = 0;
        Card c = null;

        deck = new Deck();
        deck.shuffle();
        while(count < 52){
            c = deck.dealCard();
            assertNotNull(c);
            count++;

        }
        c = deck.dealCard();
        assertNull(c);

    }
}