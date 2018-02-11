package commandline;
import java.io.*;
import java.util.ArrayList;

/**
 * Class creates a test log object. 
 * 
 * Test log will create/overwrite text file 'toptrumps.log' and print details 
 * of the game's operation.
 * 
 * Each method writes something to the test log.
 */

public class TestLog {
	// instance variables
	private final String fin = ("toptrumps.log");
	private final StringBuilder sb = new StringBuilder();
	private ArrayList<Card> initDeck, shuffledDeck, comPile, humanHand, AIHand;
	private String lineBreak, _winner;
	private int _playerID, playerID;

	/**
	 *  constructor method
	 *  
	 *   @param initDeck, shuffledDeck, comPile - ArrayList of Card objects 
	 *   representing the initial deck, shuffled deck and communal pile
	 *   @param humanHand, AIHand - ArrayList of Card objects representing
	 *   human and AI players hands
	 *   @param _winner - string representing winner of the game
	 *   @param lineBreak - string creating a linebreak between sections of the testlog
	 *   @param _playerID, playerID - int representing the ids of players in game
	 */
	
	
	// constructor
	public TestLog ()
	{
		initDeck = new ArrayList<Card>();
		shuffledDeck = new ArrayList<Card>();
		comPile = new ArrayList<Card>();
		humanHand = new ArrayList<Card>();
		AIHand = new ArrayList<Card>();
		_winner = "";
		lineBreak = "------------------------------------------------------------------------";
		_playerID = 0;
		playerID = 0;
	}

	/**
	 *  writes the original deck as loaded from file 
	 * @param deck - PileOfCards object representing the deck as read in from .txt file
	 */
	public void writeInitDeck (PileOfCards deck) {
		sb.append("Displaying original deck as loaded from StarCitizenDeck.txt: \r\n");
		initDeck = deck.getDeck();
		sb.append("\r\n");
		for (Card c : initDeck)
		{
			sb.append(c);
			sb.append(" ");
		}

		sb.append("\r\n" + lineBreak + "\r\n");
	}

	/**
	 *  writes the contents of the deck after it has been shuffled
	 * @param deck - PileOfCards representing deck after shuffle
	 */
	public void writeShuffledDeck (PileOfCards deck) {
		sb.append("\r\nDisplaying deck after shuffle: \r\n");
		shuffledDeck = deck.getDeck();
		sb.append("\r\n");
		
		for(Card c : shuffledDeck)
		{
			sb.append(c);
			sb.append(" ");
		}
		sb.append("\r\n" + lineBreak + "\r\n");
	}

	/**
	 *  this method will write the contents of the communal pile as cards are
	 *  @param - PileOfCards representing communal pile 
	 */

	public void writeCommunalPile (PileOfCards communalPile)	{
		{
			sb.append("\r\nCard added to pile. \r\n");
			sb.append("\r\nCards in the Communal Pile: \r\n");
			comPile = communalPile.getDeck();
			
			for (Card c: comPile)
			{
				sb.append(c);
				sb.append(" ");
			}
			
			sb.append("\r\n" + lineBreak + "\r\n");
		}
	}
	
	/**
	 *  prints message to indicate all cards were removed from the communal pile
	 *  following a round win 
	 */
	public void writeCommunalPileEmpty()	{
		sb.append("\r\nCards removed. Communal pile is empty. \r\n");
		sb.append("\r\n" + lineBreak + "\r\n");
	}

	/**
	 *  method to write each players deck/hand, detecting if human/AI
	 *  Cards are printed by name.
	 *  
	 *  @param j - int of player number assigned to playerID
	 *  @param playerHand - PileOfCards representing players hand
	 */

	public void writeHand(int j, PileOfCards playerHand) {
		playerID = j;
		if (playerID == 1)
		{
			humanHand = playerHand.getDeck();
			sb.append("\r\nHuman Player " + playerID + "'s deck is:  \r\n");
			for(Card c: humanHand)
			{
				sb.append(c);
				sb.append(" ");
			}
			sb.append("\r\n" + lineBreak + "\r\n");
		}
		else
		{
			AIHand = playerHand.getDeck();
			sb.append("\r\nAI Player " + playerID + "'s deck is:  \r\n");
			for(Card c: AIHand)
			{
				sb.append(c);
				sb.append(" ");
			}
			sb.append("\r\n" + lineBreak + "\r\n");
		}

	}

	/**
	 *  writes the details of each players first card in play
	 *  i.e. name, attributes and the values of those attributes
	 *  
	 *  @param i - int representing player id assigned to _playerID
	 *  @param card - card object of player's current card in play
	 */
	public void writeCardsIP (int i, Card card)	{
		_playerID = i+1;
		if (_playerID==1)
		{
			sb.append("Human ");
		}
		else
		{
			sb.append("AI ");
		}
		sb.append("Player " + _playerID + "'s card in play: " + card.getName() + " " + card.getAttribute1()
		+ " " + card.getValue1() + " " + card.getAttribute2() + " " + card.getValue2() + " "
		+ " " + card.getAttribute3() + " " + card.getValue3() + " " + card.getAttribute4() + " "
		+ card.getValue4() + " " + card.getAttribute5() + " " + card.getValue5() + "\r\n");
		sb.append("\r\n" + lineBreak + "\r\n");
	}
	
	/**
	 *  writes category selected in each round
	 *  this method indicates which category the first choice player has chosen
	 *  
	 *  @param category - string representing selected category/attribute
	 */

	public void writeCategory(String category)
	{
		sb.append("\r\nSelected Category: " + category + "\r\n");
	}
	
	/**
	 *  writes the attribute and it's value for each player in the selected category
	 * @param s - string of the stats/value of each players attribute chosen in round
	 */
	public void writeStats(String s)
	{
		sb.append("Showing stats: ");
		sb.append(s);
		sb.append("\r\n" + lineBreak + "\r\n");
	}


	/**
	 *  writes the winner of game, indicating if human or AI 
	 * @param winner - String of human/AI winning player
	 */
	public void writeWinner (String winner)	{
		_winner = winner;
		sb.append("\r\n" + _winner + " player wins!  ");
		sb.append("\r\nGAME OVER");
	}


	// writes the whole game log to file
	public void writeFile () {
		try {
			File log = new File(fin);
			PrintWriter writer = new PrintWriter(log);
			writer.append(sb);
			writer.close();
		} catch (FileNotFoundException e) {
			System.out.println("\r\nFile not found. ");
		}

	}

}