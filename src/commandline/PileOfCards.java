package commandline;

import java.util.ArrayList;
import java.util.Collections;

/*
 *  class creates PileOfCards to manage decks throughout game.
 */
public class PileOfCards 
{
	private final Integer PLAYER_ID;
	ArrayList<Card> deck = new ArrayList();
	
	/**
	 * constructor
	 * 
	 * @param playerId
	 */
	public PileOfCards(Integer playerId) 
	{
		PLAYER_ID = playerId;
	}
	
	/**
	 * method adds card to deck
	 * @param card - card object to be added to deck
	 */
	public void addCard(Card card) 
	{
		deck.add(card);
	}
	
	/**
	 * method removes card from deck 
	 * 
	 * @param card - card object to be removed from deck 
	 */
	public void removeCard(Card card) 
	{
		deck.remove(card);
	}
	
	/**
	 * method shuffles deck
	 */
	public void shuffle() 
	{
		Collections.shuffle(deck);
	}
	
	/**
	 * method to get deck 
	 * 
	 * @return deck - returns deck
	 */
	public ArrayList<Card> getDeck() 
	{
		return deck;
	}
	
	/**
	 * method to get player id 
	 * 
	 * @return PLAYER_ID - int representing player ID
	 */
	public Integer getPlayerID() 
	{
		return PLAYER_ID;
	}
	
	/**
	 * method to get number of cards in deck 
	 * 
	 * @return deck.size - int returned of deck size 
	 */
	public int getNumberOfCards() 
	{
		return deck.size();
	}
	
	/**
	 * method gets current card
	 * 
	 * @return deck.get(0) - returns current card at top of deck
	 */
	public Card getCurrentCard() 
	{
		return deck.get(0);
	}
}
