package commandline;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;

public class Game 
{
	private int totalRounds, totalDraws, numberOfPlayers;
//	private Database database;
	private ArrayList <Player> playerList, activePlayers;
	private LinkedList <Round> roundList;
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
	public Game(PileOfCards deck, String username, int numberOfPlayers) 
	{
		this.numberOfPlayers = numberOfPlayers;
		log = new TestLog();
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
		roundList = new LinkedList<Round>();
	}
	

  // method to get deck
	public PileOfCards getDeck()	
	{
		return deck;
	}
	
	public LinkedList<Round> getRoundList()
	{
		return roundList;
	}
	
	public void addToRoundList(Round currentRound) 
	{
		roundList.add(currentRound);
	}
	
	// method to get matchID
	public int getMatchID() 
	{
		return matchID;
	}
	
	public void incrementMatchID() 
	{
		matchID++;
	}
	
	// method returns players current hand size i.e. amount of cards in their deck
	public int[] getPlayerHandSize() 
	{
		HashMap<Integer, Integer> playerHandSizes = new HashMap<Integer, Integer>();
		int[] handSize = new int[numberOfPlayers];

		for(int i = 0; i < playerList.size(); i ++) 
		{
			playerHandSizes.put(playerList.get(i).getPlayerID(), playerList.get(i).getPlayerHand().getNumberOfCards());
			
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
					int playerid = playerList.get(i).getPlayerID();
					if (activePlayers.get(j).getPlayerID() == playerid) 
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
	public boolean increaseRounds()
	{
		totalRounds += 1;
		return true;
	}
	
	// method increments number of draw when round ends in draw
	public boolean increaseDraws() 

	{
		totalDraws += 1;
		return true;
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
		
		int j = 0;
		for (int i = 0; i < newDeck.size(); i ++)
		{
			if(j == playerList.size()) 
			{
				j = 0;
			}
			PileOfCards playerHand = playerList.get(j).getPlayerHand();
			playerHand.addCard(newDeck.get(i));
			j++;
		}
		
		for (int i = 0; i < numberOfPlayers; i++)
		{
			log.writeHand(i, playerList.get(i).getPlayerHand());
		}
		for (int i = 0; i < numberOfPlayers; i++)
		{
			log.writeCardsIP(i, playerList.get(i).getPlayerHand().getCurrentCard());
		}
	}
	
	// method to set which player is the first to choose an attribute
	public Player setFirstChoice() 
	{
		Random randomNumber = new Random();
		int myNumber = randomNumber.nextInt(numberOfPlayers);

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
