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
		valueComparison = new ArrayList<Integer>();
		
		for(int i = 0; i < playerList.size(); i ++) 
		{
			valueComparison.add(playerList.get(i).getPlayerHand().getCurrentCard().getAttributes().get(category));
		}
		
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
		Card currentCard = null;
		
			currentCard = player.getPlayerHand().getCurrentCard();
		
		return currentCard;
	}
	
	
}
