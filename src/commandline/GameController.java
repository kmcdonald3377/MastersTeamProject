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
			
			//decide if human or ai selecting the category - method in round for each
			HashMap<Integer, Integer> categoryComparison = new HashMap<Integer, Integer>();
			String category = "";
			selectCategory(currentRound, category, categoryComparison); //this will need to return to update hashmap and to update category
			
			//display stats
			ArrayList<Integer> cardStats = currentRound.setCategoryValues(categoryComparison);
			if(playerList.get(0).getPlayerHand().getNumberOfCards() != 0) 
			{
				gameV.showStats(username, cardStats.get(0), cardStats.get(1), cardStats.get(2), cardStats.get(3), cardStats.get(4));	//this stays here		
			}

			//decide on round winner
			ArrayList<Player> winningPlayer = currentRound.findWinner(category);

			if(currentRound.isWinner(category)) //if there is an outright winner
			{
				displayRoundWin(winningPlayer);
			}
			else //else if it is a draw then show draw message
			{
				displayRoundDraw(winningPlayer);
			}
			currentGame.increaseRounds();
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
		for(int i = 0; i < winners.size(); i++) 
		{
			winners.get(i).increaseRoundsDrawn();
		}
		currentGame.increaseDraws();
		
		if(playerList.get(0).getPlayerHand().getNumberOfCards() != 0) 
		{
			gameV.showDraw(getPlayerName(winners.get(0).getPlayerId()), getPlayerName(winners.get(1).getPlayerId())); //need to feed in the two players who drew
		}
	}

	private void displayRoundWin(ArrayList<Player> winner) 
	{
		winner.get(0).increaseRoundsWon();
		winner.get(0).getPlayerId();
		
		for(int i = 0; i < activePlayers.size(); i++) 
		{
			if(activePlayers.get(i).getPlayerId() == winner.get(0).getPlayerId()) 
			{
				firstChoice = activePlayers.get(i);
			}
		}
		
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
