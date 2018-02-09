package commandline;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class GameController 
{
	private final String username;
	private String difficulty;
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
		currentGame = new Game(starCitizenDeck, username);
		communalPile = currentGame.getCommunalPile();
		playerList = currentGame.getPlayerList();
		activePlayers = currentGame.getActivePlayers();
		this.username = username;
		_log = log;
		difficulty = "";
	}

	public void startGame() 
	{
		System.out.println("Please select a difficulty: easy/hard");
		Scanner in = new Scanner(System.in);
		//		in.useDelimiter("\\n");s
		difficulty.equalsIgnoreCase(in.nextLine());
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
					gameV.removedPlayers(currentGame.getPlayerName(removePlayers.get(i).getPlayerId())); //this should be in gamecontroller
				}
			}
			activePlayers = currentGame.removeFromActivePlayers(); //this method current has gameV line in it - need this in here
			this.cardsInPlay();
			this.writeHand();
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

	private void cardsInPlay()
	{
		for (int i = 0; i < activePlayers.size(); i++)
		{
			glog.writeCardsIP(i, activePlayers.get(i).getPlayerHand().getCurrentCard());
		}
	}

	private void writeHand()
	{
		for (int i = 0; i < 5; i++)
		{
			glog.writeHand(i+1, playerList.get(i).getPlayerHand());
		}
	}

	private String selectCategory(Round currentRound, String category) 
	{
		if(firstChoice.getPlayerId() != 1) 
		{
			if(difficulty.equalsIgnoreCase("easy"))
			{
				category = currentRound.categorySelection();
			}
			else
			{
				category = currentRound.maxCategory(firstChoice.getPlayerHand());
			}
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
		glog.writeCategory(category);
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
			String s = ("\n" + username + " has: " + cardStats.get(0)
			+ " \nAI Player 1 has: " + cardStats.get(1)
			+ " \nAI Player 2 has: " + cardStats.get(2)
			+ " \nAI Player 3 has: " + cardStats.get(3)
			+ " \nAI Player 4 has: " + cardStats.get(4));
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
			gameV.showDraw(currentGame.getPlayerName(winners.get(0).getPlayerId()), currentGame.getPlayerName(winners.get(1).getPlayerId())); //need to feed in the two players who drew
		}
	}

	private void displayRoundWin(Round currentRound, ArrayList<Player> winner) 
	{
		glog.writeCommunalPileEmpty();
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
		String winner ="";
		if(activePlayers.get(0).getPlayerId() == 1) 
		{
			gameV.humanWon();
			winner = "Human ";
		}
		else 
		{
			gameV.humanLoses(currentGame.getPlayerName(activePlayers.get(0).getPlayerId()));
			winner = "AI ";
		}
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
