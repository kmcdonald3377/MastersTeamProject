package commandline;

import java.util.ArrayList;

public class Game 
{
	private int totalRounds, totalDraws, numberOfPlayers;
//	private Database database;
	private ArrayList <Player> playerList;
	private PileOfCards communalPile;
	private PileOfCards deck;
	private TestLog log;
	
	public Game(PileOfCards deck) 
	{
		log = new TestLog();
		//database = new Database();
		communalPile = new PileOfCards(null);
		this.deck = deck;
		log.writeInitDeck(deck);
		//ArrayList <Integer> playerIDs = database.getPlayerId();
		ArrayList <Integer> playerIDs = new ArrayList(); playerIDs.add(1); playerIDs.add(2); playerIDs.add(3); playerIDs.add(4); playerIDs.add(5);
		playerList = new ArrayList<Player>();
		this.deck.shuffle();
		log.writeShuffledDeck(deck);
		
		for(Integer id : playerIDs) 
		{
			Player player = new Player();
			player.setPlayerId(id);
			playerList.add(player); //should create an array list of playerhands with each unique player id
		}
		numberOfPlayers = playerList.size();
		distributeDeck();
		
		
		
		
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
	
	public ArrayList<Player> getPlayerList() 
	{
		return playerList;
	}
	
	public void distributeDeck() 
	{
		ArrayList <Card> newDeck = deck.getDeck();
		int size = newDeck.size();
		size = size/numberOfPlayers; //want number of cards in each deck
		
		int j = 0;
		for (int i = 0; i < newDeck.size(); i ++)
		{
			if(j == 5) 
			{
				j = 0;
			}
			PileOfCards playerHand = playerList.get(j).getPlayerHand();
			playerHand.addCard(newDeck.get(i));
			j++;
			log.writeHand(j, playerHand);
			log.writeFile();
		}
	}
}
