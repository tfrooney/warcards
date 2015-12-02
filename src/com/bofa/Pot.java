package com.bofa;

import java.util.ArrayList;
import java.util.TreeSet;

/**
 * Implements the Pot class.  The Pot represents the list of Cards that have been
 * played by all the players in any given round.  The Pot manages the outcome
 * of rounds of play.  A Player will play Cards and the Pot will determine the
 * winner of the round.
 *
 * Created by trooney on 11/30/15.
 */
public class Pot {

    ArrayList<Card> pot = new ArrayList<>();
    TreeSet<Player> warringParties = new TreeSet<>();
    int highCard = 0;
    Player winner = null;

    public void playCard(Card card, Player player) {
        System.out.println("pot: card:" + card.getValue() + ",player:" + player.getPlayerNumber());
        if(card.getValue() > highCard) {
            winner = player;
            highCard = card.getValue();
            warringParties = new TreeSet<>();
        } else if(card.getValue() == highCard) {
            if(warringParties.isEmpty())
                warringParties.add(winner);
            warringParties.add(player);
        }
        pot.add(card);

    }

    public boolean war() {
        if (!warringParties.isEmpty())
            return true;
        else
            return false;
    }

    public Player winner() {
        return winner;
    }

    public void addCards(ArrayList<Card> warPile) {
        pot.addAll(warPile);
    }

    public TreeSet<Player> warringParties() {
        return warringParties;
    }

    public ArrayList<Card> collectPot() {
        warringParties.clear();
        highCard = 0;
        ArrayList<Card> winnings = new ArrayList<>();
        winnings.addAll(pot);
        pot.clear();
        return winnings;
    }

    public void goingToWar() {
        warringParties = new TreeSet<>();
        highCard = 0;
        winner = null;

    }
}
