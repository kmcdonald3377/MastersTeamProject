package commandline;

import java.util.ArrayList;
import java.util.Collections;

public class PileOfCards 
{
	private Integer PLAYER_ID;
	ArrayList<Card> deck = new ArrayList();
	
	public PileOfCards(Integer playerId) 
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
	
	public ArrayList<Card> getDeck() 
	{
		return deck;
	}
	
	public Integer getPlayerID() 
	{
		return PLAYER_ID;
	}
	
	public void setPlayerID(Integer id) 
	{
		PLAYER_ID = id;
	}
	
	public int getNumberOfCards() 
	{
		return deck.size();
	}
	
	public Card getCurrentCard() 
	{
		if(deck.size() == 0) {
			return null;
		}
		return deck.get(0);
	}
}
