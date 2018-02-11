package commandline;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

/**
 * Class representing a round in game. 
 */
public class Round 
{
	private ArrayList<Player> playerList, activePlayers;
	private HashMap<Integer, Integer> valueComparison;
	private Game currentGame;
	private int matchID;
	private PileOfCards communalPile;
	
	/**
	 * method constructs a round
	 * 
	 * @param currentGame - Game object of current game 
	 * @param playerList - ArrayList list of players in game 
	 * @param activePlayers - Arraylist number of active players in game 
	 */
	public Round(Game currentGame, ArrayList<Player> playerList, ArrayList<Player> activePlayers) 
	{
		this.playerList = playerList;
		this.activePlayers = activePlayers;
		this.currentGame = currentGame;
		communalPile = currentGame.getCommunalPile();
		matchID = currentGame.getMatchID();
	}
	
	/**
	 * method returns match number 
	 * 
	 * @return matchID - int match number 
	 */
	public int getMatchID() 
	{
		return matchID;
	}
	
	/**
	 * method returns selected category through random integer selection
	 * 
	 * @return categoryKey - string to get category at chosen index 
	 */
	public String categorySelection() 
	{
		Random randomNumber = new Random();
		int category = randomNumber.nextInt(5);
		
		Card humanPlayerCard = getCard(0);
				
		String categoryKey = humanPlayerCard.getAttributeAtIndex(category);
		return categoryKey;
	}
	
	public String maxCategory(PileOfCards hand)
	{
		int index = 0;
		Card playerCard = hand.getCurrentCard();
		int max = playerCard.getValue1();
		if (max<playerCard.getValue2())
		{
		max=playerCard.getValue2();
		index=1;
		}
		if (max<playerCard.getValue3())
		{
		max=playerCard.getValue3();
		index=2;
		}
		if (max<playerCard.getValue4())
		{
		max=playerCard.getValue4();
		index=3;
		}
		if (max<playerCard.getValue5())
		{
		max=playerCard.getValue5();
		index=4;
		}
		String categoryKey = playerCard.getAttributeAtIndex(index);
		return categoryKey;
	}
	
	/**
	 * method to return category selected by human 
	 * 
	 * @param category - string of category selected by human
	 * @return
	 */
	public String humanCategorySelection(String category)
	{
		if(!category.equalsIgnoreCase("Size") && !category.equalsIgnoreCase("Speed") && !category.equalsIgnoreCase("Range") 
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
	
	/**
	 * method to get categories and corresponding values 
	 * 
	 * @param category - string of category selected 
	 * @return - valueComparison HashMap of attributes and corresponding values 
	 */
	public HashMap<Integer, Integer> categoryValues(String category) 
	{
		valueComparison = new HashMap<Integer, Integer>();
		
		for(int i = 0; i < currentGame.getActivePlayers().size(); i ++) 
		{
			valueComparison.put(activePlayers.get(i).getPlayerId(), activePlayers.get(i).getPlayerHand().getCurrentCard().getAttributes().get(category));
		}
		
		return valueComparison;
		
	}
	
	/**
	 * method finds max score by comparing values 
	 * 
	 * @param category - string of category selected 
	 * @return max - int of max value after comparison
	 */
	public int findMaxScore(String category)
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
	
	/**
	 * method returns winner of round 
	 * 
	 * @param category - string of selected category 
	 * @return boolean to indicate winner 
	 */
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
	
	/**
	 * method returns winning player
	 * 
	 * @param category - string of selected category 
	 * @return winningPlayer - ArrayList representing winningPlayer 
	 */
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
	
	/**
	 * method returns current card of player 
	 * 
	 * @param playerPosition - integer representing players position of active player
	 * @return currentCard - Card of activeplayer at playerPosition
	 */
	public Card getCard(int playerPosition)
	{
		Player player = activePlayers.get(playerPosition);
		Card currentCard = null;
		
			currentCard = player.getPlayerHand().getCurrentCard();
		
		return currentCard;
	}
	
	/**
	 * method to compute if round ended in draw 
	 * 
	 * @param winners - ArrayList of players representing winning players who have drawn
	 */
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
	
	/**
	 * method compute if there is a round win 
	 * 
	 * @param winner - ArrayList of players 
	 */
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

		while(currentGame.getCommunalPile().getNumberOfCards() > 0) //will add cards to winner from communal pile
		{
			winner.get(0).getPlayerHand().addCard(communalPile.getCurrentCard());
			communalPile.removeCard(communalPile.getCurrentCard());
		}
	}
	
	/**
	 * method sets category values 
	 * @param categoryComparison - HashMap with categorys and corresponding values 
	 * @return
	 */
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
