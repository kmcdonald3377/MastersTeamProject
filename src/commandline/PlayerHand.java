package commandline;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class PlayerHand 
{
	private final int MAXIMUM_HAND_SIZE = 40;
	private ArrayList<Card> hand;
	
	public PlayerHand() 
	{
		hand = new ArrayList();
	}
	
	public void addToHand(Card card) 
	{		
		hand.add(card);
	}
	
	public void removeFromHand(Card card) 
	{
		hand.remove(card);
	}
	public void splitcards() {
		
	}
	}

