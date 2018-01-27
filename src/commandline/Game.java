package commandline;

import java.util.ArrayList;

public class Game 
{
	private int totalRounds, totalDraws, numberOfPlayers;
	private DatabaseConnection database;
	private ArrayList <PlayerHand> playerList;
	private CommunalPile communal;
	private Deck deck;
	
	public Game() 
	{
		database = new DatabaseConnection();
		ArrayList <Integer> playerIDs = database.getPlayerId();
		
		for(int i = 0; i < playerIDs.size(); i++) 
		{
			playerList.add(new PlayerHand(playerIDs.get(i))); //should create an array list of playerhands with each unique player id
		}
		
		numberOfPlayers = playerList.size();
		communal = new CommunalPile();
		deck = new Deck();
		deck.shuffleDeck();
		
	}
	
	public int getNumberOfPlayers() 
	{
		return numberOfPlayers;
	}
	
	public void increaseRounds() 
	{
		totalRounds += 1;
	}
	
	public void increaseDraws() 
	{
		totalDraws += 1;
	}
	
	public int getTotalRounds() 
	{
		return totalRounds;
	}
	
	public int getTotalDraws() 
	{
		return totalDraws;
	}
	
	public ArrayList getPlayerList() 
	{
		return playerList;
	}
	
	public void distributeDeck() 
	{
		ArrayList <Card> newDeck = deck.getDeck();
		int blah = newDeck.size();
		blah = blah/numberOfPlayers; //want blah number of cards in each deck
		int j = 0;
		for (int i = 0; i < blah; i ++)
		{
			if(j == 4) 
			{
				j = 0;
			}
			playerList.get(j).addToHand(newDeck.get(i));
			j++;
		}
		
	}
}
