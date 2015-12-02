"""
This program implements the card game War for code review by Bank of America

I first coded this in Java as I was more confident in completing the task in a
 short amount of time and with a more professional quality of code.  I essentially
 converted the java design to Python syntax.  This may not be the most pythonic
 design or style but I wanted to demonstrate the ability to perform the same task
 using object oriented python.

The code for the Python implementation of this exercise appears to be much more
concise than the Java version
"""

import sys
from random import randint

numberOfPlayers = 2
warPile = 3


class Card():
    """ Implements the card class for use in simulating a standard deck of cards """

    def __init__(self, card_value, card_suit):
        self.value = card_value
        self.suit = card_suit


class Deck():
    """ Implements the deck class.  This represents a collection of playing cards
    Upon initialization the deck will contain a set of 52 card objects
    The deck may be shuffled at any point """
    deck = []

    def __init__(self):
        print 'deck init'
        for card_value in range(1, 14):
            for suit_value in range(1, 5):
                card = Card(card_value, suit_value)
                self.deck.append(card)

    def shuffle(self):
        next = 0
        shuffled = []
        while len(self.deck) > 0:
            next = randint(0, len(self.deck) - 1)
            move_card = self.deck[next]
            shuffled.append(move_card)
            self.deck.remove(move_card)

        self.deck = shuffled

    def hascards(self):
        return len(self.deck) > 0

    def size(self):
        return len(self.deck)

    def deal_card(self):
        return self.deck.pop()


class Player():
    def __init__(self, player_number):
        self.hand = []
        self.player_number = player_number

    def give_card(self, card):
        self.hand.append(card)

    def has_cards(self):
        return len(self.hand) > 0

    def play_card(self):
        return self.hand.pop(0)

    def collect_pot(self, pot):
        self.hand.extend(pot.collect_pot())

    def get_war_pile(self):
        pile = []
        for junk in range(0, warPile):
            pile.append(self.hand.pop())
        return pile


class Pot():
    def __init__(self):
        self.high_card = 0
        self.winner = 0
        self.warring_parties = 0
        self.pot = []

    def play_card(self, card, player):
        print str(card.value) + ' ' + str(player.player_number)
        if card.value > self.high_card:
            self.high_card = card.value
            self.winner = player
            self.warring_parties = 0
        elif card.value == self.high_card:
            if self.warring_parties == 0:
                self.warring_parties = []
                self.warring_parties.append(self.winner)
            self.warring_parties.append(player)
        self.pot.append(card)

    def collect_pot(self):
        self.warring_parties = 0
        self.high_card = 0
        winnings = self.pot
        self.pot = []
        return winnings

    def war(self):
        if self.warring_parties != 0:
            return True
        return False

    def going_to_war(self):
        self.warring_parties = 0
        self.high_card = 0
        self.winner = 0

    def add_cards(self, cards):
        self.pot.extend(cards)


def war(pot, warring_parties):
    print 'combat'
    pot.going_to_war()
    for player in warring_parties:
        if len(player.hand) >= warPile + 1:
            pot.add_cards(player.get_war_pile())
        if player.has_cards():
            pot.play_card(player.play_card(), player)

    if pot.war():
        war(pot, pot.warring_parties)


def main(argv):
    print 'War'
    if len(sys.argv) > 2:
        print('players:' + sys.argv[1] + ' war pile: ' + sys.argv[2])
        numberOfPlayers = sys.argv[1]
        warPile = sys.argv[2]

    d = Deck()
    pot = Pot()

    players = []
    for player_number in range(1, int(numberOfPlayers) + 1):
        players.append(Player(player_number))

    print 'players: ' + str(len(players))
    print 'cards: ' + str(d.size())

    d.shuffle()

    # deal
    while (d.hascards()):
        for player in players:
            if d.hascards():
                player.give_card(d.deal_card())

    for player in players:
        print 'player: ' + str(player.player_number) + ' ' + str(
            len(player.hand))  # questionable violation of encapsulation

    # play
    round_count = 0
    while True:
        round_count += 1
        print 'round:' + str(round_count)
        for player in players:
            if player.has_cards():
                pot.play_card(player.play_card(), player)

        if pot.war():
            print 'War!'
            war(pot, pot.warring_parties)

        pot.winner.collect_pot(pot)

        for player in players:
            print 'player-' + str(player.player_number) + ' ' + str(len(player.hand))
            if len(player.hand) == 52:
                print 'Winner: Player-' + str(player.player_number)
                exit(0)


main("")
