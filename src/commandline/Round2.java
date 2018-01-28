package commandline;

import java.util.ArrayList;
import java.util.Random;

public class Round2 
{
	private ArrayList<PileOfCards> playerList;
	
	public Round2(ArrayList playerList) 
	{
		this.playerList = playerList;
	}
	
	public ArrayList aiCategorySelection() 
	{
		Random randomNumber = new Random();
		int category = randomNumber.nextInt();
		Card humanPlayer= getCard(0);
		Card ai1 = getCard(1);
		Card ai2 = getCard(2);
		Card ai3 = getCard(3);
		Card ai4 = getCard(4);
		
		ArrayList<Integer> valueComparison = new ArrayList();
		valueComparison.add(humanPlayer.getValueAtIndex(category));
		valueComparison.add(ai1.getValueAtIndex(category));
		valueComparison.add(ai2.getValueAtIndex(category));
		valueComparison.add(ai3.getValueAtIndex(category));
		valueComparison.add(ai4.getValueAtIndex(category));
		
		return valueComparison;
	}
	
	public ArrayList humanCategorySelection(int category) 
	{
		Card humanPlayer = getCard(0);
		Card ai1 = getCard(1);
		Card ai2 = getCard(2);
		Card ai3 = getCard(3);
		Card ai4 = getCard(4);
		
		ArrayList<Integer> valueComparison = new ArrayList();
		valueComparison.add(humanPlayer.getValueAtIndex(category));
		valueComparison.add(ai1.getValueAtIndex(category));
		valueComparison.add(ai2.getValueAtIndex(category));
		valueComparison.add(ai3.getValueAtIndex(category));
		valueComparison.add(ai4.getValueAtIndex(category));
		
		return valueComparison;
		
	}
	
	public int findWinner(ArrayList categoryValues) 
	{
		ArrayList<Integer> valueComparison = categoryValues;
		int max = 0;
		for(int i = 0; i < valueComparison.size(); i++) 
		{
			if(valueComparison.get(i) > max) 
			{
				max = valueComparison.get(i);
			}
		}
		return max;
	}
	
	public boolean isWinner(ArrayList categoryValues) 
	{
		ArrayList<Integer> valueComparison = categoryValues;
		int max = findWinner(valueComparison);
		int count = 0;
		for(int i = 0; i < categoryValues.size(); i++) 
		{
			if(max == valueComparison.get(i)) 
			{
				count++;
			}
		}
		
		if(count > 1) 
		{
			return false;
		}
		
		return true;
	}
	
	public Card getCard(int playerID)
	{
		Card currentCard = playerList.get(playerID).getCurrentCard();
		return currentCard;
	}
}
