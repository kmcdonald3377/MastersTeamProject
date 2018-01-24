package commandline;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

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
		//Move to Game controllor 
		List<Card> human = hand.subList(0, 7);
		List<Card> AI1 = hand.subList(8, 15);
		List<Card> AI2 = hand.subList(16, 23);
		List<Card> AI3 = hand.subList(24, 31);
		List<Card> AI4 = hand.subList(32, 39);
		
	}
	}

