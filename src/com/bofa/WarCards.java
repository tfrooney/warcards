package com.bofa;

import java.util.ArrayList;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class implements the card game War for code review by Bank of America
 *
 * This simulation demonstrates object oriented coding principles.
 * At a high level the simulation brings together Card, Deck, Pot and Player objects
 * to simulate the progress and outcome of the card game War.  This game has no strategy
 * and has the hazard of becoming unending.  A round limit is available to cap the run.
 *
 * Basic function - A Deck is instantiated that contains a complete set of Card objects.
 * A list of Players is instantiated to begin the game.  Once the game is started a Pot is
 * instantiated and a round commences.  In a round each Player plays (transfers) a card to
 * the Pot.  The Pot keeps track of who played which Card and determines a winner.  If there
 * is War then a round is extended whereby each player must submit a War Pile and play a
 * Card.  If a player does not have enough cards to satisfy the requirements of any round they
 * have no chance of winning and have been eliminated from the game.  As War may happen multiple
 * times in a round, calls to War are recursive.  Only tied players advance as warring parties.
 * When only one Player has Cards at the end of a round a game winner is declared.
 *
 */
public class WarCards {
    private static final Logger logger = Logger.getLogger(WarCards.class.getName(), null);

    public static int numberOfPlayers = 2;
    public static int warPile = 3;

    public static void main(String[] args) {

        parseParameters(args);
        Deck deck = new Deck();

        ArrayList<Player> players = new ArrayList<>();
        for(int playerNumber=1; playerNumber <= numberOfPlayers; playerNumber++){
            Player p = new Player(playerNumber);
            players.add(p);
        }

        Player winner = playGame(deck, players);
        System.out.println("GAME WINNER:" + winner.getPlayerNumber());


    }

    private static void parseParameters(String[] args) {
        //parameters include the following
        // number of players
        // one or three card war
        if (args.length != 2) {
            logger.log(Level.SEVERE, "Invalid command line");
            System.out.println("Usage>java -jar Warcards.jar players warpile");
            System.exit(0);
        }

        try{
            numberOfPlayers = Integer.parseInt(args[0]);
            warPile = Integer.parseInt(args[1]);
        } catch (Exception e){
            System.out.println("Usage:>java -jar Warcards.jar players warpile.");
            System.exit(0);
        }

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
