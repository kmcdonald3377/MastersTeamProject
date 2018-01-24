package commandline;

import java.util.ArrayList;

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
}
