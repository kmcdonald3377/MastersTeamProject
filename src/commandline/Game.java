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
	
	/**
	 * constructor 
	 * 
	 * @param deck - PileOfCards representing the deck during the game
	 * @param username - String of the players entered username
	 */
	public Game(PileOfCards deck, String username) 
	{
		log = new TestLog();
		communalPile = new PileOfCards(null);
		this.deck = deck;
		log.writeInitDeck(deck);
		communalPile = new PileOfCards(null); //0 passed in as no player with an id of 0
		this.deck = deck;
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
		activePlayers = new ArrayList<Player>();
		for(int i = 0; i < playerList.size(); i ++) 
		{
			activePlayers.add(playerList.get(i));
		}
		this.username = username;
	}
	
	// method to get deck
	public PileOfCards getDeck()	{
		return deck;
	}
	
	// method to get matchID
	public int getMatchID() 
	{
		return matchID;
	}
	
	// method returns players current hand size i.e. amount of cards in their deck
	public int[] getPlayerHandSize() 
	{
		HashMap<Integer, Integer> playerHandSizes = new HashMap<Integer, Integer>();
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
	
	// method gets the number of active players remaining
	public ArrayList<Player> getActivePlayers() 
	{
		return activePlayers;
	}
	
	
	// method removes players from the game 
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
	
	
	// method removes players from the active players ArrayList
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
	
	
	// method to return the number of players in game
	public int getNumberOfPlayers() 
	{
		return numberOfPlayers;
	}
	
	
	// method increments the number of rounds played after the conclusion of each round
	public void increaseRounds() 
	{
		totalRounds += 1;
	}
	
	
	// method increments number of draw when round ends in draw
	public void increaseDraws() 
	{
		totalDraws += 1;
	}
	
	
	// method gets total number of rounds played
	public int getTotalRounds() 
	{
		return totalRounds;
	}
	
	// method gets total number of rounds ending in a draw 
	public int getTotalDraws() 
	{
		return totalDraws;
	}
	
	
	// method to get the list of players
	public ArrayList<Player> getPlayerList() 
	{
		return playerList;
	}
	
	
	// method to add a card to the communal pile PileOfCards
	public void addToCommunalPile(Card currentCard) 
	{
		communalPile.addCard(currentCard);
	}
	
	// method to remove cards from the communal pile
	public void removeFromCommunalPile(Card currentCard) 
	{
		communalPile.removeCard(currentCard);
	}
	
	// method which will return the communal pile 
	public PileOfCards getCommunalPile() 
	{
		return communalPile;
	}
	
	// method to distribute the deck between players
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
	
	// method to set which player is the first to choose an attribute
	public Player setFirstChoice() 
	{
		Random randomNumber = new Random();
		int myNumber = randomNumber.nextInt(5);

		firstChoice = activePlayers.get(myNumber);
		return firstChoice;
	}
	
	
	// method to get the name of chosen player 
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
	
	
	// method to return the test log 
	public TestLog gameLog()
	{
		return log;
	}
}
