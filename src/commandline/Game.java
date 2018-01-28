package commandline;

import java.util.ArrayList;

public class Game 
{
	private int totalRounds, totalDraws, numberOfPlayers;
	private Database database;
	private ArrayList <PileOfCards> playerList;
	private PileOfCards communalPile;
	private PileOfCards deck;
	
	public Game(PileOfCards deck) 
	{
		database = new Database();
		communalPile = new PileOfCards(0);
		this.deck = deck;
		ArrayList <Integer> playerIDs = database.getPlayerId();
		
		deck.shuffle();
		for(int i = 0; i < playerIDs.size(); i++) 
		{
			playerList.add(new PileOfCards(playerIDs.get(i))); //should create an array list of playerhands with each unique player id
		}
		distributeDeck();
		
		numberOfPlayers = playerList.size();
		
		
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
			playerList.get(j).addCard(newDeck.get(i));
			j++;
		}
	}
}
