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
	private TestLog log;

	public GameController(PileOfCards starCitizenDeck, String username) 
	{
		gameV = new GameView(this);
		currentGame = new Game(starCitizenDeck, username);
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
			activePlayers = currentGame.getActivePlayers();
			Round currentRound = new Round(currentGame, playerList, activePlayers);
			displayCardHandDetails(currentRound);
			String category = "";
			HashMap<Integer, Integer> categoryComparison = new HashMap<Integer, Integer>();
			category = selectCategory(currentRound, category);
			categoryComparison = makeCategoryComparison(currentRound, category, categoryComparison); //this will need to return to update hashmap and to update category
			displayStats(currentRound, categoryComparison);
			decideRoundWinners(currentRound, category);			
			currentGame.increaseRounds();
			ArrayList<Player> removePlayers = currentGame.playersToBeRemoved();
			for(int i = 0; i < removePlayers.size(); i++) 
			{
				if(playerList.get(0).getPlayerHand().getNumberOfCards() != 0) 
				{
					gameV.removedPlayers(currentGame.getPlayerName(removePlayers.get(i).getPlayerId())); //this should be in gamecontroller
				}
			}
			
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
	
	private void displayCardHandDetails(Round currentRound) 
	{
		int[] handSize = currentGame.getPlayerHandSize();
		Card currentCard = currentRound.getCard(0); //0 as only currently showing the human players card
		if(playerList.get(0).getPlayerHand().getNumberOfCards() != 0) 
		{
			gameV.showCard(handSize[0], handSize[1], handSize[2], handSize[3], handSize[4], currentCard);
		}
	}

	private String selectCategory(Round currentRound, String category) 
	{
		if(firstChoice.getPlayerId() != 1) 
		{
			category = currentRound.categorySelection();
			if(playerList.get(0).getPlayerHand().getNumberOfCards() != 0) //stays here
			{
				gameV.aiSelectCategory(currentGame.getPlayerName(firstChoice.getPlayerId()), category);
				gameV.userInput();
			}
		}
		else
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
		}	
		return category;
	}
	
	private HashMap<Integer, Integer> makeCategoryComparison(Round currentRound, String category, HashMap<Integer, Integer> categoryComparison) 
	{
		categoryComparison = currentRound.categoryValues(category);
		return categoryComparison;
	}
	
	private void displayStats(Round currentRound, HashMap<Integer, Integer> categoryComparison) 
	{
		ArrayList<Integer> cardStats = currentRound.setCategoryValues(categoryComparison);
		if(playerList.get(0).getPlayerHand().getNumberOfCards() != 0) 
		{
			gameV.showStats(username, cardStats.get(0), cardStats.get(1), cardStats.get(2), cardStats.get(3), cardStats.get(4));	//this stays here		
		}
	}
	
	private void decideRoundWinners(Round currentRound, String category) 
	{
		ArrayList<Player> winningPlayer = currentRound.findWinner(category);

		if(currentRound.isWinner(category)) //if there is an outright winner
		{
			displayRoundWin(currentRound, winningPlayer);
		}
		else //else if it is a draw then show draw message
		{
			displayRoundDraw(currentRound, winningPlayer);
		}
	}
	
	private void displayRoundDraw(Round currentRound, ArrayList<Player> winners) 
	{
		currentRound.computeDraw(winners);
		for(int i = 0; i < winners.size(); i++) 
		{
			winners.get(i).increaseRoundsDrawn();
		}
		currentGame.increaseDraws();
		
		if(playerList.get(0).getPlayerHand().getNumberOfCards() != 0) 
		{
			gameV.showDraw(currentGame.getPlayerName(winners.get(0).getPlayerId()), currentGame.getPlayerName(winners.get(1).getPlayerId())); //need to feed in the two players who drew
		}
	}

	private void displayRoundWin(Round currentRound, ArrayList<Player> winner) 
	{
		currentRound.computeWin(winner);
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
			gameV.showWinner(currentGame.getPlayerName(winner.get(0).getPlayerId()));
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
			gameV.humanLoses(currentGame.getPlayerName(activePlayers.get(0).getPlayerId()));
		}
	}
}
