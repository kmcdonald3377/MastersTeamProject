package commandline;

import java.util.ArrayList;
import java.util.Collections;

public class Deck 
{
	ArrayList<Card> deck = new ArrayList();
	
	public Deck() 
	{
		
	}
	
	public void addCard(Card card) 
	{
		deck.add(card);
	}
	
	public void shuffleDeck() 
	{
		Collections.shuffle(deck);
	}
}
