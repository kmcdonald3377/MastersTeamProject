package commandline;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;
import java.util.InputMismatchException;

public class GameController 
{
	private final int PLAYERS = 5;
	private final String username;
	private int difficulty;
	private GameView gameV;
	private PileOfCards communalPile;
	private Player firstChoice;
	private Game currentGame;
	private ArrayList <Player> playerList, activePlayers;
	private TestLog glog;
	private boolean _log;

	public GameController(PileOfCards starCitizenDeck, String username, boolean log) 
	{
		gameV = new GameView(this);
		currentGame = new Game(starCitizenDeck, username, PLAYERS);
		communalPile = currentGame.getCommunalPile();
		playerList = currentGame.getPlayerList();
		activePlayers = currentGame.getActivePlayers();
		this.username = username;
		_log = log;
		difficulty = 0;
	}

	public void startGame() 
	{
		gameV.askDifficulty();
		boolean isValid = false;
		char c;
		do 
		{
			String diff = gameV.userInput();
			c = diff.toCharArray()[0];
			if(Character.isDigit(c)) 
			{
				difficulty = Integer.parseInt(""+c);
				
				if(difficulty != 1 && difficulty != 2 && difficulty != 3) 
				{
					gameV.errorMessage();
				}
				else 
				{
					isValid = true;
				}
			}
			else 
			{
				gameV.errorMessage();
			}
		}
		while(!isValid);
		
		
		difficulty = Integer.parseInt(""+c);
		
		gameV.gameIntroduction();
		firstChoice = currentGame.setFirstChoice(); //reference to game
		runGame();
	}

	public void runGame() 
	{		
		while(isValid()) //will make loop for as long as player has cards or 
		{
			glog = currentGame.gameLog();
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
					gameV.removedPlayers(currentGame.getPlayerName(removePlayers.get(i).getPlayerID())); //this should be in gamecontroller
				}
			}
			activePlayers = currentGame.removeFromActivePlayers(); //this method current has gameV line in it - need this in here
			this.writeHand();
			this.cardsInPlay();
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
		if(playerList.get(0).getPlayerHand().getNumberOfCards() != 0) 
		{
			Card currentCard = currentRound.getCard(0); //0 as only currently showing the human players card
			gameV.showCard(handSize[0], handSize[1], handSize[2], handSize[3], handSize[4], currentCard);
		}
	}

	private void cardsInPlay()
	{
		for (int i = 0; i < activePlayers.size(); i++) //should this be active players or all players
		{
				glog.writeCardsIP(i, activePlayers.get(i).getPlayerHand().getCurrentCard());
		}
	}

	private void writeHand()
	{
		for (int i = 0; i < playerList.size(); i++)
		{
			glog.writeHand(i, playerList.get(i).getPlayerHand());
		}
	}

	private String selectCategory(Round currentRound, String category) 
	{
		if(firstChoice.getPlayerID() != 1) 
		{
			category = currentRound.categorySelection(difficulty, firstChoice.getPlayerID());
			
			if(playerList.get(0).getPlayerHand().getNumberOfCards() != 0) //display to the user the choice only if the user is still in the game
			{
				gameV.aiSelectCategory(currentGame.getPlayerName(firstChoice.getPlayerID()), category);
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
		glog.writeCategory(firstChoice.getPlayerID(), category);
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
			String s = ("\r\n" + username + " has: " + cardStats.get(0)
			+ " \r\nAI Player 1 has: " + cardStats.get(1)
			+ " \r\nAI Player 2 has: " + cardStats.get(2)
			+ " \r\nAI Player 3 has: " + cardStats.get(3)
			+ " \r\nAI Player 4 has: " + cardStats.get(4));
			glog.writeStats(s);
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
		glog.writeCommunalPile(communalPile);
		for(int i = 0; i < winners.size(); i++) 
		{
			winners.get(i).increaseRoundsDrawn();
		}
		currentGame.increaseDraws();

		if(playerList.get(0).getPlayerHand().getNumberOfCards() != 0) 
		{
			gameV.showDraw(currentGame.getPlayerName(winners.get(0).getPlayerID()), currentGame.getPlayerName(winners.get(1).getPlayerID())); //need to feed in the two players who drew
		}
	}

	private void displayRoundWin(Round currentRound, ArrayList<Player> winner) 
	{
		glog.writeCommunalPileEmpty();
		currentRound.computeWin(winner);
		winner.get(0).increaseRoundsWon();
		winner.get(0).getPlayerID();
		for(int i = 0; i < activePlayers.size(); i++) 
		{
			if(activePlayers.get(i).getPlayerID() == winner.get(0).getPlayerID()) 
			{
				firstChoice = activePlayers.get(i);
			}
		}

		if(playerList.get(0).getPlayerHand().getNumberOfCards() != 0) 
		{
			gameV.showWinner(currentGame.getPlayerName(winner.get(0).getPlayerID()));
		}
	}

	private void displayGameResult()
	{
		String winner ="";
		if(activePlayers.get(0).getPlayerID() == 1) 
		{
			gameV.humanWon();
			winner = "Human "+ activePlayers.get(0).getPlayerID();
		}
		else 
		{
			gameV.humanLoses(currentGame.getPlayerName(activePlayers.get(0).getPlayerID()));
			winner = "AI " + (activePlayers.get(0).getPlayerID()-1);
		}
		currentGame.finishGame(activePlayers.get(0).getPlayerID());
		glog = currentGame.gameLog();
		glog.writeWinner(winner);
		if (!_log)
		{
			System.out.println("User chose not to print log.");
		}
		else
		{
			glog.writeFile();
		}
	}
}
