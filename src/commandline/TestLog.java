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
			
	private final StringBuilder sb = new StringBuilder();
	
	private ArrayList<Card> initDeck;
	private ArrayList<Card> shuffledDeck;
	private ArrayList<Card> currentDeck;
	private Card [] comPile;
	private ArrayList<Card> cardsIP;
	private ArrayList<Card> humanHand;
	private ArrayList<Card> AIHand;
	
	private Player _winner;

	private String lineBreak;
	private String _playerID;
	
	private int roundCount;
	private int playerID;
	private final int MAX_SIZE = 41;

	public TestLog ()
	{
		initDeck = new ArrayList<Card>();
		shuffledDeck = new ArrayList<Card>();
		comPile = new Card[MAX_SIZE];
		cardsIP = new ArrayList<Card>();
		humanHand = new ArrayList<Card>();
		AIHand = new ArrayList<Card>();
		
		_winner = new Player();
		
		lineBreak = "------------------------------------------------------------------------";
		_playerID = "";
		
		roundCount = 0;
		playerID = 0;
	}
	
	// write original deck as loaded from file
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
	
	// write shuffled deck
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
	
	// write communal pile
	public void writeCommunalPile (Card [] card)	{
		sb.append("Cards in the Communal Pile: \r\n");
		comPile = card;
		for (Card c: comPile)
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
	
	// write first card in play
	public void writeCardsIP (String playerID, ArrayList<Card> hand)	{
		_playerID = playerID;
		cardsIP = hand;
		sb.append(_playerID + "'s card in play: " + hand.get(0));
		sb.append(lineBreak + "\r\n");
	}
	
	
	// write number of rounds played
	public void writeRounds (int totalRounds)	{
		roundCount = totalRounds;
		sb.append("\r\n Number of rounds: " + roundCount);
		sb.append(lineBreak + "\r\n");
	}
	
	// writes if there is a winner of game 
	public void writeWinner (Player winner)	{
		_winner = winner;
			sb.append("\r\nPlayer " + _winner + " wins!  ");
			sb.append("\r\nGAME OVER");
	}
	
	// writes if there is a draw in game 
	public void writeDraw ()	{
			sb.append("\r\nGame ends in a draw.  ");
			sb.append("\r\nGAME OVER");
	}

	// write game log to file
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