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
	
	public ArrayList aiCategorySelection() 
	{
		Random randomNumber = new Random();
		int category = randomNumber.nextInt(5);
		
		Card humanPlayerCard = getCard(0);
		Card ai1Card = getCard(1);
		Card ai2Card = getCard(2);
		Card ai3Card = getCard(3);
		Card ai4Card = getCard(4);
		
		valueComparison = new ArrayList();
		valueComparison.add(humanPlayerCard.getValueAtIndex(category));
		valueComparison.add(ai1Card.getValueAtIndex(category));
		valueComparison.add(ai2Card.getValueAtIndex(category));
		valueComparison.add(ai3Card.getValueAtIndex(category));
		valueComparison.add(ai4Card.getValueAtIndex(category));
		
		return valueComparison;
	}
	
	
	private Integer chooseValue(int randomNumber, Card card, int cardIndex) {
				
		int index = 0;
		Integer valueToReturn = null; 
		Iterator  iterator = card.getAttributes().entrySet().iterator();
		
		while(iterator.hasNext()) {
			
			if(index == cardIndex) {
				Map.Entry mapEntry = (Map.Entry) iterator.next();
				
				valueToReturn = (Integer) mapEntry.getValue();
				break;
			}			
		}
		
		return valueToReturn;
		
	}
	
	
	public ArrayList humanCategorySelection(int category) 
	{
		Card humanPlayerCard = getCard(0);
		HashMap<String, Integer> humanAttributes = humanPlayerCard.getAttributes();
		humanAttributes.get(category);
		
		Card ai1Card = getCard(1);
		HashMap<String, Integer> ai1Attributes = ai1Card.getAttributes();
		ai1Attributes.get(category);
		
		Card ai2Card = getCard(2);
		HashMap<String, Integer> ai2Attributes = ai1Card.getAttributes();
		ai2Attributes.get(category);
		
		Card ai3Card = getCard(3);
		HashMap<String, Integer> ai3Attributes = ai1Card.getAttributes();
		ai3Attributes.get(category);
		
		Card ai4Card = getCard(4);
		HashMap<String, Integer> ai4Attributes = ai1Card.getAttributes();
		ai4Attributes.get(category);
		
		
		
		valueComparison = new ArrayList();
		valueComparison.add();
		valueComparison.add();
		valueComparison.add();
		valueComparison.add();
		valueComparison.add();
		
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
	
	public Card getCard(int playerID) //change playerid to position in arraylist
	{
		Player player = playerList.get(playerID);
		Card currentCard = player.getPlayerHand().getCurrentCard();
		return currentCard;
	}
}
