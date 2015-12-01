package com.bofa;

import java.util.ArrayList;
import java.util.TreeSet;
import java.util.logging.Logger;

public class WarCards {
    private static final Logger logger = Logger.getLogger(WarCards.class.getName(), null);

    public static int numberOfPlayers = 2;
    public static int warPile = 3;

    public static void main(String[] args) {

        //parameters could include the following
        // number of players
        // number of decks
        // Ace high or low
        // one or three card war
        //

//        parseParameters(args);
        Deck deck = new Deck();

        ArrayList<Player> players = new ArrayList<>();
        for(int playerNumber=1; playerNumber <= numberOfPlayers; playerNumber++){
            Player p = new Player(playerNumber);
            players.add(p);
        }

        Player winner = playGame(deck, players);
        System.out.print("GAME WINNER:" + winner.getPlayerNumber());


    }

    static Player playGame(Deck deck, ArrayList<Player> players){
        System.out.println("play game");
        Pot pot = new Pot();
        deck.shuffle();
        //Deal
        System.out.println("deal");
        while(deck.hasCards()) {
            for (Player p : players) {
                if (deck.hasCards()) {
                    p.giveCard(deck.dealCard());
                } else {
                    break;
                }
            }
        }

        //Play
        int roundCount = 0;
        while(true){
            //Play a round
            System.out.println("play a round:" + roundCount++);
            for(Player p : players){
                if(p.hasCards()){
                    pot.playCard(p.playCard(),p);
                }
            }
            if(pot.war()){
                System.out.println("war");
                war(pot, pot.warringParties());
            }
            System.out.println("winner:" + pot.winner().getPlayerNumber());
            pot.winner().collectPot(pot);

            Player winner = null;
            int playerCount = 0;
            for(Player p : players){
                System.out.println("win check " + p.getPlayerNumber() + " has:" + p.cardCount());
                if(p.hasCards()){
                    playerCount++;
                    winner = p;
                }
            }
            if(playerCount == 1)
                return winner;

        }

    }

    static void war(Pot pot, TreeSet<Player> warringParties){
        pot.goingToWar();
        for(Player p : warringParties){
            if(p.cardCount() >= (warPile + 1)){
                pot.addCards(p.getWarPile());

                if(p.hasCards())
                    pot.playCard(p.playCard(),p);
            }
        }
        if(pot.war()){
            System.out.println("repeat war");
            war(pot, pot.warringParties());
        }

    }
}
