package commandline;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

public class Round2 
{
	private ArrayList<Player> playerList;
	private ArrayList<Integer> valueComparison;
	
	public Round2(ArrayList<Player> playerList) 
	{
		this.playerList = playerList;
	}
	
	public String categorySelection() 
	{
		Random randomNumber = new Random();
		int category = randomNumber.nextInt(5);
		
		Card humanPlayerCard = getCard(0);
				
		String categoryKey = humanPlayerCard.getAttributeAtIndex(category);
		return categoryKey;
	}
	
	public ArrayList<Integer> categoryValues(String category) 
	{
		Card humanPlayerCard = getCard(0);
		HashMap<String, Integer> humanAttributes = humanPlayerCard.getAttributes();
		
		Card ai1Card = getCard(1);
		HashMap<String, Integer> ai1Attributes = ai1Card.getAttributes();
		
		Card ai2Card = getCard(2);
		HashMap<String, Integer> ai2Attributes = ai2Card.getAttributes();
		
		Card ai3Card = getCard(3);
		HashMap<String, Integer> ai3Attributes = ai3Card.getAttributes();
		
		Card ai4Card = getCard(4);
		HashMap<String, Integer> ai4Attributes = ai4Card.getAttributes();
		
		valueComparison = new ArrayList<Integer>();
		valueComparison.add(humanAttributes.get(category));
		valueComparison.add(ai1Attributes.get(category));
		valueComparison.add(ai2Attributes.get(category));
		valueComparison.add(ai3Attributes.get(category));
		valueComparison.add(ai4Attributes.get(category));
		
		return valueComparison;
		
	}
	
	public int findMaxScore(String category) 
	{
		ArrayList<Integer> valueComparison = categoryValues(category);
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
	
	public boolean isWinner(String category) 
	{
		ArrayList<Integer> valueComparison = categoryValues(category);
		int max = findMaxScore(category);
		int count = 0;
		for(int i = 0; i < valueComparison.size(); i++) 
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
	
	public ArrayList<Player> findWinner(String category) 
	{
		ArrayList<Player> winningPlayer = new ArrayList<Player>();
		
		for(int i = 0; i < playerList.size(); i ++) 
		{
			HashMap<String, Integer> test = playerList.get(i).getPlayerHand().getCurrentCard().getAttributes();
			
			if(test.get(category) == findMaxScore(category)) 
			{
				winningPlayer.add(playerList.get(i));
			}
		}
		
		return winningPlayer;
		
	}
	
	public Card getCard(int playerID) //change playerid to position in arraylist
	{
		Player player = playerList.get(playerID);
		Card currentCard = player.getPlayerHand().getCurrentCard();
		return currentCard;
	}
	
	
}
