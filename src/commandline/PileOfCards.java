package commandline;

import java.util.ArrayList;
import java.util.Collections;

public class PileOfCards 
{
	private final int PLAYER_ID;
	ArrayList<Card> deck = new ArrayList();
	
	public PileOfCards(int playerId) 
	{
		PLAYER_ID = playerId;
	}
	
	public void addCard(Card card) 
	{
		deck.add(card);
	}
	
	public void removeCard(Card card) 
	{
		deck.remove(card);
	}
	
	public void shuffle() 
	{
		Collections.shuffle(deck);
	}
	
	public ArrayList getDeck() 
	{
		return deck;
	}
	
	public int getPlayerID() 
	{
		return PLAYER_ID;
	}
	
	public int getNumberOfCards() 
	{
		return deck.size();
	}
	
	public Card getCurrentCard() 
	{
		return deck.get(0);
	}
}
