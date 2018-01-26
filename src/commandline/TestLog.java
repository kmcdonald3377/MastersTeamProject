// Robbie
package commandline;
import java.io.*;
import java.util.ArrayList;

/*
 * Class creates a test log object. 
 * Test log will create/overwrite file 'toptrumps.log' and print details 
 * of the game's operation.
 */

public class TestLog {
	// instance variables
	private final String fin = ("toptrumps.log");
			
	private StringBuilder sb;
	
	private ArrayList<Card> initDeck;
	private ArrayList<Card> shuffledDeck;
	private ArrayList<Card> currentDeck;
	private ArrayList<Card> communalPile; // need class ?
	private ArrayList<Card> cardsIP;
	private ArrayList<Card> humanHand;
	private ArrayList<Card> AIHand;
	
	private Player _winner;
	
	private boolean isDraw;
	
	private String lineBreak;
	
	private int roundCount;
	private int playerID;

	public TestLog ()
	{
		sb = new StringBuilder();
		
		initDeck = new ArrayList();
		shuffledDeck = new ArrayList();
		communalPile = new ArrayList();
		cardsIP = new ArrayList();
		humanHand = new ArrayList();
		AIHand = new ArrayList();
		
		_winner = new Player();
		
		isDraw = false;
		
		lineBreak = "------------------------------------------------------------------------";
		
		roundCount = 0;
		playerID = 0;
	}
	
	// receive original deck as loaded from file
	public void writeInitDeck (ArrayList<Card> deck) {
		sb.append("Displaying original deck as loaded from StarCitizenDeck.txt: \r\n");
		initDeck = deck;
		
		for (Card c : initDeck)
		{
			sb.append(c);
			sb.append(" ");
		}
		
		sb.append(lineBreak + "\r\n");
	}
	
	// receive shuffled deck
	public void writeShuffledDeck (ArrayList<Card> deck)	{
		sb.append("Displaying deck after shuffle: \r\n");
		shuffledDeck = deck;
		
		for(Card c : shuffledDeck)
		{
			sb.append(c);
			sb.append(" ");
		}
		sb.append(lineBreak + "\r\n");
	}
	
	// used to write deck after cards added/removed
	public void writeCurrentDeck (ArrayList<Card> deck) {
		sb.append("Displaying deck after cards added/removed: \r\n");
		currentDeck = deck;
		
		for (Card c : currentDeck)
		{
			sb.append(c);
			sb.append(" ");
		}
		
		sb.append(lineBreak + "\r\n");
	}
	
	// method to write hand, detecting if human/AI
	public void writeHand(ArrayList<Card> hand, String playerId) {
		sb.append("\r\nWriting player hand... ");
		
		playerID = Integer.parseInt(playerId);
		if (playerID == 1)
		{
			humanHand = hand;
			sb.append("Human Player " + playerID + "'s hand is:  ");
			for(Card c: humanHand)
			{
				sb.append(c);
				sb.append(" ");
			}
			sb.append(lineBreak + "\r\n");
		}
		else
		{
			AIHand = hand;
			sb.append("AI Player " + playerID + "'s hand is:  ");
			for(Card c: AIHand)
			{
				sb.append(c);
				sb.append(" ");
			}
			sb.append(lineBreak + "\r\n");
		}
			
	}
	
	// write cards in play
	public void writeCardsIP ()	{
		
	}

	
	// write number of rounds played
	public void writeRounds (int rCount)	{
		roundCount = rCount;
		// test 
		System.out.println("\r\n Number of rounds: " + roundCount);
		sb.append("\r\n Number of rounds: " + roundCount);
	}
	
	// writes if there is a winner of game 
	public void writeWinner (Player winner)	{
		_winner = winner;
			sb.append("\r\nPlayer " + _winner + " wins!  ");
			sb.append("\r\nGAME OVER");
	}
	
	// writes if there is a draw in game 
	public void writeDraw (boolean draw)	{
		isDraw = draw;
			sb.append("\r\nGame ends in a draw, nobody wins!  ");
			sb.append("\r\nGAME OVER");
	}

	// write game log to file
	public void writeFile () {
		try {
			File log = new File(fin)
			PrintWriter writer = new PrintWriter(log);
			writer.append(sb);
			writer.close();
		} catch (FileNotFoundException e) {
			System.out.println("\r\nFile not found. ");
		}
		
	}

}