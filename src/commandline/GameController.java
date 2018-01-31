package commandline;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class GameController 
{
	private final String username;
	private GameView gameV;
	private PileOfCards communalPile;
	private Player firstChoice;
	private Game currentGame;
	private ArrayList <Player> playerList, activePlayers;

	public GameController(PileOfCards starCitizenDeck, String username) 
	{
		gameV = new GameView(this);
		communalPile = new PileOfCards(null); //0 passed in as no player with an id of 0
		currentGame = new Game(starCitizenDeck);
		playerList = currentGame.getPlayerList();
		activePlayers = new ArrayList<Player>();
		for(int i = 0; i < playerList.size(); i ++) 
		{
			activePlayers.add(playerList.get(i));
		}
		this.username = username;
	}

	public void startGame() 
	{
		gameV.gameIntroduction();
		setFirstChoice();
		runGame();
	}

	public void runGame() 
	{		
		while(isValid()) //will make loop for as long as player has cards or 
		{
			Round2 currentRound = new Round2(playerList);
			currentRound.setActivePlayers(activePlayers);
			displayCard(currentRound); //displays current card to user

			HashMap<Integer, Integer> categoryComparison = new HashMap<Integer, Integer>();
			String category = "";

			if(firstChoice.getPlayerId() != 1) 
			{
				category = currentRound.categorySelection();

				categoryComparison = currentRound.categoryValues(category);


				gameV.aiSelectCategory(getPlayerName(firstChoice.getPlayerId()), category);
				gameV.userInput();
			}
			else if(firstChoice.getPlayerId() == 1)
			{
				gameV.userSelectCategory();
				category = gameV.userInput();
				categoryComparison = currentRound.categoryValues(category);
			}		
			
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
			
			gameV.showStats(username, humanHand, ai1Hand, ai2Hand, ai3Hand, ai4Hand);			
			
			ArrayList<Player> winningPlayer = currentRound.findWinner(category);

			if(currentRound.isWinner(category)) //if there is an outright winner
			{
				computeWin(winningPlayer);
				winningPlayer.get(0).increaseRoundsWon();
			}
			else //else if it is a draw then show draw message
			{
				computeDraw(winningPlayer);
				currentGame.increaseDraws();

				for(int i = 0; i < winningPlayer.size(); i++) 
				{
					winningPlayer.get(i).increaseRoundsDrawn();
				}
				currentGame.increaseDraws();
			}
			
			currentGame.increaseRounds();
			
			
			for(int i = 0; i < playerList.size(); i ++) 
			{
				if(playerList.get(i).getPlayerHand().getNumberOfCards() == 0) 
				{
					gameV.removedPlayers(getPlayerName(playerList.get(i).getPlayerId()));
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
			
			int id = firstChoice.getPlayerId();
			int max = 1;
			int min = 1;
			
			for(int i = 0; i < activePlayers.size(); i++) 
			{
				if(activePlayers.get(i).getPlayerId() > max) 
				{
					max = activePlayers.get(i).getPlayerId();
				}
				
				if(activePlayers.get(i).getPlayerId() < min) 
				{
					min = activePlayers.get(i).getPlayerId();
				}
			}
			
			int index = 0;
			boolean test = false;
			

			while(!test) 
			{
				if(id == max) 
				{
					id = min;
					index = 0;
					test = true;
				}
				else 
				{
					id++;
					for(int i = 0; i < activePlayers.size(); i++) 
					{
						if(activePlayers.get(i).getPlayerId() == id) 
						{
							index = i;
							test = true;
						}
						
					}
				}
			}

			
			
			firstChoice = activePlayers.get(index);
			
		}
		
		
		if(playerList.get(0).getPlayerId() == 1) 
		{
			gameV.humanWon();
		}
		else 
		{
			gameV.humanLoses(getPlayerName(playerList.get(0).getPlayerId()));
		}
		
	}

	private void displayCard(Round2 current) 
	{
		Card currentCard = current.getCard(0); //0 as only currently showing the human players card
		HashMap<Integer, Integer> playerHandSizes = new HashMap<Integer, Integer>();
		int humanHand;
		int ai1Hand;
		int ai2Hand;
		int ai3Hand;
		int ai4Hand;
		
		for(int i = 0; i < playerList.size(); i ++) 
		{
			playerHandSizes.put(playerList.get(i).getPlayerId(), playerList.get(i).getPlayerHand().getNumberOfCards());
		}
		
		if(playerHandSizes.containsKey(1)) 
		{
			humanHand = playerHandSizes.get(1);
		}
		else 
		{
			humanHand = 0;
		}
		
		if(playerHandSizes.containsKey(2)) 
		{
			ai1Hand = playerHandSizes.get(2);
		}
		else 
		{
			ai1Hand = 0;
		}
		
		if(playerHandSizes.containsKey(3)) 
		{
			ai2Hand = playerHandSizes.get(3);
		}
		else 
		{
			ai2Hand = 0;
		}
		
		if(playerHandSizes.containsKey(4)) 
		{
			ai3Hand = playerHandSizes.get(4);
		}
		else 
		{
			ai3Hand = 0;
		}
		
		if(playerHandSizes.containsKey(5)) 
		{
			ai4Hand = playerHandSizes.get(5);
		}
		else 
		{
			ai4Hand = 0;
		}
		
		if(playerList.get(0).getPlayerHand().getNumberOfCards() != 0) 
		{
			gameV.showCard(humanHand, ai1Hand, ai2Hand, ai3Hand, ai4Hand, currentCard);
		}
		else 
		{
			gameV.showCardNumbers(humanHand, ai1Hand, ai2Hand, ai3Hand, ai4Hand);
		}
		
		

	}

	private boolean isValid() 
	{
		if(activePlayers.size() == 1) 
		{
			return false;
		}
		else 
		{
			return true;
		}
	}

	public void computeDraw(ArrayList<Player> winners) 
	{
		int i = 0;
		for(Player player : activePlayers) //only looping for active players = means that when do playerList.get then will be missing some out
		{
			Card currentCard = player.getPlayerHand().getCurrentCard(); //identifies current card
			communalPile.addCard(currentCard); //adds current card to communal pile
			playerList.get(i).getPlayerHand().removeCard(currentCard); //removes current card from each players hand
			i++;
		}
		gameV.showDraw(getPlayerName(winners.get(0).getPlayerId()), getPlayerName(winners.get(1).getPlayerId())); //need to feed in the two players who drew
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

		while(communalPile.getNumberOfCards() > 0) 
		{
			winner.get(0).getPlayerHand().addCard(communalPile.getCurrentCard());
			communalPile.removeCard(communalPile.getCurrentCard());
		}

		gameV.showWinner(getPlayerName(winner.get(0).getPlayerId()));
	}

	private void setFirstChoice() 
	{
		Random randomNumber = new Random();
		int myNumber = randomNumber.nextInt(5);

		firstChoice = activePlayers.get(myNumber);
	}

	private String getPlayerName(int playerID) 
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

}
