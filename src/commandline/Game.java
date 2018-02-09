package commandline;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Game 
{
	private int totalRounds, totalDraws, numberOfPlayers;
//	private Database database;
	private ArrayList <Player> playerList, activePlayers;
	private PileOfCards communalPile;
	private PileOfCards deck;
	private TestLog log;
	private Player firstChoice;
	private String username;
	private int matchID;
	
	public Game(PileOfCards deck, String username, int numberOfPlayers) 
	{
		this.numberOfPlayers = numberOfPlayers;
		log = new TestLog();
		//database = new Database();
		communalPile = new PileOfCards(null);
		this.deck = deck;
		log.writeInitDeck(deck);
		//ArrayList <Integer> playerIDs = database.getPlayerId();

//		database = new Database();
//		matchID = database.getMaxMatchID();
//		incrementMatchID();
		communalPile = new PileOfCards(null); //0 passed in as no player with an id of 0
		this.deck = deck;
//		ArrayList <Integer> playerIDs = database.getPlayerId(numberOfPlayers);
		ArrayList <Integer> playerIDs = new ArrayList(); for(int i = 1; i <= this.numberOfPlayers; i++) {playerIDs.add(i);}

		
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
		activePlayers = new ArrayList<Player>();
		for(int i = 0; i < playerList.size(); i ++) 
		{
			activePlayers.add(playerList.get(i));
		}
		this.username = username;
		this.matchID = 0;
	}
	
	public PileOfCards getDeck()	{
		return deck;
	}
	
	public int getMatchID() 
	{
		return matchID;
	}
	
	public void incrementMatchID() 
	{
		matchID++;
	}
	
	public int[] getPlayerHandSize() 
	{
		HashMap<Integer, Integer> playerHandSizes = new HashMap<Integer, Integer>();
		int[] handSize = new int[numberOfPlayers];

		for(int i = 0; i < playerList.size(); i ++) 
		{
			playerHandSizes.put(playerList.get(i).getPlayerId(), playerList.get(i).getPlayerHand().getNumberOfCards());
			
			if(playerHandSizes.containsKey(1)) 
			{
				handSize[i] = playerHandSizes.get(1);
			}
			else 
			{
				handSize[i] = 0;
			}
		}
		
		return handSize;
	}
	
	public ArrayList<Player> getActivePlayers() 
	{
		return activePlayers;
	}
	
	public ArrayList<Player> playersToBeRemoved()
	{
		ArrayList<Player> removePlayers = new ArrayList<Player>();
		for(int i = 0; i < playerList.size(); i ++) 
		{
			if(playerList.get(i).getPlayerHand().getNumberOfCards() == 0) 
			{
				removePlayers.add(playerList.get(i));
			}
		}
		
		return removePlayers;
	}
	
	public ArrayList<Player> removeFromActivePlayers()
	{
		for(int i = 0; i < playerList.size(); i ++) 
		{
			if(playerList.get(i).getPlayerHand().getNumberOfCards() == 0) 
			{
				for(int j = 0; j < activePlayers.size(); j ++) 
				{
					int playerid = playerList.get(i).getPlayerId();
					if (activePlayers.get(j).getPlayerId() == playerid) 
					{
						activePlayers.remove(j);
					}
				}
			}
		}
		return activePlayers;
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
	
	public void addToCommunalPile(Card currentCard) 
	{
		communalPile.addCard(currentCard);
	}
	
	public void removeFromCommunalPile(Card currentCard) 
	{
		communalPile.removeCard(currentCard);
	}
	
	public PileOfCards getCommunalPile() 
	{
		return communalPile;
	}
	
	public void distributeDeck() 
	{
		ArrayList <Card> newDeck = deck.getDeck();
		
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
		}
		
		for (int i = 0; i < 5; i++)
		{
			log.writeHand(i+1, playerList.get(i).getPlayerHand());
		}
		for (int i = 0; i <5; i++)
		{
			log.writeCardsIP(i, playerList.get(i).getPlayerHand().getCurrentCard());
		}
	}
	
	public Player setFirstChoice() 
	{
		Random randomNumber = new Random();
		int myNumber = randomNumber.nextInt(5);

		firstChoice = activePlayers.get(myNumber);
		return firstChoice;
	}
	
	public String getPlayerName(int playerID) 
	{
		String playerName = "";
		if(playerID == 1) 
		{
			playerName = username;
		}
		else if(playerID == 2) 
		{
			playerName = "AI Player 1";
		}
		else if(playerID == 3) 
		{
			playerName = "AI Player 2";
		}
		else if(playerID == 4) 
		{
			playerName = "AI Player 3";
		}
		else if(playerID == 5) 
		{
			playerName = "AI Player 4";
		}

		return playerName;
	}
	
	public TestLog gameLog()
	{
		return log;
	}
}
