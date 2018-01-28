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
	
	public GameController(PileOfCards starCitizenDeck, String username) 
	{
		gameV = new GameView(this);
		communalPile = new PileOfCards(null); //0 passed in as no player with an id of 0
		currentGame = new Game(starCitizenDeck);
		playerList = currentGame.getPlayerList();
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
			displayCard(currentRound); //displays current card to user
			
			
			
			ArrayList<Integer> categoryComparison = new ArrayList<Integer>();
						
			
			if(firstChoice.getPlayerId() != 0) 
			{
				
				categoryComparison = currentRound.categoryValues(currentRound.categorySelection());
				
				if(firstChoice.getPlayerId() == 1) 
				{
					gameV.aiSelectCategory("AI Player 1", currentRound.categorySelection());
				}
				else if(firstChoice.getPlayerId() == 2) 
				{
					gameV.aiSelectCategory("AI Player 2", currentRound.categorySelection());
				}
				else if(firstChoice.getPlayerId() == 3) 
				{
					gameV.aiSelectCategory("AI Player 3", currentRound.categorySelection());
				}
				else if(firstChoice.getPlayerId() == 4) 
				{
					gameV.aiSelectCategory("AI Player 4", currentRound.categorySelection());
				}
				
				gameV.userInput(); //for enter command to try break up flow to console
			}
			else if(firstChoice.getPlayerId() == 0)
			{
				gameV.userSelectCategory();
				String category = gameV.userInput();
				categoryComparison = currentRound.categoryValues(category);
			}
			
			gameV.showStats(username, categoryComparison.get(0), categoryComparison.get(1), 
					categoryComparison.get(2), categoryComparison.get(3), categoryComparison.get(4));

//			int winningScore = currentRound.findWinner(categoryComparison);
//			int winner;
//			
//			for(int i = 0; i < playerList.size(); i++) 
//			{
//				//need to be able to get the player/pile of cards which has the winner score - may be one or multiple for draw
//			}
//				
//			if(currentRound.isWinner(categoryComparison)) //if there is an outright winner
//			{
//				//computeWin();
//				//increase round won for winner
//			}
//			else //else if it is a draw then show draw message
//			{
//				computeDraw();
//				currentGame.increaseDraws();
//				//increase rounds drawn for the players who drew
//			}
//			currentGame.increaseRounds();
//			int id = firstChoice.getPlayerId();
//			if(id == 5) 
//			{
//				id = 1;
//			}
//			else 
//			{
//				id++;
//			}
//			firstChoice = playerList.get(id);
		}
	}
	
	private void displayCard(Round2 current) 
	{
		Card currentCard = current.getCard(0); //0 as only currently showing the human players card
		
		gameV.showCard(currentCard);
	}
	
	private boolean isValid() 
	{
		if(playerList.get(0).getPlayerHand().getNumberOfCards() == 0 || playerList.get(0).getPlayerHand().getNumberOfCards() == 40) 
		{
			return false;
		}
		else 
		{
			return true;
		}
	}
	
	private void computeDraw() 
	{
		for(int i = 0; i < playerList.size(); i++) 
		{
			Card currentCard = playerList.get(i).getPlayerHand().getCurrentCard(); //identifies current card
			communalPile.addCard(currentCard); //adds current card to communal pile
			playerList.get(i).getPlayerHand().removeCard(currentCard); //removes current card from each players hand
		}
		gameV.showDraw(null, null); //need to feed in the two players who drew
	}
	
	private void computeWin(int winner) 
	{
		playerList.get(winner).getPlayerHand().addCard(playerList.get(winner).getPlayerHand().getCurrentCard());
		playerList.get(winner).getPlayerHand().removeCard(playerList.get(winner).getPlayerHand().getCurrentCard()); //this will move the current card to the back of the deck
		
		for(int i = 0; i < playerList.size(); i++) 
		{
			if(i == winner) 
			{
				
			}
			else 
			{
				playerList.get(winner).getPlayerHand().addCard(playerList.get(i).getPlayerHand().getCurrentCard()); //will add card to winners hand
				playerList.get(i).getPlayerHand().removeCard(playerList.get(i).getPlayerHand().getCurrentCard()); //will remove card from losers hand
			}
		}
		
		while(communalPile.getNumberOfCards() > 0) 
		{
			playerList.get(winner).getPlayerHand().addCard(communalPile.getCurrentCard());
			communalPile.removeCard(communalPile.getCurrentCard());
		}
				
		gameV.showWinner("" + playerList.get(winner).getPlayerId());
	}
	
	private void setFirstChoice() 
	{
		Random randomNumber = new Random();
		int myNumber = randomNumber.nextInt(5);
		
		firstChoice = playerList.get(myNumber);
	}
	
}
