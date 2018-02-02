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
		currentGame = new Game(starCitizenDeck);
		communalPile = currentGame.getCommunalPile();
		playerList = currentGame.getPlayerList();
		activePlayers = currentGame.getActivePlayers();
		this.username = username;
	}

	public void startGame() 
	{
		gameV.gameIntroduction();
		firstChoice = currentGame.setFirstChoice(); //reference to game
		runGame();
	}

	public void runGame() 
	{		
		while(isValid()) //will make loop for as long as player has cards or 
		{
			Round currentRound = new Round(playerList);
			activePlayers = currentGame.getActivePlayers();
			int[] handSize = currentRound.getPlayerHandSize();
			Card currentCard = currentRound.getCard(0); //0 as only currently showing the human players card
			if(playerList.get(0).getPlayerHand().getNumberOfCards() != 0) 
			{
				gameV.showCard(handSize[0], handSize[1], handSize[2], handSize[3], handSize[4], currentCard);
			}

			//at this point have displayed the users current card and shown the size of the hands of all players
			
			
			//decide if human or ai selecting the category - method in round for each
			HashMap<Integer, Integer> categoryComparison = new HashMap<Integer, Integer>();
			String category = "";
			selectCategory(currentRound, category, categoryComparison); //this will need to return to update hashmap and to update category
			
			//display stats 
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

			if(playerList.get(0).getPlayerHand().getNumberOfCards() != 0) 
			{
				gameV.showStats(username, humanHand, ai1Hand, ai2Hand, ai3Hand, ai4Hand);	//this stays here		
			}

			//decide on round winner
			ArrayList<Player> winningPlayer = currentRound.findWinner(category);

			if(currentRound.isWinner(category)) //if there is an outright winner
			{
				displayRoundWin(winningPlayer);
				winningPlayer.get(0).increaseRoundsWon();
				winningPlayer.get(0).getPlayerId();
				
				for(int i = 0; i < activePlayers.size(); i++) 
				{
					if(activePlayers.get(i).getPlayerId() == winningPlayer.get(0).getPlayerId()) 
					{
						firstChoice = activePlayers.get(i);
					}
				}
			}
			else //else if it is a draw then show draw message
			{
				displayRoundDraw(winningPlayer);
				currentGame.increaseDraws();

				for(int i = 0; i < winningPlayer.size(); i++) 
				{
					winningPlayer.get(i).increaseRoundsDrawn();
				}
				currentGame.increaseDraws();
			}

			//after win or loss of round is displayed then increase the number of rounds played
			currentGame.increaseRounds();
			
			//remove any players which are out of cards from the active players list - should be in another class/method
			activePlayers = currentGame.removeFromActivePlayers(); //this method current has gameV line in it - need this in here
		}
		displayGameResult();
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

	private void displayRoundDraw(ArrayList<Player> winners) 
	{
		if(playerList.get(0).getPlayerHand().getNumberOfCards() != 0) 
		{
			gameV.showDraw(getPlayerName(winners.get(0).getPlayerId()), getPlayerName(winners.get(1).getPlayerId())); //need to feed in the two players who drew
		}
	}

	private void displayRoundWin(ArrayList<Player> winner) 
	{
		if(playerList.get(0).getPlayerHand().getNumberOfCards() != 0) 
		{
		gameV.showWinner(getPlayerName(winner.get(0).getPlayerId()));
		}
	}
	
	private void displayGameResult() 
	{
		if(activePlayers.get(0).getPlayerId() == 1) 
		{
			gameV.humanWon();
		}
		else 
		{
			gameV.humanLoses(getPlayerName(activePlayers.get(0).getPlayerId()));
		}
	}
	
	private void selectCategory(Round currentRound, String category, HashMap<Integer, Integer> categoryComparison) 
	{
		if(firstChoice.getPlayerId() != 1) 
		{
			categoryComparison = currentRound.aiCategorySelection(category, categoryComparison);
			
			if(playerList.get(0).getPlayerHand().getNumberOfCards() != 0) //stays here
			{
				gameV.aiSelectCategory(currentGame.getPlayerName(firstChoice.getPlayerId()), category);
				gameV.userInput();
			}
		}
		else if(firstChoice.getPlayerId() == 1)
		{
			gameV.userSelectCategory();
			category = gameV.userInput();
			category = currentRound.humanCategorySelection(category);
			while(category.equals("")) 
			{
				gameV.errorMessage();
				category = gameV.userInput();
				category = currentRound.humanCategorySelection(category);
			}
			
			categoryComparison = currentRound.categoryValues(category);
		}	
	}
}
