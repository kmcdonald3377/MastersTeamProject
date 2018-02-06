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
	private ArrayList<Card> comPile;
	private Card cardIP;
	private ArrayList<Card> humanHand;
	private ArrayList<Card> AIHand;

	private String lineBreak, _addRemove, _winner;
	private int _playerID;
	
	private int roundCount;
	private int playerID;
	private final int MAX_SIZE = 41;

	public TestLog ()
	{
		initDeck = new ArrayList<Card>();
		shuffledDeck = new ArrayList<Card>();
		comPile = new ArrayList<Card>();
		humanHand = new ArrayList<Card>();
		AIHand = new ArrayList<Card>();
		
		_winner = "";
		
		lineBreak = "------------------------------------------------------------------------";
		_addRemove = "";
		_playerID = 0;
		
		roundCount = 0;
		playerID = 0;
	}
	
	// write original deck as loaded from file
	public void writeInitDeck (PileOfCards deck) {
		sb.append("Displaying original deck as loaded from StarCitizenDeck.txt: \r\n");
		initDeck = deck.getDeck();
		
		for (Card c : initDeck)
		{
			sb.append(c);
			sb.append(" ");
		}
		
		sb.append("\r\n" + lineBreak + "\r\n");
	}
	
	// write shuffled deck
	public void writeShuffledDeck (PileOfCards deck)	{
		sb.append("\r\n Displaying deck after shuffle: \r\n");
		shuffledDeck = deck.getDeck();
		
		for(Card c : shuffledDeck)
		{
			sb.append(c);
			sb.append(" ");
		}
		sb.append("\r\n" + lineBreak + "\r\n");
	}
	
	public void writeCurrentDeck (PileOfCards deck)	{
		sb.append("\r\n Displaying deck after round: \r\n");
		shuffledDeck = deck.getDeck();
		
		for(Card c : shuffledDeck)
		{
			sb.append(c);
			sb.append(" ");
		}
		sb.append("\r\n" + lineBreak + "\r\n");
	}
	
	// write communal pile
	public void writeCommunalPile (String addRemove, PileOfCards communalPile)	{
		_addRemove = addRemove;
		if (_addRemove.equalsIgnoreCase("add"))
		{
			sb.append("\r\n Card added to pile. \r\n");
		}
		else if (_addRemove.equalsIgnoreCase("remove"))
		{
			sb.append("\r\n Card removed from pile. \r\n");
		}
		sb.append("Cards in the Communal Pile: \r\n");
		comPile = communalPile.getDeck();
		for (Card c: comPile)
		{
			sb.append(c);
			sb.append(" ");
		}
		sb.append("\r\n" + lineBreak + "\r\n");
	}
	
	// method to write hand, detecting if human/AI
	public void writeHand(int j, PileOfCards playerHand) {
		sb.append("\r\nWriting player hand... ");
		
		playerID = j;
		if (playerID == 1)
		{
			humanHand = playerHand.getDeck();
			sb.append("Human Player " + playerID + "'s hand is:  ");
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
			sb.append("AI Player " + playerID + "'s hand is:  ");
			for(Card c: AIHand)
			{
				sb.append(c);
				sb.append(" ");
			}
			sb.append("\r\n" + lineBreak + "\r\n");
		}
			
	}
	
	// write first card in play
	public void writeCardsIP (int i, Card card)	{
		_playerID = i+1;
		cardIP = card;
		sb.append("Player " + _playerID + "'s card in play: " + card.getName() + " " + card.getAttribute1()
		+ " " + card.getValue1() + " " + card.getAttribute2() + " " + card.getValue2() + " "
		+ " " + card.getAttribute3() + " " + card.getValue3() + " " + card.getAttribute4() + " "
		+ card.getValue4() + " " + card.getAttribute5() + " " + card.getValue5() + "\r\n");
		sb.append("\r\n" + lineBreak + "\r\n");
	}
	
	
	// write number of rounds played
	public void writeRounds (int totalRounds)	{
		roundCount = totalRounds;
		sb.append("\r\n Number of rounds: " + roundCount);
		sb.append("\r\n" + lineBreak + "\r\n");
	}
	
	// writes if there is a winner of game 
	public void writeWinner (String winner)	{
		_winner = winner;
			sb.append("\r\nPlayer " + _winner + " wins!  ");
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