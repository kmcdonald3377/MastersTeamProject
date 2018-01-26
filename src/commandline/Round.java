//Nadya
package commandline;

public class Round {

	private Player[] players;
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

	public void round(Player[] players, Player currentPlayer, CommunalPile cp, Deck deck, Player winner, int numCards) {

		this.players = players;
		numPlayers = this.players.length;
		activePlayers = new int[numPlayers];
		currentPlayers = new int[numPlayers];
		this.deck = deck;
		this.cPile = cp;
		this.currentPlayer = currentPlayer;
		this.winner = null;
	}

	public void start() {

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
}
