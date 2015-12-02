package com.bofa;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * This test is not comprehensive.  Some tests could be broken out into multiple
 * test cases.
 *
 * Created by trooney on 12/1/15.
 */
public class PotTest {

    Pot pot = null;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testPlayCard() throws Exception {

        pot = new Pot();

        Player player_loser = new Player(1);
        Player player_winner = new Player(2);

        Card card_loser = new Card(2,1);
        Card card_tie = new Card(2,2);
        Card card_winner = new Card(3,1);

        pot.playCard(card_loser, player_loser);
        pot.playCard(card_winner, player_winner);

        Player winner = pot.winner();
        assertEquals(player_winner.getPlayerNumber(), winner.getPlayerNumber());

        player_winner.collectPot(pot);

        pot.playCard(card_loser, player_loser);
        pot.playCard(card_tie, player_winner);

        assertTrue(pot.war());

    }

    @Test
    public void testWar() throws Exception {

    }

    @Test
    public void testAddCards() throws Exception {

    }

    @Test
    public void testCollectPot() throws Exception {

    }
}