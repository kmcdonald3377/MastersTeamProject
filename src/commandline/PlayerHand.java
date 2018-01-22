package commandline;

import java.util.Arrays;
import java.util.Comparator;

public class PlayerHand 
{
	private final int MAXIMUM_HAND_SIZE = 40;
	private Card[] hand;
	
	public PlayerHand() 
	{
		hand = new Card[MAXIMUM_HAND_SIZE];
	}
	
	public void addToHand(Card card) 
	{
		for(int i = 0; i < hand.length; i++) 
		{
			if(hand[i] == null) 
			{
				hand[i] = card;
				break;
			}
		}
	}
	
	public void removeFromHand(Card card) 
	{
		for(int i = 0; i < hand.length; i++) 
		{
			if(hand[i] == card) 
			{
				hand[i] = null;
				break;
			}
		}
		
		Arrays.sort(hand, Comparator.nullsLast(null)); //think potential fix to move nulls to the end of the array
		
		//this way will definately work - preferable to use above method if it will work
//		int j = 0;
//		
//		for(int i = 0; i < hand.length; i++) 
//		{
//			if(hand[i] != null) 
//			{
//				hand[j] = hand[i];
//				j++;
//			}
//		}
	}

}
