//Nadya
package commandline;

import java.util.ArrayList;
import java.util.Random;

public class Round 
{

	private ArrayList<PileOfCards> players;
	private PileOfCards[] playerHand;
	private Card[] playableCards;
	private PileOfCards cPile;
	private PileOfCards deck;
	private Player currentPlayer; // index of player whose turn it is to play
	private PileOfCards winner;

	private int numPlayers;
	private int[] currentPlayers; // indexes of players who are currently in the round
	private int[] activePlayers; // indexes of players who are still active in the game

	private final int ATTRIBUTES = 5;
	private boolean draw;

	public Round(ArrayList<PileOfCards> players, Player currentPlayer, PileOfCards cp, PileOfCards deck, Player winner, int numCards) 
	{

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
					winner.addCard(cd); // add this card to the players hand
				}
			}
		}

		// give cards in communal pile to winner
		while (cPile.getNumberOfCards() != 0) {

			Card cd = cPile.getCurrentCard();

			if (cd != null) {
				winner.addCard(cd); // add this card to the players hand
			}
		}

		// communal pile is now empty, create new communal pile
		cPile = new PileOfCards(null);
	}

	// a method to calculate the winning card
	private void calculateWinner() {

		final int ACTIVECARD = 1; // current card can only ever be one
		int score;

		for (int i = 0; i < this.players.size(); i++) {

			// if the players hand size or the number of cards in play are greater than 0
			if ((playerHand[i].getHandLength() || ACTIVECARD) > 0) { // getHandLength in PlayerHand class //playerHand.getNumberOfCards?

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

	public PileOfCards getWinner() {

		return winner;
	}

	public boolean humanWinner() {

		int index = 0;
		Player player = this.players.get(index);

		// when the human players hand size equals the number of cards in the deck
		return player.getPlayerHand().getNumberOfCards() == 40; // Player class
	}

	public boolean AIWinner() {

		int index = 0;
		Player player = this.players.get(index);

		// player hand is 0
		return player.getPlayerHand().getNumberOfCards() == 0;
	}
	

	private ArrayList aiCategorySelection(PileOfCards player) 
	{
		Random randomNumber = new Random();
		int myNumber = randomNumber.nextInt();
		Card test = getCard();
		Card ai1 = players.get(1).getCurrentCard();
		Card ai2 = players.get(2).getCurrentCard();
		Card ai3 = players.get(3).getCurrentCard();
		Card ai4 = players.get(4).getCurrentCard();
		
		ArrayList<Integer> valueComparison = new ArrayList();
		valueComparison.add(test.getValueAtIndex(myNumber));
		valueComparison.add(ai1.getValueAtIndex(myNumber));
		valueComparison.add(ai2.getValueAtIndex(myNumber));
		valueComparison.add(ai3.getValueAtIndex(myNumber));
		valueComparison.add(ai4.getValueAtIndex(myNumber));
		
		return valueComparison;
	}
	
	public ArrayList humanCategorySelection(int number) 
	{
		Card test = getCard();
		Card ai1 = players.get(1).getCurrentCard();
		Card ai2 = players.get(2).getCurrentCard();
		Card ai3 = players.get(3).getCurrentCard();
		Card ai4 = players.get(4).getCurrentCard();
		
		ArrayList<Integer> valueComparison = new ArrayList();
		valueComparison.add(test.getValueAtIndex(number));
		valueComparison.add(ai1.getValueAtIndex(number));
		valueComparison.add(ai2.getValueAtIndex(number));
		valueComparison.add(ai3.getValueAtIndex(number));
		valueComparison.add(ai4.getValueAtIndex(number));
		
		return valueComparison;
		
	}
	
	public int findWinner(ArrayList test) 
	{
		ArrayList<Integer> myNumbers = test;
		int max = 0;
		for(int i = 0; i < myNumbers.size(); i++) 
		{
			if(myNumbers.get(i) > max) 
			{
				max = myNumbers.get(i);
			}
		}
		return max;
	}
	
	public boolean isWinner(ArrayList test) 
	{
		ArrayList<Integer> myNumbers = test;
		int max = findWinner(myNumbers);
		int count = 0;
		for(int i = 0; i < test.size(); i++) 
		{
			if(max == myNumbers.get(i)) 
			{
				count++;
			}
		}
		
		if(count > 1) 
		{
			return false;
		}
		
		return true;
	}
	
	public Card getCard() //could have int parameter to feed in which will get the card depending upon the id of the players playing
	{
		Card currentCard = players.get(0).getCurrentCard();
		return currentCard;
	}
}
