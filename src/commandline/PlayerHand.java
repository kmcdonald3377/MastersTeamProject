package commandline;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class PlayerHand 
{
	private final String PLAYER_ID;
	private ArrayList<Card> hand;
	
	public PlayerHand(String playerId) 
	{
		hand = new ArrayList();
		PLAYER_ID = playerId;
	}
	
	public void addToHand(Card card) 
	{		
		hand.add(card);
	}
	
	public void removeFromHand(Card card) 
	{
		hand.remove(card);
	}
	
	public String getPlayerID() 
	{
		return PLAYER_ID;
	}
	
	public int getNumberOfCards() 
	{
		return hand.size();
	}
	
	public Card getCurrentCard() 
	{
		return hand.get(0);
	}
}

