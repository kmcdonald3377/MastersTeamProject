package commandline;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

public class Round 
{
	private ArrayList<Player> playerList;
	private HashMap<Integer, Integer> valueComparison;
	private Game currentGame;
	
	public Round(ArrayList<Player> playerList) 
	{
		this.playerList = playerList;
	}
	
	
	
	public int[] getPlayerHandSize() 
	{
		HashMap<Integer, Integer> playerHandSizes = new HashMap<Integer, Integer>(); //is this needed?
		int[] handSize = new int[5];

		for(int i = 0; i < playerList.size(); i ++) 
		{
			playerHandSizes.put(playerList.get(i).getPlayerId(), playerList.get(i).getPlayerHand().getNumberOfCards());
		}

		if(playerHandSizes.containsKey(1)) 
		{
			handSize[0] = playerHandSizes.get(1);
		}
		else 
		{
			handSize[0] = 0;
		}

		if(playerHandSizes.containsKey(2)) 
		{
			handSize[1] = playerHandSizes.get(2);
		}
		else 
		{
			handSize[1] = 0;
		}

		if(playerHandSizes.containsKey(3)) 
		{
			handSize[2] = playerHandSizes.get(3);
		}
		else 
		{
			handSize[2] = 0;
		}

		if(playerHandSizes.containsKey(4)) 
		{
			handSize[3] = playerHandSizes.get(4);
		}
		else 
		{
			handSize[3] = 0;
		}

		if(playerHandSizes.containsKey(5)) 
		{
			handSize[4] = playerHandSizes.get(5);
		}
		else 
		{
			handSize[4] = 0;
		}
		
		return handSize;
	}
	
	public String categorySelection() 
	{
		Random randomNumber = new Random();
		int category = randomNumber.nextInt(5);
		
		Card humanPlayerCard = getCard(0);
				
		String categoryKey = humanPlayerCard.getAttributeAtIndex(category);
		return categoryKey;
	}
	
	public HashMap<Integer, Integer> aiCategorySelection(String category, HashMap<Integer, Integer> categoryComparison) 
	{
		category = categorySelection();

		categoryComparison = categoryValues(category);
		
		return categoryComparison;
	}
	
	public String humanCategorySelection(String category)
	{
		while(!category.equalsIgnoreCase("Size") && !category.equalsIgnoreCase("Speed") && !category.equalsIgnoreCase("Range") 
				&& !category.equalsIgnoreCase("Firepower") && !category.equalsIgnoreCase("Cargo")) 
		{
			category = "";
		}

		if(category.equalsIgnoreCase("Size")) 
		{
			category = "Size";
		}
		if(category.equalsIgnoreCase("Speed")) 
		{
			category = "Speed";
		}
		if(category.equalsIgnoreCase("Range")) 
		{
			category = "Range";
		}
		if(category.equalsIgnoreCase("Firepower")) 
		{
			category = "Firepower";
		}
		if(category.equalsIgnoreCase("Cargo")) 
		{
			category = "Cargo";
		}
		
		return category;
	}
	
	public HashMap<Integer, Integer> categoryValues(String category) 
	{
		valueComparison = new HashMap<Integer, Integer>();
		
		for(int i = 0; i < currentGame.getActivePlayers().size(); i ++) 
		{
			valueComparison.put(activePlayers.get(i).getPlayerId(), activePlayers.get(i).getPlayerHand().getCurrentCard().getAttributes().get(category));
		}
		
		return valueComparison;
		
	}
	
	public int findMaxScore(String category) //is failing in here
	{
		HashMap<Integer, Integer> valueComparison = categoryValues(category);
		int max = 0;
		
		for(int i : valueComparison.keySet()) 
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
		HashMap<Integer, Integer> valueComparison = categoryValues(category);
		int max = findMaxScore(category);
		int count = 0;
		
		for(int i : valueComparison.keySet()) 
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
		
		for(int i = 0; i < activePlayers.size(); i ++) 
		{
			HashMap<String, Integer> test = activePlayers.get(i).getPlayerHand().getCurrentCard().getAttributes();
			
			if(test.get(category) == findMaxScore(category)) 
			{
				winningPlayer.add(activePlayers.get(i));
			}
		}
		
		return winningPlayer;
		
	}
	
	public Card getCard(int playerID) //change playerid to position in arraylist
	{
		Player player = activePlayers.get(playerID);
		Card currentCard = null;
		
			currentCard = player.getPlayerHand().getCurrentCard();
		
		return currentCard;
	}
	
	public void computeDraw(ArrayList<Player> winners) 
	{
		for(Player player : activePlayers) 
		{
			Card currentCard = player.getPlayerHand().getCurrentCard(); //identifies current card
			int playerid = player.getPlayerId();
			currentGame.addToCommunalPile(currentCard); //adds current card to communal pile
			for(int i = 0; i < playerList.size(); i ++) 
			{
				if(playerList.get(i).getPlayerId() == playerid) 
				{
					playerList.get(i).getPlayerHand().removeCard(currentCard); //removes current card from each players hand
				}
			}
		}
	}
	
	public void computeWin(ArrayList<Player> winner) 
	{
		winner.get(0).getPlayerHand().addCard(winner.get(0).getPlayerHand().getCurrentCard());
		winner.get(0).getPlayerHand().removeCard(winner.get(0).getPlayerHand().getCurrentCard()); //this will move the current card to the back of the deck

		for(Player player : activePlayers) 
		{
			if(player == winner.get(0)) 
			{

			}
			else 
			{
				winner.get(0).getPlayerHand().addCard(player.getPlayerHand().getCurrentCard()); //will add card to winners hand
				player.getPlayerHand().removeCard(player.getPlayerHand().getCurrentCard()); //will remove card from losers hand
			}
		}

		while(currentGame.getCommunalPile().getNumberOfCards() > 0) 
		{
			winner.get(0).getPlayerHand().addCard(communalPile.getCurrentCard());
			communalPile.removeCard(communalPile.getCurrentCard());
		}
	}
	
	public ArrayList<Integer> setCategoryValues(HashMap<Integer, Integer> categoryComparison) 
	{
		ArrayList<Integer> cardStats = new ArrayList<Integer>();
		int humanHand = 0;
		int ai1Hand = 0;
		int ai2Hand = 0;
		int ai3Hand = 0;
		int ai4Hand = 0;
		
		if(categoryComparison.containsKey(1)) 
		{
			humanHand = categoryComparison.get(1);
		}

		if(categoryComparison.containsKey(2)) 
		{
			ai1Hand = categoryComparison.get(2);
		}

		if(categoryComparison.containsKey(3)) 
		{
			ai2Hand = categoryComparison.get(3);
		}

		if(categoryComparison.containsKey(4)) 
		{
			ai3Hand = categoryComparison.get(4);
		}

		if(categoryComparison.containsKey(5)) 
		{
			ai4Hand = categoryComparison.get(5);
		}
		cardStats.add(humanHand);
		cardStats.add(ai1Hand);
		cardStats.add(ai2Hand);
		cardStats.add(ai3Hand);
		cardStats.add(ai4Hand);
		
		return cardStats;
	}
	
	
}
