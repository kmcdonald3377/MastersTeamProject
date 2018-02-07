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
	private ArrayList<Card> initDeck, shuffledDeck, comPile, humanHand, AIHand;
	private String lineBreak, _winner;
	private int _playerID, playerID;

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

	// write original deck as loaded from file
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

	// write shuffled deck
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

	// write communal pile
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
	
	// write if cpile empty
	public void writeCommunalPileEmpty()	{
		sb.append("\r\nCards removed. Communal pile is empty. \r\n");
		sb.append("\r\n" + lineBreak + "\r\n");
	}

	// method to write each players deck/hand, detecting if human/AI
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

	// write each players first card in play
	public void writeCardsIP (int i, Card card)	{
		_playerID = i+1;
		sb.append("Player " + _playerID + "'s card in play: " + card.getName() + " " + card.getAttribute1()
		+ " " + card.getValue1() + " " + card.getAttribute2() + " " + card.getValue2() + " "
		+ " " + card.getAttribute3() + " " + card.getValue3() + " " + card.getAttribute4() + " "
		+ card.getValue4() + " " + card.getAttribute5() + " " + card.getValue5() + "\r\n");
		sb.append("\r\n" + lineBreak + "\r\n");
	}
	
	// write category selected in each round
	public void writeCategory(String category)
	{
		sb.append("\r\nSelected Category: " + category + "\r\n");
	}
	
	// write statistics for each player in the selected category in each round
	public void writeStats(String s)
	{
		sb.append("Showing stats: ");
		sb.append(s);
		sb.append("\r\n" + lineBreak + "\r\n");
	}


	// writes if there is a winner of game 
	public void writeWinner (String winner)	{
		_winner = winner;
		sb.append("\r\n" + _winner + " player wins!  ");
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