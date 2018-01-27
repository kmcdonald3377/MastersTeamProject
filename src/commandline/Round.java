//Nadya
package commandline;

import java.util.ArrayList;
import java.util.Random;

public class Round {

	private ArrayList<PlayerHand> players;
	private PlayerHand[] playerHand;
	private Card[] playableCards;
	private CommunalPile cPile;
	private Deck deck;
	private Player currentPlayer; // index of player whose turn it is to play
	private PlayerHand winner;

	private int numPlayers;
	private int[] currentPlayers; // indexes of players who are currently in the round
	private int[] activePlayers; // indexes of players who are still active in the game

	private final int ATTRIBUTES = 5;
	private boolean draw;

	public Round(ArrayList<PlayerHand> players, Player currentPlayer, CommunalPile cp, Deck deck, Player winner, int numCards) {

		this.players = players;
		numPlayers = this.players.size();
		activePlayers = new int[numPlayers];
		currentPlayers = new int[numPlayers];
		this.deck = deck;
		this.cPile = cp;
		this.currentPlayer = currentPlayer;
		this.winner = null;
	}

	public void start() 
	{

	}

	private void printDeckAttributeNames(Card card) {

		String attribute1 = card.getAttribute1();
		String attribute2 = card.getAttribute2();
		String attribute3 = card.getAttribute3();
		String attribute4 = card.getAttribute4();
		String attribute5 = card.getAttribute5();

		String attributeNames = String.format("%15s %15s %15s %15s %15s", attribute1, attribute2, attribute3,
				attribute4, attribute5);

		System.out.println(attributeNames);

	}

	// a method to display the card and its attributes to the console
	public void printCurrentCardValues(Card card) {

		String name = card.getName();
		String att1 = Integer.toString(card.getValue1());
		String att2 = Integer.toString(card.getValue2());
		String att3 = Integer.toString(card.getValue3());
		String att4 = Integer.toString(card.getValue4());
		String att5 = Integer.toString(card.getValue5());

		String printValues = String.format("%20s %15s %15s %15s %15s %15s", name, att1, att2, att3, att4, att5);

		System.out.println(printValues);
	}

	// a method to distribute the cards after each round, either to the winner of
	// the round, or to the communal pile after a draw
	private void giveCards() {

		// if there is a draw
		if (winner == null) {

			// add cards to CommunalPile
			for (int i = 0; i < playableCards.length; i++) {

				Card cd = playableCards[i];

				//
				if (cd != null) {
					this.cPile.addCard(cd);
				}
			}
		} else {

			// give card to winner
			for (int i = 0; i < playableCards.length; i++) {
				Card cd = playableCards[i];

				if (cd != null) {
					winner.addToHand(cd); // add this card to the players hand
				}
			}
		}

		// give cards in communal pile to winner
		for (int i = 0; i < cPile.getPileLength(); i++) {

			Card cd = cPile.getCardAtIndex(i);

			if (cd != null) {
				winner.addToHand(cd); // add this card to the players hand
			}
		}

		// communal pile is now empty, create new communal pile
		cPile = new CommunalPile();
	}

	// a method to calculate the winning card
	private void calculateWinner() {

		final int ACTIVECARD = 1; // current card can only ever be one
		int score;

		for (int i = 0; i < this.players.length; i++) {

			// if the players hand size or the number of cards in play are greater than 0
			if ((playerHand[i].getHandLength() || ACTIVECARD) > 0) { // getHandLength in PlayerHand class

				try {

					if (score == 0) {

						winner = null; // draw
					}
				} catch (Exception e) {
					System.out.println(players[i] + " has no cards left to play.");
				}
			}
		}
	}

	// a method to check if the round ended in a draw
	private boolean checkForDraw() {

		boolean draw = false;

		// code

		return draw;
	}

	public boolean isDraw() {

		return this.draw;
	}

	public Player getWinner() {

		return winner;
	}

	public boolean humanWinner() {

		int index = 0;
		Player player = this.players[index];

		// when the human players hand size equals the number of cards in the deck
		return player.getHandLength() == 40; // Player class
	}

	public boolean AIWinner() {

		int index = 0;
		Player player = this.players[index];

		// player hand is 0
		return player.getHandLength() == 0;
	}
	

	private void aiCategorySelection(PlayerHand player) 
	{
		Random randomNumber = new Random();
		int myNumber = randomNumber.nextInt();
		
	}
	
	public void humanCategorySelection(int number) 
	{
		Card test = getCard();
		Card ai1 = players.get(1).getCurrentCard();
		Card ai2 = players.get(2).getCurrentCard();
		Card ai3 = players.get(3).getCurrentCard();
		Card ai4 = players.get(4).getCurrentCard();
		
		if(number == 1) 
		{
			test.getValue1();
			ai1.getValue1();
			ai2.getValue1();
			ai3.getValue1();
			ai4.getValue1();
			
		}
		else if(number == 2) 
		{
			test.getValue2();
			ai1.getValue2();
			ai2.getValue2();
			ai3.getValue2();
			ai4.getValue2();
			
		}
		else if(number == 3) 
		{
			test.getValue3();
			ai1.getValue3();
			ai2.getValue3();
			ai3.getValue3();
			ai4.getValue3();
			
		}
		else if(number == 4) 
		{
			test.getValue4();
			ai1.getValue4();
			ai2.getValue4();
			ai3.getValue4();
			ai4.getValue4();
		}
		else if(number == 5) 
		{
			test.getValue5();
			ai1.getValue5();
			ai2.getValue5();
			ai3.getValue5();
			ai4.getValue5();
			
		}
	}
	
	public Card getCard() 
	{
		Card currentCard = players.get(0).getCurrentCard();
		return currentCard;
	}
}
