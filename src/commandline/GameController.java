package commandline;

import java.util.ArrayList;
import java.util.Random;

public class GameController 
{
	private final String username;
	private GameView gameV;
	private PileOfCards communalPile;
	private Player firstChoice;
	private Game currentGame;
	private ArrayList <Player> playerList;
	private ArrayList <Player> activePlayers;

	public GameController(PileOfCards starCitizenDeck, String username) 
	{
		gameV = new GameView(this);
		communalPile = new PileOfCards(null); //0 passed in as no player with an id of 0
		currentGame = new Game(starCitizenDeck);
		playerList = currentGame.getPlayerList();
		this.username = username;
		activePlayers = new ArrayList<Player>();
		for(int i = 0; i < playerList.size(); i++) 
		{
			activePlayers.add(playerList.get(i));
		}
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
			displayCard(currentRound); //displays current card to user

			ArrayList<Integer> categoryComparison = new ArrayList<Integer>();
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

			gameV.showStats(username, categoryComparison.get(0), categoryComparison.get(1), 
					categoryComparison.get(2), categoryComparison.get(3), categoryComparison.get(4));

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
			
			int id = firstChoice.getPlayerId();
			
			if(id == 5) 
			{
				id = 1;
			}
			else 
			{
				id++;
			}
			
			
			firstChoice = playerList.get(id-1);
			
			for(int i = 0; i < playerList.size(); i ++) 
			{
				if(playerList.get(i).getPlayerHand().getNumberOfCards() == 0) 
				{
					gameV.removedPlayers(getPlayerName(playerList.get(i).getPlayerId()));
					activePlayers.remove(i);
				}
			}
		}
		
		
		if(activePlayers.get(0).getPlayerId() == 1) 
		{
			gameV.humanWon();
		}
		else 
		{
			gameV.humanLoses();
		}
		
	}

	private void displayCard(Round2 current) 
	{
		Card currentCard = current.getCard(0); //0 as only currently showing the human players card

		gameV.showCard(playerList.get(0).getPlayerHand().getNumberOfCards(), playerList.get(1).getPlayerHand().getNumberOfCards(), 
				playerList.get(2).getPlayerHand().getNumberOfCards(), playerList.get(3).getPlayerHand().getNumberOfCards(),
				playerList.get(4).getPlayerHand().getNumberOfCards(), currentCard);
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
		for(int i = 0; i < playerList.size(); i++) 
		{
			Card currentCard = playerList.get(i).getPlayerHand().getCurrentCard(); //identifies current card
			communalPile.addCard(currentCard); //adds current card to communal pile
			playerList.get(i).getPlayerHand().removeCard(currentCard); //removes current card from each players hand
		}
		gameV.showDraw(getPlayerName(winners.get(0).getPlayerId()), getPlayerName(winners.get(1).getPlayerId())); //need to feed in the two players who drew
	}

	public void computeWin(ArrayList<Player> winner) 
	{
		winner.get(0).getPlayerHand().addCard(winner.get(0).getPlayerHand().getCurrentCard());
		winner.get(0).getPlayerHand().removeCard(winner.get(0).getPlayerHand().getCurrentCard()); //this will move the current card to the back of the deck

		for(int i = 0; i < playerList.size(); i++) 
		{
			if(i == (winner.get(0).getPlayerId())-1) 
			{

			}
			else 
			{
				winner.get(0).getPlayerHand().addCard(playerList.get(i).getPlayerHand().getCurrentCard()); //will add card to winners hand
				playerList.get(i).getPlayerHand().removeCard(playerList.get(i).getPlayerHand().getCurrentCard()); //will remove card from losers hand
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

		firstChoice = playerList.get(myNumber);
	}

	private String getPlayerName(int playerID) 
	{
		String playerName = "";
		if(playerID == 1) 
		{
			playerName = "Bob";
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
